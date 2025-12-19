package jestgame.cards;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import jestgame.cards.exceptions.*;
import jestgame.cards.characteristics.*;


/**
 * Cards draw class, where players draw a card at each round.
 * Using a Queue implementation allows us to pick the card at the top of the draw.
 * 
 * @author Kiran K {@link https://github.com/KeeTl}.
 */
public class Draw extends CardGroup {

    public static Set<COLOR> colors = new TreeSet<>();
    public static Set<VALUE> values = new TreeSet<>();

    static {
        colors.add(COLOR.CLUBS); colors.add(COLOR.DIAMONDS); colors.add(COLOR.SPADES); colors.add(COLOR.HEARTS);
        values.add(VALUE.ACE); values.add(VALUE.TWO); values.add(VALUE.THREE); values.add(VALUE.FOUR);
    }

    public Draw() {
        this.cards = new LinkedList();

        List<Card> originalCards = new ArrayList<>();
        Iterator<COLOR> iColor = colors.iterator();
        while (iColor.hasNext()) {            
            COLOR currentColor = iColor.next();
            Iterator<VALUE> iValue = values.iterator();
            while(iValue.hasNext()) {
                VALUE currentValue = iValue.next();
                originalCards.add(new Card(currentValue, currentColor));
            }
        }
        this.fill(originalCards);
    }

    public Draw(List<Card> cards) {
        this();
        this.fill(cards);
    }

    public void fill(List<Card> cards) {
        Collections.shuffle(cards);
        Iterator<Card> it = cards.iterator();
        while (it.hasNext()) {
            this.addCard(it.next());
        }
    }

    @Override
    public Card removeCard(int id) throws UnauthorizedCardDrawException {
        UnauthorizedCardDrawException e = new UnauthorizedCardDrawException();
        throw e;
        
    }

    public Card drawCard() {
        return (Card)((Queue)this.cards).remove();
    }

    /*
    public Card pop(int cardIndex) {
        //Card c = (Card)((List)this.cards).get(cardIndex);
        return (Card)((List)this.cards).remove(cardIndex);
    }
    */
}