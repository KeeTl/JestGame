package jestgame.player.strategy;
import jestgame.player.*;
import jestgame.cards.exceptions.*;
import jestgame.cards.Hand;

public interface Strategy {

    public void offerCard(Hand h) throws UnreferencedCardException, CardFlippingException ;
    
    
}