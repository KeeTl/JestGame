
package fr.utt.kirank.jestgame.cards;

import java.util.ArrayList;

public class Hand extends CardStack {

    public Hand() {
        this.cStack = new ArrayList<Card>();
    }

    public Card removeCard(int id) {
        return this.cStack.remove(id);
    }

    




}