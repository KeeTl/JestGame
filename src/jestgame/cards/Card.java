package jestgame.cards;
import jestgame.cards.exceptions.*;
import jestgame.cards.characteristics.*;
import jestgame.visitor.*;

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

    private int score = 0;

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

    public void faceUp() throws CardFlippingException {
        if (this.isFaceup()) {
            CardFlippingException e = new CardFlippingException(this.isFaceup());
            throw e;
        }
        this.faceup = true;
    }

    public void faceDown() throws CardFlippingException {
        if (this.isFaceup()) {
            CardFlippingException e = new CardFlippingException(this.isFaceup());
            throw e;
        }
        this.faceup = false;
    }


    /**
     * Puts a card face up if it is face down and vice-versa.
     */
    public boolean turnFace() {
        this.faceup = !this.faceup;
        return this.faceup;
    }

    public String toString() {
        return this.getval().getV() + " of " + this.getcol().getdesc();

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

    /**
     * Tells if the card is face up or down
     * 
     * @return true if the card is face up, false if it is'nt.
     */
    public boolean isFaceup() {
        return this.faceup;
    }

    public void setscore(int s) {
        this.score = s;
    }

    public int getscore() {
        return this.score;
    }

    public static void main(String[] args) {
        
        Card c = new Card(VALUE.ACE, COLOR.CLUBS);
        System.out.println(c);
    }

}