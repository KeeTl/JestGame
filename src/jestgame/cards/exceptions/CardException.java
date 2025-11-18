package jestgame.cards.exceptions;

public class CardException extends Exception {

    public CardException() {
        super("Unknown CardException");
    }

    public CardException(String msg) {
        super(msg);
    }

}