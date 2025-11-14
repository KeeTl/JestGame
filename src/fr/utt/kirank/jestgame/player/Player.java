package fr.utt.kirank.jestgame.player;


public abstract class Player {

    private String name;


    public Player(String name) {
        this.name = name;
    }

    public String getname() {return this.name;}
    public void setname(String newname) {this.name = newname;}

    public abstract void chooseCard();



}