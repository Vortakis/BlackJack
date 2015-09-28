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

    public enum SideRule {
        INSURANCE, DOUBLE_DOWN, NONE, SURRENDER;
    }

    /** Name of the player. */
    private String name;

    /** Number of chips the player holds. */
    private int chips;

    /** Bet set by the player. */
    private int bet;

    private SideRule sideRule;

    /** Insurance set by the player. */
    private int insurance;

    private int doubleDown;

    private int surrender;

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
        this.sideRule = SideRule.NONE;
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

    public SideRule getSideRule() {
        return this.sideRule;
    }

    public void setSideRule(final SideRule sideRule) {
        this.sideRule = sideRule;
    }

    public int getInsurance() {
        return this.insurance;
    }

    public void setInsurance() {
        this.sideRule = SideRule.INSURANCE;
        this.insurance = this.bet / 2;
        this.chips -= this.insurance;
    }

    public void resetInsurance() {
        this.insurance = 0;
    }

    public int getDoubleDown() {
        return this.doubleDown;
    }

    public void setDoubleDown() {
        this.sideRule = SideRule.DOUBLE_DOWN;
        this.doubleDown = this.bet;
        this.chips -= this.doubleDown;
    }

    public int getSurrender() {
        return this.surrender;
    }

    public void setSurrender() {
        this.sideRule = SideRule.SURRENDER;
        this.surrender = this.bet / 2;
        this.chips += this.surrender;
        this.bet = 0;
    }

    public void resetBets() {
        this.sideRule = SideRule.NONE;
        this.doubleDown = 0;
        this.insurance = 0;
        this.surrender = 0;
        this.bet = 0;
    }

    /**
     * Insurance the bet.
     */
    /*
     * public void insuranceBet() {
     * this.chips -= this.bet / 2;
     * this.bet += this.bet / 2;
     * }
     */

    /**
     * Doubles the bet the player has pre-set.
     */
    /*
     * public void doubleBet() {
     * this.chips -= this.bet;
     * this.bet *= 2;
     * }
     */

    /**
     * Print player's stats.
     */
    public void printPlayerStats() {
        System.out.println("=========================================");
        System.out.println("Player: " + this.name);
        System.out.println("Chips: " + this.chips);
        System.out.println("Current Bet:" + this.bet);
        switch (this.sideRule) {
            case DOUBLE_DOWN:
                System.out.println("DoubleDown:" + this.doubleDown);
                break;
            case INSURANCE:
                System.out.println("Insurance:" + this.insurance);
                break;
            case SURRENDER:
                System.out.println("Surrender:" + this.surrender);
                break;
            default:
                break;
        }
        System.out.println("=========================================");

    }

    /**
     * Print player's hand.
     */
    @Override
    public void printHand(final boolean showFirstCard) {
        System.out.println(this.name);
        super.printHand(showFirstCard);
    }
}
