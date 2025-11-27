package jestgame.cards;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import jestgame.cards.exceptions.*;


/**
 * Cards draw class, where players draw a card at each round.
 * Using a Queue implementation allows us to pick the card at the top of the draw.
 * 
 * @author Kiran K {@link https://github.com/KeeTl}.
 */
public class Draw extends CardGroup {

    public Draw() {
        this.cards = new LinkedList();
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