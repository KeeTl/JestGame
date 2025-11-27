package jestgame.cards.exceptions;

public class UnreferencedCardException extends CardException {
    public UnreferencedCardException(String msg) {
        super(msg + "| Card is not of a known Color or Value");
    }
}