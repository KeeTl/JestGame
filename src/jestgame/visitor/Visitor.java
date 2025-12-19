package jestgame.visitor;
import jestgame.cards.Hand;
import jestgame.cards.Jest;
import jestgame.cards.Trophies;

/**
 * <b> Visitor interface. </b>
 * 
 * 
 */
public interface Visitor {
    public void visit(Jest v);
    public void visit(Hand h); 
    public void visit(Trophies t); 
}