package fr.utt.kirank.jestgame.cards;


public class Card {
    private VALUE val;
    private COLOR col;

    private String face;

    public Card(VALUE val, COLOR col) {
        this.val = val;
        this.col = col;
    }

}