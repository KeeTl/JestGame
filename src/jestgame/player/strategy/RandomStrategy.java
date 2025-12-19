package jestgame.player.strategy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import jestgame.cards.Card;
import jestgame.cards.Hand;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;
import jestgame.player.Player;

public class RandomStrategy implements Strategy {

    @Override
    public void offerCard(Hand h) throws UnreferencedCardException, CardFlippingException {
        h.flipCardUp(ThreadLocalRandom.current().nextInt(0, h.getSize()));
    }


    //public void pickCard()

    @Override
    public Player selectPlayer(List<Player> availablePlayers) {
        return availablePlayers.get(ThreadLocalRandom.current().nextInt(0, availablePlayers.getSize()));
    }

    @Override
    public Card selectCard(Hand h) {
        return h.getCards().get(ThreadLocalRandom.current().nextInt(0, h.getSize()));
    }
}