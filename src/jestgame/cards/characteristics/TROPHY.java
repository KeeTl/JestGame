package jestgame.cards.characteristics;
import java.util.List;
import jestgame.cards.*;
import jestgame.player.*;

public enum TROPHY {
    HIGHEST(
        (players, card) -> {
            Player res = null;
            VALUE minValue = VALUE.ACE;
            for (Player p : players) {
                for (Card c : p.getJest().getCards(card.getcol())) {
                    if (c.getval().getV() > minValue.getV()) res = p;
                }
            }
            return res;
        }
    ),
    LOWEST(
        (players, card) -> {
            Player res = null;
            VALUE minValue = VALUE.JOKER;
            for (Player p : players) {
                for (Card c : p.getJest().getCards(card.getcol())) {
                    if (c.getval().getV() < minValue.getV()) res = p;
                }
            }
            return res;
        }
        
    ),
    MAJORITY( (players, card) -> {
        Player res = players.get(0);
        for (Player p : players) {
            if (p.getJest().count(card.getval()) > res.getJest().count(card.getval())) res = p;
        }
        
        return res;
    }),
    JOKER( (players, card) -> {
        Player res = null;
        for (Player p: players) {
            if (p.getJest().hasJoker()) return p;
        }
        return res;
    }),
    BESTJEST(
        (players, card) -> {
            Player res = players.get(0);
            for (Player p : players) {
                if (p.getJest().getscore() > res .getJest().getscore()) {
                    res = p;
                }

            }
        return res;
        }),
    BESTJEST_NOJOKE(
        (players, card) -> {
            Player res = players.get(0);
            if (res.getJest().hasJoker()) players.get(1);
            for (Player p : players) {
                if ((p.getJest().getscore() > res .getJest().getscore()) && (!p.getJest().hasJoker())) {
                    res = p;
                }

            }
        return res;
        }
    );

    private final TrophyInterface trophyInterface;

    private TROPHY(TrophyInterface trophyInterface) {
        this.trophyInterface = trophyInterface;
    }

    public Player trophy(List<Player> players, Card card) {
        return this.trophyInterface.trophy(players, card);
    }
    
}