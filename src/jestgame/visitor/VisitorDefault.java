package jestgame.visitor;

import java.util.*;
import jestgame.cards.Card;
import jestgame.cards.Hand;
import jestgame.cards.Jest;
import jestgame.cards.Trophies;
import jestgame.cards.characteristics.*;

public class VisitorDefault implements Visitor {

    // Ordre des couleurs pour bris d'égalité (du plus fort au plus faible)
    private static final List<COLOR> COLOR_ORDER = Arrays.asList(
        COLOR.SPADES,    // Plus fort
        COLOR.CLUBS,
        COLOR.DIAMONDS,
        COLOR.HEARTS,
        COLOR.JOKER      // Plus faible
    );

    public void visit(Jest jest) {
        // Compter les cartes par couleur
        Map<COLOR, Integer> colorCount = new HashMap<>();
        for (COLOR color : COLOR.values()) {
            colorCount.put(color, jest.count(color));
        }

        // Premier passage : calculer les valeurs de base
        for (Card c : jest.getCards()) {
            // 1. Valeur de base
            if (c.getval() == VALUE.ACE) {
                // Ace : 5 si seul de sa couleur, sinon 1
                if (colorCount.get(c.getcol()) == 1) {
                    c.setscore(5);
                }
                else {
                    c.setscore(1);
                }
            }
            else if (c.getval() == VALUE.JOKER) {
                c.setscore(0); // Valeur de base 0
            }
            else {
                c.setscore(c.getval().getV()); // 2, 3 ou 4
            }
        }

        for (Card c : jest.getCards()) {
            switch (c.getcol()) {
                case SPADES:
                    c.setscore(c.getscore() * 2);
                    for (Card other : jest.getCards()) {
                        if (other != c && 
                            ((c.getcol() == COLOR.SPADES && other.getcol() == COLOR.CLUBS) ||
                             (c.getcol() == COLOR.CLUBS && other.getcol() == COLOR.SPADES)) &&
                            c.getval() == other.getval()) {
                            c.setscore(c.getscore() + 2);
                        }
                    }
                    break;
                    
                case DIAMONDS:
                    c.setscore(c.getscore() * -1);
                    break;
                    
                case HEARTS:
                    boolean hasJoker = jest.has(COLOR.JOKER);
                    int heartCount = colorCount.get(COLOR.HEARTS);
                    
                    if (hasJoker) {
                        if (heartCount == 4) {
                            c.setscore(c.getscore() * 1);
                        }
                        else if (heartCount <= 3) {
                            c.setscore(c.getscore() * -1);
                        }
                    }
                    else {
                        c.setscore(0);
                    }
                    break;
                    
                case JOKER:
                    int heartsInJest = colorCount.get(COLOR.HEARTS);
                    if (heartsInJest == 0) {
                        c.setscore(4);
                    }
                    else if (heartsInJest == 4) {
                        c.setscore(0);
                    }
                    else {
                        c.setscore(0);
                    }
                    break;
                    
                case CLUBS:
                default:
                    break;
            }
        }

        jest.calculateScore();
    }

    public void visit(Hand hand) {
        for (Card c : hand.getCards()) {
            if (c.isFaceup()) {
                
                float baseValue;
                if (c.getval() == VALUE.ACE) {
                    baseValue = 1;
                }
                else if (c.getval() == VALUE.JOKER) {
                    baseValue = 0;
                }
                else {
                    baseValue = c.getval().getV();
                }
                
                float colorBonus = (COLOR_ORDER.size() - COLOR_ORDER.indexOf(c.getcol())) * 0.01f;
                c.setscore(baseValue + colorBonus);
            }
            else {
                c.setscore(0);
            }
        }
        
        hand.calculateScore();
    }

    public void visit(Trophies t) {
        Map<COLOR, Map<VALUE, TROPHY>> trophiesMap = new HashMap<>();
        
        Map<VALUE, TROPHY> heartsMap = new HashMap<>();
        heartsMap.put(VALUE.ACE, TROPHY.JOKER);
        heartsMap.put(VALUE.TWO, TROPHY.JOKER);
        heartsMap.put(VALUE.THREE, TROPHY.JOKER);
        heartsMap.put(VALUE.FOUR, TROPHY.JOKER);
        trophiesMap.put(COLOR.HEARTS, heartsMap);
        
        Map<VALUE, TROPHY> diamondsMap = new HashMap<>();
        diamondsMap.put(VALUE.ACE, TROPHY.MAJORITY);
        diamondsMap.put(VALUE.TWO, TROPHY.HIGHEST);
        diamondsMap.put(VALUE.THREE, TROPHY.LOWEST);
        diamondsMap.put(VALUE.FOUR, TROPHY.BESTJEST_NOJOKE);
        trophiesMap.put(COLOR.DIAMONDS, diamondsMap);
        
        Map<VALUE, TROPHY> clubsMap = new HashMap<>();
        clubsMap.put(VALUE.ACE, TROPHY.HIGHEST);
        clubsMap.put(VALUE.TWO, TROPHY.LOWEST);
        clubsMap.put(VALUE.THREE, TROPHY.HIGHEST);
        clubsMap.put(VALUE.FOUR, TROPHY.LOWEST);
        trophiesMap.put(COLOR.CLUBS, clubsMap);
        
        Map<VALUE, TROPHY> spadesMap = new HashMap<>();
        spadesMap.put(VALUE.ACE, TROPHY.HIGHEST);
        spadesMap.put(VALUE.TWO, TROPHY.MAJORITY);
        spadesMap.put(VALUE.THREE, TROPHY.MAJORITY);
        spadesMap.put(VALUE.FOUR, TROPHY.LOWEST);
        trophiesMap.put(COLOR.SPADES, spadesMap);
        
        Map<VALUE, TROPHY> jokerMap = new HashMap<>();
        jokerMap.put(VALUE.JOKER, TROPHY.BESTJEST_NOJOKE);
        trophiesMap.put(COLOR.JOKER, jokerMap);

        for (Card c : t.getCards()) {
            Map<VALUE, TROPHY> valueMap = trophiesMap.get(c.getcol());
            if (valueMap != null) {
                TROPHY trophy = valueMap.get(c.getval());
                if (trophy != null) {
                    c.setTrophy(trophy);
                }
            }
        }
    }
}