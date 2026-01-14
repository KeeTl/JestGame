
package jestgame.cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;
import jestgame.visitor.*;

public class Hand extends CardGroup implements Visitable {

    private float score;

    public Hand() {
        this.cards = new ArrayList();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Card removeCard(int id) {
        return (Card)((List)this.cards).remove(id);
    }

    public boolean removeCard(Card c) {
        return ((List)this.cards).remove(c);
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
        int i = 1;
        while (it.hasNext()) {
            Card c = it.next();            
            s.append(i);
            s.append(": ");
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

    public void setScore(int i) {
        this.score = i;
    }

    public float getScore() {
        return this.score;
    }

    public float calculateScore() {
        this.score = 0;
        for (Card c : this.cards) {
            if (c.isFaceup()) this.score += c.getscore();
        }
        return this.score;        
    }


    /*
    public Card removeCard(int id) {
        return (Card)(((List)this.cards).remove(id));
    }
    */


}