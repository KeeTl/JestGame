
package jestgame.cards;

import java.util.*;

public abstract class CardStack {
    protected  Collection<Card> cStack;

    public CardStack() {

    }

    public void addCard(Card c) {
        cStack.add(c);
    }

    public abstract Card removeCard(int id);

    public abstract void shuffle();



}