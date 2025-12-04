package jestgame.visitor.exceptions;

public class UnhandledColorException extends VisitorException {

    public UnhandledColorException() {
        super("Unhandled card color Exception, this card's color isn't recognised !");
    }

    public UnhandledColorException(String msg) {
        super(msg);
    }

}