package jestgame.player.strategy;
import java.util.List;
import jestgame.cards.Card;
import jestgame.cards.Hand;
import jestgame.cards.exceptions.*;
import jestgame.player.*;

public interface Strategy {

    public void offerCard(Hand h) throws UnreferencedCardException, CardFlippingException ;
    public Player selectPlayer(List<Player> availablePlayers);
    public Card selectCard(Hand h);
    
}