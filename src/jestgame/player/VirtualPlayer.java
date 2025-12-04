package jestgame.player;

import jestgame.cards.*;
import jestgame.cards.characteristics.*;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;
import jestgame.player.strategy.*;


public class VirtualPlayer extends Player {
    private Strategy playerStrategy;

    public VirtualPlayer(String name) {
        super(name);
    }

    public void setStrategy(Strategy s) {
        this.playerStrategy = s;
    }

    @Override
    public void chooseCard() {

    }

    @Override
    public void offer() throws UnreferencedCardException, CardFlippingException{
        this.playerStrategy.offerCard(this.hand);
    }

    public static void main(String[] args) {
        Card c1 = new Card(VALUE.ACE, COLOR.SPADES);
        Card c2 = new Card(VALUE.FOUR, COLOR.CLUBS);

        VirtualPlayer player = new VirtualPlayer("Bot #1");
        player.addToHand(c1);
        player.addToHand(c2);
        player.setStrategy(new RandomStrategy());

        try {
            player.offer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        

        System.out.println(player.getHand());



    }


}