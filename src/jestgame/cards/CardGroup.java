
package jestgame.cards;

import jestgame.cards.exceptions.*;
import java.util.Collection;

/**
 * This is an abstract class establishing some methods used by its children classes. The
 * The CardStack class represents any group of cards, wether this group belongs to a Player or the game.
 */
public abstract class CardGroup {

    /**
     * cards is a collection of cards, it can be a List or a Queue.
     */
    protected  Collection<Card> cards;

    /**
     * the addCard method adds a card to the CardStack
     * @param c a card to add
     */
    public void addCard(Card c) {
        cards.add(c);
    }

    public abstract Card removeCard(int id) throws UnauthorizedCardDrawException;
    //public abstract Card removeCard();
}