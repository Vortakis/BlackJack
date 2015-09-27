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

    /** Name of the player. */
    private String name;

    /** Number of chips the player holds. */
    private int chips;

    /** Bet set by the player. */
    private int bet;

    /**
     * Player constructor method.
     *
     * @param name of the player.
     * @param chips number of chips the player starts.
     */
    public Player(final String name, final int chips) {
        super();
        this.name = name;
        this.chips = chips;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
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
        this.chips -= bet;
    }

    /**
     *
     */
    public void printPlayerStats() {
        System.out.println("=========================================");
        System.out.println("Player: " + this.name);
        System.out.println("Chips: " + this.chips);
        System.out.println("Current Bet:" + this.bet);
        System.out.println("=========================================");

    }

    /**
    *
    */
    @Override
    public void printHand(final boolean showFirstCard) {
        System.out.println(this.name);
        super.printHand(showFirstCard);
    }
}
