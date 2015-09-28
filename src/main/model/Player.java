/**
 *
 */
package main.model;

/**
 * Player Class. It extends Hand Class, which enables Player to inherit all of its properties. In addition the Player
 * object has the properties: Name, Chips, Bet, Side-Rule and other methods that enables him to take particular actions.
 *
 * @author Nikos Pavlou
 */
public class Player extends Hand {

    /**
     * SideRule Enum.
     */
    public enum SideRule {
        /** Insurance. */
        INSURANCE,

        /** Double Down. */
        DOUBLE_DOWN,

        /** Surrender. */
        SURRENDER,

        /** None. */
        NONE;
    }

    /** Name of the player. */
    private String name;

    /** Number of chips the player holds. */
    private double chips;

    /** Bet set by the player. */
    private double bet;

    /** Side Rule for the player. */
    private SideRule sideRule;

    /** Insurance value. */
    private double insurance;

    /** Double-down value. */
    private double doubleDown;

    /** Surrender value. */
    private double surrender;

    /**
     * Player constructor method.
     *
     * @param name of the player.
     * @param chips number of chips the player starts.
     */
    public Player(final String name, final double chips) {
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
    public double getChips() {
        return this.chips;
    }

    /**
     * @param chips the chips to set.
     */
    public void setChips(final double chips) {
        this.chips = chips;
    }

    /**
     * @return the bet.
     */
    public double getBet() {
        return this.bet;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(final double bet) {
        this.bet = bet;
        this.chips -= bet;
    }

    /**
     * @return the sideRule.
     */
    public SideRule getSideRule() {
        return this.sideRule;
    }

    /**
     * @param sideRule the sideRule to set.
     */
    public void setSideRule(final SideRule sideRule) {
        this.sideRule = sideRule;
    }

    /**
     * @return the insurance.
     */
    public double getInsurance() {
        return this.insurance;
    }

    /**
     * @param insurance the insurance to set.
     */
    public void setInsurance() {
        this.sideRule = SideRule.INSURANCE;
        this.insurance = this.bet / 2;
        this.chips -= this.insurance;
    }

    /**
     * Resets Insurance.
     */
    public void resetInsurance() {
        this.insurance = 0;
    }

    /**
     * @return the doubleDown.
     */
    public double getDoubleDown() {
        return this.doubleDown;
    }

    /**
     * @param doubleDown the doubleDown to set.
     */
    public void setDoubleDown() {
        this.sideRule = SideRule.DOUBLE_DOWN;
        this.doubleDown = this.bet;
        this.chips -= this.doubleDown;
    }

    /**
     * @return the surrender.
     */
    public double getSurrender() {
        return this.surrender;
    }

    /**
     * @param surrender the surrender to set.
     */
    public void setSurrender() {
        this.sideRule = SideRule.SURRENDER;
        this.surrender = this.bet / 2;
        this.chips += this.surrender;
        this.bet = 0;
    }

    /**
     * Resets all bets and side-rules.
     */
    public void resetBets() {
        this.sideRule = SideRule.NONE;
        this.doubleDown = 0;
        this.insurance = 0;
        this.surrender = 0;
        this.bet = 0;
    }

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
