package jestgame.visitor;
import jestgame.cards.Card;


/**
 * <b> Visitor interface. </b>
 * 
 * 
 */
public interface Visitor {
    public void visit(Card c);    
}