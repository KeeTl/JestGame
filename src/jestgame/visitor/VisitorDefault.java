package jestgame.visitor;
import java.util.HashMap;
import jestgame.cards.Card;
import jestgame.cards.VALUE;
import jestgame.cards.COLOR;
import jestgame.cards.TROPHY;

public class VisitorDefault implements Visitor {

    private HashMap<COLOR, HashMap<VALUE, TROPHY>> trophiesMap = new HashMap();

    @Override
    public void visit(Card card) {
        VALUE v = card.getval();
        COLOR c = card.getcol();
        TROPHY troph = trophiesMap.get(c).get(v);
        card.setTrophy(troph);
        
    }
}