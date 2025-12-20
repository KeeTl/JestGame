package jestgame.player.strategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import jestgame.cards.*;
import jestgame.cards.characteristics.*;
import jestgame.player.Player;

public class AmbitiousStrategy implements Strategy {

    private static final ArrayList<COLOR> priorityColor = new ArrayList() {{
        add(COLOR.HEARTS);
        add(COLOR.DIAMONDS);
        add(COLOR.CLUBS);
        add(COLOR.SPADES);
        add(COLOR.JOKER);
    }};


    @Override
    public void offerCard(Hand h) throws UnreferencedCardException, CardFlippingException{
        if (h.getCards().get(0).getval().getV() != h.getCards().get(1).getval().getV()) {
            h.getCards().stream().max(Comparator.comparing(card -> ((Card)card).getval().getV())).get().faceUp();
        }
        else {
            h.getCards().stream().max(Comparator.comparing(card -> priorityColor.indexOf(h))).get().faceUp();
        }
    }

    @Override
    public Player selectPlayer(List<Player> availablePlayers) {
        
    }

    @Override
    public Card selectCard(Hand h) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}