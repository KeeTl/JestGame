package jestgame.cards;
import jestgame.visitor.Visitor; 

/**
 * Card class, it's name is pretty explicit isn't he ?
 * 
 * @author Kiran K {@link https://github.com/KeeTl}.
 */
public class Card {
    private final VALUE val;
    private final COLOR col;
    private TROPHY troph;

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

    /**
     * Visitor pattern implementation
     * 
     * @param v Visitor type.
     */
    public void accept(Visitor v) {
        v.visit(this);
    }

    public VALUE getval() {
        return this.val;
    }

    public COLOR getcol() {
        return this.col;
    }
    
    public void setTrophy(TROPHY t) {
        this.troph = t;
    }

    public TROPHY gettroph() {
        return this.troph;
    }

}