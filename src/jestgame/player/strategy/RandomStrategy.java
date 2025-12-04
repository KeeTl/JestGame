package jestgame.player.strategy;
import java.util.concurrent.ThreadLocalRandom;
import jestgame.cards.Hand;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;

public class RandomStrategy implements Strategy {

    @Override
    public void offerCard(Hand h) throws UnreferencedCardException, CardFlippingException {
        h.flipCardUp(ThreadLocalRandom.current().nextInt(0, h.getSize()));
    }

}