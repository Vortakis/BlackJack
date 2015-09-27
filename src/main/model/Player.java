/**
 *
 */
package main.model;

/**
 * Player Class.
 *
 * @author Nikos Pavlou
 */
public class Player extends Hand {

    /** Number of chips the player holds. */
    private int chips;

    /** Bet set by the player. */
    private int bet;

    /**
     * Player constructor method.
     *
     * @param chips number of chips the player starts.
     */
    public Player(final int chips) {
        super();
        this.chips = chips;
    }

    /**
     * @return the chips.
     */
    public int getChips() {
        return this.chips;
    }

    /**
     * @param chips the chips to set.
     */
    public void setChips(final int chips) {
        this.chips = chips;
    }

    /**
     * @return the bet.
     */
    public int getBet() {
        return this.bet;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(final int bet) {
        this.bet = bet;
    }

}
