package jestgame;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import jestgame.cards.*;
import jestgame.player.*;
import jestgame.visitor.Visitor;
import jestgame.visitor.VisitorDefault;

public class JestGame {
    private Draw draw;
    private Trophies trophies;
    private ArrayList<Player> players;
    private Visitor visitor;

    public JestGame(int nPhysicalPlayers, int nVirtualPlayers, String[] pNames) {
        this.draw = new Draw();
        this.trophies = new Trophies();
        this.visitor = new VisitorDefault();
        
        for (int i = 0; i < nPhysicalPlayers; i++) {
            players.add(new PhysicalPlayer(pNames[i]));
        }

        for (int j = 0; j < nVirtualPlayers; j++) {
            players.add(new VirtualPlayer(pNames[nPhysicalPlayers + j]));
        }

        this.trophies.addCard(this.draw.drawCard());
        this.trophies.addCard(this.draw.drawCard());

        for (int i = 0; i < 2; i++) {
            Iterator<Player> iPlayer = this.players.iterator();
            while (iPlayer.hasNext()) {
                iPlayer.next().addToHand(this.draw.drawCard());
            }
        }
    }

    /**
     * 
     */
    public void round() {
        for (Player p : this.players) {
            p.acceptHand(this.visitor);
        }

        ArrayList<Player> playersLeftToPlay = new ArrayList(this.players);
        ArrayList<Player> playersAvailableToPick = new ArrayList(this.players);
        playersLeftToPlay.sort(Comparator.comparing(player -> ((Player)player).getHand().getScore()).reversed());

        Player nextPlayer = playersLeftToPlay.remove(0);

        while (!playersLeftToPlay.isEmpty()) {

            nextPlayer = nextPlayer.chooseCard(playersAvailableToPick, this.draw);
            playersAvailableToPick.remove(nextPlayer);

            if (playersLeftToPlay.indexOf(nextPlayer) == -1) {
                nextPlayer = playersLeftToPlay.remove(0);
            }

            else {
                playersLeftToPlay.remove(nextPlayer);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (Player p : this.players) {
                if (this.draw.size() == 0) {
                    return;
                }
                p.addToHand(this.draw.drawCard());
            }
        }
    }

    public Player endGame() {
        this.trophies.accept(this.visitor);
        for (Player p : this.players) {
            p.acceptJest(this.visitor);
        }

        for (Card c : this.trophies.getCards()) {
            Player p = c.gettroph().trophy(this.players, c);
            if (p != null) {
                int cardIndex = this.trophies.getCards().indexOf(c);
                Card card = this.trophies.getCards().get(cardIndex);
                this.trophies.getCards().remove(cardIndex);
                p.addToJest(card);
            }
        }

        return this.players.stream().max(Comparator.comparing(p -> ((Player)p).getJest().getScore())).orElse(null);


        
    }


    public static void main() {



        
    }
}