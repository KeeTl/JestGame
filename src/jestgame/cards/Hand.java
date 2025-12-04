
package jestgame.cards;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;

public class Hand extends CardGroup {

    public Hand() {
        this.cards = new ArrayList();
    }

    public Card removeCard(int id) {
        return (Card)((List)this.cards).remove(id);
    }

    public List<Card> getCards() {
        return (List)this.cards;
    }

    public int getSize() {
        return this.cards.size();
    }

    public void flipCardUp(Card c) throws UnreferencedCardException, CardFlippingException {
        int i = ((List)this.cards).indexOf(c);
        if (i == -1) {
            UnreferencedCardException e = new UnreferencedCardException("Card is not in hand !");
            throw e;
        }

        ((Card)(((List)this.cards).get(i))).faceUp();
    }

    public void flipCardUp(int i) throws UnreferencedCardException, CardFlippingException {
        if (i < 0 || i > this.cards.size()) {
            UnreferencedCardException e = new UnreferencedCardException("Card's index is incorrect !");
            throw e;
        }
        ((Card)((List)this.cards).get(i)).faceUp();

    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        Iterator<Card> it = this.cards.iterator();
        while (it.hasNext()) {
            Card c = it.next();
            if (c.isFaceup()) {
                s.append(c.toString());
            }
            else {
                s.append("Faced down card.");
            }
            s.append(" | ");
        }
        return s.toString();
    }    

    /*
    public Card removeCard(int id) {
        return (Card)(((List)this.cards).remove(id));
    }
    */


}