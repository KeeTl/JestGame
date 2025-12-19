package jestgame.visitor;
import java.util.*;
import jestgame.cards.Card;
import jestgame.cards.Hand;
import jestgame.cards.Jest;
import jestgame.cards.Trophies;
import jestgame.cards.characteristics.*;

public class VisitorDefault implements Visitor {

    public void visit(Jest jest) {

        HashMap<COLOR, Integer> colorCount = new HashMap() {{
            put(COLOR.CLUBS, jest.count(COLOR.CLUBS));
            put(COLOR.DIAMONDS, jest.count(COLOR.DIAMONDS));
            put(COLOR.HEARTS, jest.count(COLOR.HEARTS));
            put(COLOR.SPADES, jest.count(COLOR.SPADES));
        }};

        List<Card> cards = jest.getCards();
        Iterator<Card> iCard = cards.iterator();

        while (iCard.hasNext()) {
            Card c = iCard.next();
            if (c.getval() == VALUE.ACE) {
                    if (colorCount.get(c.getcol()) == 1) c.setscore(4);
                    else c.setscore(1);
            }

            else {
                c.setscore(c.getval().getV());
            }

            switch (c.getcol()) {
                case (COLOR.SPADES):
                    c.setscore(c.getscore() * 2);
                    if (jest.has(c.getcol()) && jest.has(c.getval())) {
                        c.setscore(c.getscore() + 2);
                    }
                    break;
                case (COLOR.DIAMONDS):
                    c.setscore(c.getscore() * - 1);
                    break;
                case (COLOR.HEARTS):
                    if (jest.hasJoker()) {
                        if (jest.count(COLOR.HEARTS) <= 3) {
                            c.setscore(c.getscore() * - 1);
                        }
                    }
                    break;
                case(COLOR.JOKER):
                    if (jest.count(COLOR.HEARTS) == 0) {
                        c.setscore(4);
                    }
                    else {
                        c.setscore(0);
                    }

                default:

                    break;
            }
        }

        jest.calculateScore();
    }

    
    public void visit(Hand hand) {
        List<COLOR> colorVals = new ArrayList<>() {{
            add(COLOR.HEARTS);
            add(COLOR.DIAMONDS);
            add(COLOR.CLUBS);
            add(COLOR.SPADES);
            add(COLOR.JOKER);
            }};

        for (Card c : hand.getCards()) {
            if (c.isFaceup()) {
                c.setscore(c.getval().getV() + (colorVals.indexOf(c.getcol()) / 10));                
            }
        }
    }

    public void visit(Trophies t) {
        Map<COLOR, Map<VALUE, TROPHY>> trophiesMap = new HashMap() {{
            put(COLOR.HEARTS, new HashMap() {{
                put(VALUE.ACE, TROPHY.JOKER);
                put(VALUE.TWO, TROPHY.JOKER);
                put(VALUE.THREE, TROPHY.JOKER);
                put(VALUE.FOUR, TROPHY.JOKER);
            }});

            put(COLOR.DIAMONDS, new HashMap() {{
                put(VALUE.ACE, TROPHY.MAJORITY);
                put(VALUE.TWO, TROPHY.HIGHEST);
                put(VALUE.THREE, TROPHY.LOWEST);
                put(VALUE.FOUR, TROPHY.BESTJEST_NOJOKE);
            }});

            put(COLOR.CLUBS, new HashMap() {{
                put(VALUE.ACE, TROPHY.HIGHEST);
                put(VALUE.TWO, TROPHY.LOWEST);
                put(VALUE.THREE, TROPHY.HIGHEST);
                put(VALUE.FOUR, TROPHY.LOWEST);
            }});
            
             put(COLOR.SPADES, new HashMap() {{
                put(VALUE.ACE, TROPHY.HIGHEST);
                put(VALUE.TWO, TROPHY.MAJORITY);
                put(VALUE.THREE, TROPHY.MAJORITY);
                put(VALUE.FOUR, TROPHY.LOWEST);
            }});

        }};

        for (Card c : t.getCards()) {
            c.setTrophy(trophiesMap.get(c.getcol()).get(c.getval()));
        }
    }
}
