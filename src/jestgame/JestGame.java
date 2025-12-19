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
    }


    public static void main() {

        
    }
}