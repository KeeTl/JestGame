package jestgame.cards.exceptions;

public class CardFlippingException extends CardException {

    public CardFlippingException(String msg, boolean cardState) {

        String message = msg + " | Card is : " + ((cardState) ? "flipped" : "not flipped");

        super(message);
    }

    public CardFlippingException(boolean cardState) {
        String message = "Card is already in that position";
        this(message, cardState);
    }
}