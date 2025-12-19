package jestgame.cards;

import java.util.List;
import java.util.ArrayList;
import jestgame.cards.exceptions.CardFlippingException;

public class Trophies extends CardGroup {
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

    @Override
    public Card removeCard(int id) {
        return (Card)((List)this.cards).remove(id);
    }

}