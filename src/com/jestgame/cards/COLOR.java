package com.jestgame.cards;

public enum COLOR {
    SPADES("spades", "pic"),
    CLUBS("clubs", "trèele"),
    DIAMONDS("diamonds", "carreaux"),
    HEARTS("hearts", "coeur"),
    JOKER("joker", "joker");

    private final String desc;
    private final String descFrench;


    private COLOR(String desc, String descFrench) {
        this.desc = desc;
        this.descFrench = descFrench;
    }

    public  String getdesc() {
        return this.desc;
    }

    public String getdescFrench() {
        return this.descFrench;
    }

}