/* package jestgame.player.strategy;
import jestgame.cards.*;
import jestgame.player.*;

public class AmbitiousStrategy implements  Strategy {

    @Override
    public void offerCard(Hand h) throws UnreferencedCardException, CardFlippingException{
        // ERREUR : comparer card avec h au lieu de card.getcol()
        if (h.getCards().get(0).getval().getV() != h.getCards().get(1).getval().getV()) {
            h.getCards().stream().max(Comparator.comparing(card -> ((Card)card).getval().getV())).get().faceUp();
        }
        else {
            // CORRIGER CETTE LIGNE :
            h.getCards().stream().max(Comparator.comparing(card -> priorityColor.indexOf(((Card)card).getcol()))).get().faceUp();
        }
    }

    @Override
    public Player selectPlayer(List<Player> availablePlayers) {
        // IMPLÉMENTER CETTE MÉTHODE (actuellement vide)
        if (availablePlayers.isEmpty()) return null;
        
        // Stratégie ambitieuse : choisir le joueur avec la meilleure main
        Player bestPlayer = availablePlayers.get(0);
        float bestScore = bestPlayer.getHand().getScore();
        
        for (Player p : availablePlayers) {
            if (p.getHand().getScore() > bestScore) {
                bestPlayer = p;
                bestScore = p.getHand().getScore();
            }
        }
        return bestPlayer;
    }

    @Override
    public Card selectCard(Hand h) {
        // IMPLÉMENTER CETTE MÉTHODE
        // Stratégie ambitieuse : prendre la carte face visible
        for (Card c : h.getCards()) {
            if (c.isFaceup()) {
                return h.removeCard(h.getCards().indexOf(c));
            }
        }
        // Sinon prendre la carte avec la plus haute valeur
        Card highestCard = h.getCards().get(0);
        for (Card c : h.getCards()) {
            if (c.getval().getV() > highestCard.getval().getV()) {
                highestCard = c;
            }
        }
        return h.removeCard(h.getCards().indexOf(highestCard));
    }

}
*/