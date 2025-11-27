package jestgame.visitor;
import java.util.HashMap;
import jestgame.cards.Card;
import jestgame.cards.VALUE;
import jestgame.cards.COLOR;
import jestgame.cards.TROPHY;

public class VisitorDefault implements Visitor {

    private  static final HashMap<COLOR, HashMap<VALUE, TROPHY>> trophiesDefaultMap = new HashMap() {{
        put(COLOR.SPADES, new HashMap<VALUE, TROPHY>() {{
            put(VALUE.ACE, TROPHY.HIGHEST);
            put(VALUE.TWO, TROPHY.MAJORITY);
            put(VALUE.THREE, TROPHY.MAJORITY);
            put(VALUE.FOUR, TROPHY.LOWEST);
        }});
        put(COLOR.CLUBS, new HashMap<VALUE, TROPHY>() {{
            put(VALUE.ACE, TROPHY.HIGHEST);
            put(VALUE.TWO, TROPHY.LOWEST);
            put(VALUE.THREE, TROPHY.HIGHEST);
            put(VALUE.FOUR, TROPHY.LOWEST);
        }});
        put(COLOR.DIAMONDS, new HashMap<VALUE, TROPHY>() {{
            put(VALUE.ACE, TROPHY.MAJORITY);
            put(VALUE.TWO, TROPHY.HIGHEST);
            put(VALUE.THREE, TROPHY.LOWEST);
            put(VALUE.FOUR, TROPHY.BESTJEST_NOJOKE);
        }});
        put(COLOR.HEARTS, new HashMap<VALUE, TROPHY>() {{
            put(VALUE.ACE, TROPHY.JOKER);
            put(VALUE.TWO, TROPHY.JOKER);
            put(VALUE.THREE, TROPHY.JOKER);
            put(VALUE.FOUR, TROPHY.JOKER);
        }});
    }};

    @Override
    public void visit(Card card) {
        VALUE v = card.getval();
        COLOR c = card.getcol();
        TROPHY troph = trophiesDefaultMap.get(c).get(v);
        card.setTrophy(troph);
    }
}