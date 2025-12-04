package jestgame.visitor.exceptions;

public class VisitorException extends Exception {
    public VisitorException() {
        super("Unkown Visitor Excpetion");
    }

    public VisitorException(String msg) {
        super(msg);
    }

}