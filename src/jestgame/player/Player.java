package jestgame.player;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import jestgame.cards.*;
import jestgame.cards.exceptions.*;
import jestgame.visitor.*;

public abstract class Player {

    private String name;
    protected Hand hand;
    private Jest jest;
    protected PropertyChangeSupport diffuser;


    public Player(String name) {
        this.hand = new Hand();
        this.name = name;
        this.jest = new Jest();
        this.diffuser = new PropertyChangeSupport(this);
    }

    public void addEventListener(PropertyChangeListener pcl) {
        this.diffuser.addPropertyChangeListener(pcl);
        this.diffuser.firePropertyChange("hand", null, this.hand); 
        this.diffuser.firePropertyChange("name", null, this.name); 
        this.diffuser.firePropertyChange("jest", null, this.jest);
    }

    public String getname() {return this.name;}
    public void setname(String newname) {this.name = newname; this.diffuser.firePropertyChange("name", null, this.name);}


    public abstract void offer() throws UnreferencedCardException, CardFlippingException;

    public void addToHand(Card c) {
        this.hand.addCard(c);
        this.diffuser.firePropertyChange("hand", null, this.hand);
    }
    public void addToJest(Card c) {
        this.jest.addCard(c);
        this.diffuser.firePropertyChange("jest", null, this.jest);
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
        this.diffuser.firePropertyChange("hand", null, this.hand);
        return this.hand;
    }

    public final Player chooseCard(List<Player> availablePlayers, Draw draw) {
        Player res = null;
        if (availablePlayers.isEmpty()) {
            this.addToJest(draw.drawCard());
        }

        else {
            res = this.selectPlayer(availablePlayers);
            Card c = this.selectCard(res.getHand());
            res.getHand().removeCard(c);
            this.addToJest(c);
                           
        }
        return res;
    
    }

    protected abstract Player selectPlayer(List<Player> availablePlayers);
    protected abstract Card selectCard(Hand h);
}