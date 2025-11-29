package jestgame.visitor;
import jestgame.cards.Jest;
import jestgame.cards.Card;
import jestgame.cards.characteristics.*;
import java.util.*;
import jestgame.cards.characteristics.*;

public class VisitorDefault implements Visitor {

    public void visit(Jest jest) {

        HashMap<COLOR, Integer> colorCount = new HashMap() {{
            put(COLOR.CLUBS, jest.count(COLOR.CLUBS));
            put(COLOR.DIAMONDS, jest.count(COLOR.DIAMONDS));
            put(COLOR.HEARTS, jest.count(COLOR.HEARTS));
            put(COLOR.SPADES, jest.count(COLOR.SPADES));
        }};

        List<Card> cards = jest.getCards();
        Iterator<Card> iCard = cards.iterator();

        while (iCard.hasNext()) {
            Card c = iCard.next();
            if (c.getval() == VALUE.ACE) {
                    if (colorCount.get(c.getcol()) == 1) c.setscore(4);
                    else c.setscore(1);
            }

            else {
                c.setscore(c.getval().getV());
            }

            switch (c.getcol()) {
                case (COLOR.SPADES):
                    c.setscore(c.getscore() * 2);
                    if (jest.has(c.getcol()) && jest.has(c.getval())) {
                        c.setscore(c.getscore() + 2);
                    }
                    break;
                case (COLOR.DIAMONDS):
                    c.setscore(c.getscore() * - 1);
                    break;
                case (COLOR.HEARTS):
                    if (jest.hasJoker()) {
                        if (jest.count(COLOR.HEARTS) <= 3) {
                            c.setscore(c.getscore() * - 1);
                        }
                    }
                    break;
                case(COLOR.JOKER):
                    if (jest.count(COLOR.HEARTS) == 0) {
                        c.setscore(4);
                    }
                    else {
                        c.setscore(0);
                    }

                default:

                    break;
            }
        }

        jest.calculateScore();
    }
}