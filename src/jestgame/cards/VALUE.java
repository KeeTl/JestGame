package jestgame.cards;


/**
 * Value enum class, composed
 */
public enum VALUE{
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    JOKER(6);

    /**
     * Not sure if I'll keep this like that
     */
    private final int v;

    private VALUE(int v) {
        this.v = v;
    }

    public int getV() {
        return this.v;
    }

}