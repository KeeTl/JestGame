package jestgame.visitor;
import jestgame.cards.Jest;
import jestgame.cards.Hand;

/**
 * <b> Visitor interface. </b>
 * 
 * 
 */
public interface Visitor {
    public void visit(Jest v);
    public void visit(Hand h);    
}