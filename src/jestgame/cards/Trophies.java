package jestgame.cards;

import java.util.ArrayList;
import java.util.List;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.visitor.Visitable;
import jestgame.visitor.Visitor;

public class Trophies extends CardGroup implements Visitable{
    public Trophies() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card c) {
        if (!c.isFaceup()) try {
            c.faceUp();   
        } catch (CardFlippingException e) {
            e.printStackTrace();
        }
        super.addCard(c);
    }

    public List<Card> getCards() {
        return (List)this.cards;
    }

    @Override
    public Card removeCard(int id) {
        return (Card)((List)this.cards).remove(id);
    }


    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}