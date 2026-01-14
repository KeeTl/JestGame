package jestgame;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import jestgame.cards.*;
import jestgame.cards.exceptions.CardFlippingException;
import jestgame.cards.exceptions.UnreferencedCardException;
import jestgame.player.*;
import jestgame.player.strategy.RandomStrategy;
import jestgame.view.Vue;
import jestgame.visitor.Visitor;
import jestgame.visitor.VisitorDefault;

public class JestGame implements Runnable {
    private Draw draw;
    private Trophies trophies;
    private ArrayList<Player> players;
    private Visitor visitor;

    public Draw getDraw() {
        return this.draw;
    }

    public ArrayList<Player> getPlayers() {return this.players;}
    public Trophies trophies() {return this.trophies;}

    public JestGame(int nPhysicalPlayers, int nVirtualPlayers, String[] pNames) {
        this.draw = new Draw();
        this.trophies = new Trophies();
        this.visitor = new VisitorDefault();
        this.players = new ArrayList();
        
        for (int i = 0; i < nPhysicalPlayers; i++) {
            players.add(new PhysicalPlayer(pNames[i]));
        }

        for (int j = 0; j < nVirtualPlayers; j++) {
            players.add(new VirtualPlayer(pNames[nPhysicalPlayers + j], new RandomStrategy()));
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

    public void partie() {
        new Thread(this).start();
    }

    public void run() {
        while (this.getDraw().size() != 0) {
            this.round();
        }

        List<Player> endRanking = this.endGame();
        for (Player p : endRanking) {
            System.out.println(p.getname());
        }
    }

    /**
     * 
     */
    public void round() {
    
        for (Player p: this.players) {
            try {
                p.offer();
            } catch (UnreferencedCardException ex) {
                System.getLogger(JestGame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (CardFlippingException ex) {
                System.getLogger(JestGame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

        for (Player p : this.players) {
            p.acceptHand(this.visitor);
        }

        ArrayList<Player> avalaiblePlayers = new ArrayList<>(this.players);
        ArrayList<Player> playOrder = new ArrayList<>(this.players);

        playOrder.sort(Comparator.comparing(p -> ((Player)  p).getHand().getScore()).reversed());

        Player currentPlayer = playOrder.remove(0);

        while (!playOrder.isEmpty()) {
            Player chosenPlayer;
            if (avalaiblePlayers.contains(currentPlayer)) {
                avalaiblePlayers.remove(currentPlayer);
                chosenPlayer = currentPlayer.chooseCard(avalaiblePlayers, this.draw);
                avalaiblePlayers.add(currentPlayer);
            }

            else {
                chosenPlayer = currentPlayer.chooseCard(avalaiblePlayers, this.draw);
            }

            avalaiblePlayers.remove(chosenPlayer);

            if (chosenPlayer != null) {
                if (playOrder.contains(chosenPlayer)) {
                    currentPlayer = chosenPlayer;
                    playOrder.remove(currentPlayer);
                }

                else {
                    currentPlayer = playOrder.remove(0);
                }

            }
        }


        if (this.draw.size() > 0) {
            for (Player p  : this.players) {
                while (p.getHand().getSize() > 0) {
                    Card remainingCard = p.getHand().removeCard(0);
                    p.addToJest(remainingCard);
                }
            }
            for (int i = 0; i < 2; i++) {
                for (Player p : this.players) {
                    if (this.draw.size() > 0) {
                        p.addToHand(this.draw.drawCard());
                    }
                }
            }
        }
    }
    
    public List<Player> endGame() {
        this.trophies.accept(this.visitor);
        for (Player p : this.players) {
            p.acceptJest(this.visitor);
        }

        Iterator<Card> iC = this.trophies.getCards().iterator();
        while (iC.hasNext()) {
            Card c = iC.next();
            Player p = c.gettroph().trophy(this.players, c);
            if (p != null) {
                this.trophies.getCards().remove(c);
                p.addToJest(c);
            }
        }
        
        List<Player> res = new ArrayList(this.players);
        res.sort(Comparator.comparing(p -> ((Player)  p).getJest().getScore()).reversed());
        return res;
        
        
    } 


    public static void main(String[] args) {
        System.out.print("Please enter your name : ");
        Scanner s = new Scanner(System.in);
        String playerName = s.nextLine();
        String[] pNames = {playerName, "bot1", "bot2"}; 

        JestGame game = new JestGame(1, 2, pNames);
        
        Vue GUI = new Vue(game);

        game.partie();

    }
}