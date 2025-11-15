package fr.utt.kirank.jestgame.cards;

import fr.utt.kirank.jestgame.cards.visitor.*;

/**
 * Card class, it's name is pretty explicit isn't he ?
 * 
 * @author Kiran K {@link https://github.com/KeeTl}.
 */
public class Card {
    private VALUE val;
    private COLOR col;
    //private TROPHY troph;

    private boolean faceup;

    /**
     * Card init.
     * Creates a Card object.
     * 
     * he trophy condition is detnermined by {@link fr.utt.kirank.jestgame.cards.visitor}
     * @param val Value of the card
     * @param col color (suit) of the card
     *
     */
    public Card(VALUE val, COLOR col) {
        this.val = val;
        this.col = col;
        this.faceup = false;
    }

    /**
     * Tells if the card is face up or down
     * 
     * @return true if the card is face up, false if it is'nt.
     */
    public boolean faceup() {
        return this.faceup;
    }


    /**
     * Puts a card face up if it is face down and vice-versa.
     */
    public boolean turnFace() {
        this.faceup = !this.faceup;
        return this.faceup;
    }

    public void Accept(Visitor v) {

    }

}