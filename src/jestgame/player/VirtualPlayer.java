package jestgame.player;

import java.util.List;
import jestgame.cards.*;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;
import jestgame.player.strategy.*;


public class VirtualPlayer extends Player {
    private Strategy playerStrategy;

    public VirtualPlayer(String name, Strategy s) {
        this.playerStrategy = s;
        super(name);
    }

    public void setStrategy(Strategy s) {
        this.playerStrategy = s;
    }

    @Override
    public void offer() throws UnreferencedCardException, CardFlippingException{
        this.playerStrategy.offerCard(this.hand);
    }

    @Override
    protected Player selectPlayer(List<Player> availablePlayers) {
        return this.playerStrategy.selectPlayer(availablePlayers);
    }

    @Override
    protected Card selectCard(Hand h) {
        return this.playerStrategy.selectCard(h);
    }


}