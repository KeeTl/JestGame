package jestgame.player;
import java.util.List;
import jestgame.cards.*;
import jestgame.cards.exceptions.*;
import jestgame.visitor.*;

public abstract class Player {

    private String name;
    protected Hand hand;
    private Jest jest;


    public Player(String name) {
        this.hand = new Hand();
        this.name = name;
        this.jest = new Jest();
    }

    public String getname() {return this.name;}
    public void setname(String newname) {this.name = newname;}


    public abstract void offer() throws UnreferencedCardException, CardFlippingException;

    public void addToHand(Card c) {
        this.hand.addCard(c);
    }
    public void addToJest(Card c) {
        this.jest.addCard(c);
    }

    public void acceptHand(Visitor v) {
        this.hand.accept(v);
    }
    public void acceptJest(Visitor v) {
        this.jest.accept(v);
    }

    public Jest getJest() {
        System.out.print("");
        return this.jest;
    }

    public Hand getHand() {
        return this.hand;
    }

    public final Player chooseCard(List<Player> availablePlayers, Draw draw) {
        if (availablePlayers == null || availablePlayers.isEmpty()) {
            // pas d'adversaire -> piocher et terminer ce tour
            this.addToJest(draw.drawCard());
            return null;
        }
        Player res = this.selectPlayer(availablePlayers);
        if (res != null) {
            Card chosen = this.selectCard(res.getHand());
            if (chosen != null) {
                this.addToJest(chosen);
            }
        }
        return res;
    
    }

    protected abstract Player selectPlayer(List<Player> availablePlayers);
    protected abstract Card selectCard(Hand h);
}