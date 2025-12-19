package jestgame.cards;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jestgame.cards.characteristics.*;
import jestgame.visitor.*;


public class Jest extends CardGroup implements Visitable{

    private float score;

    public Jest() {
        this.cards = new ArrayList();
    }

    public Card removeCard(int id) {
        return (Card)((List)this.cards).remove(id);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


    public boolean has(COLOR c) {
        Iterator<Card> iC = this.cards.iterator();
        while (iC.hasNext()) {
            if (iC.next().getcol() == c) return true;
        }
        return false;
    }

    public boolean has (VALUE v) {
        Iterator<Card> iC = this.cards.iterator();
        while (iC.hasNext()) {
            if (iC.next().getval() == v) return true;
        }
        return false;
    }

    public boolean hasJoker() {
        return this.has(COLOR.JOKER) && this.has(VALUE.JOKER);
    }

    public int count(COLOR c) {
        int count = 0;
        Iterator<Card> iC = this.cards.iterator();
        while (iC.hasNext()) {
            if (iC.next().getcol() == c) count++;
        }
        return count;
    }

    public int count(VALUE v) {
        int count = 0;
        Iterator<Card> iC = this.cards.iterator();
        while (iC.hasNext()) {
            if (iC.next().getval() == v) count++;
        }
        return count;
    }

    public int calculateScore() {
        this.score = 0;
        Iterator<Card> c = this.cards.iterator();
        while (c.hasNext()) {
            this.score += c.next().getscore();
        }
        return (int)this.score;        
    }

    public Card lowestCard() {
        Card res = (Card)(((List)this.cards).get(0));
        Iterator<Card> c = this.cards.iterator();

        while(c.hasNext()) {
            Card current = c.next();
            if (current.getscore() < res.getscore()) {
                res = current;
            }
        }
        return res;
    }

    public Card highestCard() {
        Card res = (Card)(((List)this.cards).get(0));
        Iterator<Card> c = this.cards.iterator();

        while(c.hasNext()) {
            Card current = c.next();
            if (current.getscore() > res.getscore()) {
                res = current;
            }
        }
        return res;
    }

    public List<Card> getCards() {
        return (List)this.cards;
    }

    public List<Card> getCards(VALUE v) {
        List<Card> res = new ArrayList();
        Iterator<Card> iC = this.cards.iterator();
        while (iC.hasNext()) {
            Card current = iC.next();
            if (current.getval() == v) {
                res.add(current);
            }
        }
        return res;
    }

    public List<Card> getCards(COLOR c) {
        List<Card> res = new ArrayList();
        Iterator<Card> iC = this.cards.iterator();
        while (iC.hasNext()) {
            Card current = iC.next();
            if (current.getcol() == c) {
                res.add(current);
            }
        }
        return res;
    }

    public int getscore() {
        return (int)this.score;
    }

    public void setScore(int s) {
        this.score = s;
    }

    /*
    public Card removeCard() {
        

    } */

    public static void main(String[] args) {
        Jest j = new Jest();
        j.addCard(new Card(VALUE.ACE, COLOR.CLUBS));
        j.addCard(new Card(VALUE.TWO, COLOR.SPADES));
        j.addCard(new Card(VALUE.ACE, COLOR.HEARTS));
        j.addCard(new Card(VALUE.JOKER, COLOR.JOKER));

        j.accept((Visitor)(new VisitorDefault()));
        System.out.println(j.getscore());
    }
}