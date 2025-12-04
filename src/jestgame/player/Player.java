package jestgame.player;
import jestgame.cards.*;
import jestgame.visitor.*;
import jestgame.player.strategy.*;
import jestgame.cards.exceptions.*;

public abstract class Player {

    private String name;
    protected Hand hand;
    private Jest jest;


    public Player(String name) {
        this.hand = new Hand();
        this.name = name;
    }

    public String getname() {return this.name;}
    public void setname(String newname) {this.name = newname;}


    public abstract void offer() throws UnreferencedCardException, CardFlippingException;

    public void addToHand(Card c) {
        this.hand.addCard(c);
    }

    public void accept(Visitor v) {
        this.jest.accept(v);
    }

    public Jest getJest() {
        return this.jest;
    }

    public Hand getHand() {
        return this.hand;
    }

    public abstract void chooseCard();



}