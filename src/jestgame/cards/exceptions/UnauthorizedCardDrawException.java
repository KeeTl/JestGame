package jestgame.cards.exceptions;

public class UnauthorizedCardDrawException extends CardException {
    public UnauthorizedCardDrawException() {
        super("Card cannot be drawn");
    }

    public UnauthorizedCardDrawException(String msg) {
        super(msg);
    }


    
}