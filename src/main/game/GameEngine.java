/**
 *
 */
package main.game;

import java.util.Scanner;

import main.model.Dealer;
import main.model.Player;

/**
 * Game Engine class.
 *
 * @author Nikos Pavlou
 */
public class GameEngine {

    /** Game counter. */
    private int gameCounter = 1;

    /** Player object. */
    private Player player;

    /** Dealer object. */
    private Dealer dealer;

    /** Scanner to read the command-line inputs. */
    private final Scanner scanner;

    /** Double-down flag. */
    private boolean doubleDownFlag = false;

    /** Insurance flag. */
    private boolean insuranceFlag = false;

    /**
     * Game Engine constructor.
     */
    public GameEngine() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initialise game.
     *
     * @param playerName the name of the player.
     * @param numOfDecks the number of decks in the game.
     * @param numOfChips the initial number of chips.
     */
    public void initialiseGame(final String playerName, final int numOfDecks, final int numOfChips) {
        this.player = new Player(playerName, numOfChips);
        this.dealer = new Dealer(numOfDecks);
    }

    /**
     * Play the game. This method is calling to itself if the player wants to
     * play more.
     */
    public void playGame() {

        // PLAYER BETS.
        playerBets();

        // GAME HEATER.
        printGameHeader();

        // FIRST DEAL.
        firstDeal();

        // SIDE RULES.
        sideRules();

        // PLAYERS TURN.
        final boolean playerIsSafe = playersTurn();

        // DEALERS TURN.
        boolean dealerIsSafe = true;
        // If player already lost, no point for dealer to play.
        if (playerIsSafe == true) {
            dealerIsSafe = dealersTurn();
        }

        // RESULTS.
        calculateResults(playerIsSafe, dealerIsSafe);

        // FINALISE GAME.
        finaliseGame();

    }

    /**
     * Player bets chips for this game.
     */
    private void playerBets() {
        System.out.println("-----------------------------------------");
        System.out.print("Player " + this.player.getName() + ", please make a bet: ");
        final String bet = this.scanner.nextLine();
        System.out.println("-----------------------------------------");
        this.player.setBet(Integer.parseInt(bet));

        // Print an empty line.
        System.out.println();
    }

    /**
     * Game banner. It displays the number of the game and the stas of the
     * player.
     */
    private void printGameHeader() {
        System.out.println("Game: " + this.gameCounter);
        this.player.printPlayerStats();

        // Print an empty line.
        System.out.println();
    }

    /**
     * First deal. The dealer gives 2 cards to the player and 2 hards to
     * himself.
     */
    private void firstDeal() {
        // First 2 cards
        System.out.println("-----------------------------------------");
        System.out.println("Dealer deals first 2 cards.");
        System.out.println("-----------------------------------------");

        // Print an empty line.
        System.out.println();

        // Deal two cards to player and two to dealer.
        for (int times = 0; times < 2; times++) {
            this.player.addCard(this.dealer.dealCard());
            this.dealer.addCard();
        }

        // Print dealers and players hands.
        this.dealer.printHand(false);
        this.player.printHand(true);

        // Print an empty line.
        System.out.println();
    }

    /**
     * These rules are enabled after the first deal. Player has more options:
     * "Double-Down" or "Insurance".
     */
    private void sideRules() {
        String sideRule;
        System.out.println("-----------------------------------------");
        System.out.println(this.player.getName() + ", ");
        System.out.println("Type 'DD' if you want to Double-Down, ");
        System.out.println("     'I'  if you want Insurance, ");
        System.out.print("     or just press Enter to continue: ");
        boolean invalid = true;

        do {
            sideRule = this.scanner.nextLine();
            final int dealersFaceUpCard = this.dealer.getCardsInHand().get(0).getValue();

            if (sideRule.equalsIgnoreCase("I") || sideRule.equalsIgnoreCase("DD") || sideRule.equals("")) {
                if (sideRule.equalsIgnoreCase("I") && dealersFaceUpCard != 1) {
                    System.out.println(
                            "Invalid insurance, dealer's face-up card is not an Ace. Please provide a valid one: ");
                    invalid = true;
                } else {
                    invalid = false;
                }
            } else {
                invalid = true;
                System.out.println("Invalid input " + sideRule + ". Please provide a valid one: ");
            }

        } while (invalid);

        System.out.println("-----------------------------------------");

        // Print an empty line.
        System.out.println();

        switch (sideRule.toUpperCase()) {
            case "DD":
                this.player.doubleBet();
                this.doubleDownFlag = true;

                // Print updated player stas.
                this.player.printPlayerStats();

                // Print an empty line.
                System.out.println();
                break;
            case "I":
                this.player.insuranceBet();
                this.insuranceFlag = true;

                // Print updated player stas.
                this.player.printPlayerStats();

                // Print an empty line.
                System.out.println();
                break;
            case "":
                break;
            default:
                break;
        }

    }

    /**
     * Player's turn. Player decides if he wants to "Hit" or "Stand".
     *
     * @return boolean value whether the player has chance to win:True or
     *         already lost:False.
     */
    public boolean playersTurn() {
        boolean playerIsSafe = true;
        String action;

        do {
            if (this.insuranceFlag == true) {
                System.out.println("-----------------------------------------");
                System.out.println("Insurance: You are not allowed to take another card.");
                System.out.println("-----------------------------------------");
                // Print an empty line.
                System.out.println();

                // Exit while loop.
                break;
            }

            System.out.println("-----------------------------------------");
            System.out.print(this.player.getName() + ", type 'H' if you want to Hit or 'S' if you want to Stand: ");
            action = this.scanner.nextLine();
            System.out.println("-----------------------------------------");

            // Print an empty line.
            System.out.println();

            if (action.equalsIgnoreCase("h")) {
                playerIsSafe = this.player.addCard(this.dealer.dealCard());
                this.player.printHand(true);

                // Print an empty line.
                System.out.println();

                if (this.doubleDownFlag == true) {
                    System.out.println("-----------------------------------------");
                    System.out.println("Double-Down: You are not allowed to take another card.");
                    System.out.println("-----------------------------------------");
                    // Print an empty line.
                    System.out.println();

                    // Reseting flag.
                    this.doubleDownFlag = false;

                    // Exit while loop.
                    break;
                }
            }

        } while ((playerIsSafe) && (action.equalsIgnoreCase("h")));

        if (playerIsSafe == false) {
            System.out.println("-----------------------------------------");
            System.out.println("Player Burnt: Sum = " + this.player.getHandSum());
            System.out.println("-----------------------------------------");

            // Print an empty line.
            System.out.println();
        }

        return playerIsSafe;
    }

    /**
     * Dealer's turn. Dealer plays "Hit" if his hand sum is below or equal than
     * 16, and "Stands" when it is great than 17.
     *
     * @return boolean value whether the dealer has chance to win:True or
     *         already lost:False.
     */
    private boolean dealersTurn() {
        boolean dealerIsSafe = true;
        if (this.insuranceFlag == true && this.dealer.getHandSum() == 21) {
            this.player.setChips(this.player.getChips() + this.player.getBet());
            this.player.setBet(0);
            this.insuranceFlag = false;
        } else {
            final int tempChips = this.player.getChips();
            this.player.setBet(this.player.getBet() / 3);
            this.player.setChips(tempChips);
            this.insuranceFlag = false;
        }

        while ((this.dealer.getHandSum() <= 16) && (dealerIsSafe)) {
            dealerIsSafe = this.dealer.addCard();
            this.dealer.printHand(true);
        }

        if (dealerIsSafe == false) {
            System.out.println("-----------------------------------------");
            System.out.println("Dealer Burnt: Sum = " + this.dealer.getHandSum());
            System.out.println("-----------------------------------------");

            // Print an empty line.
            System.out.println();
        }

        return dealerIsSafe;
    }

    /**
     * Calculates and displays the winner of this game.
     *
     * @param playerIsSafe boolean if player still has a chance to win.
     * @param dealerIsSafe boolean if dealer still has a chance to win.
     */
    private void calculateResults(final boolean playerIsSafe, final boolean dealerIsSafe) {
        final int chipsEarned;

        System.out.println("=================RESULTS=================");
        // Print an empty line.
        System.out.println();
        if (dealerIsSafe == false || (playerIsSafe == true
                && ((dealerIsSafe == true) && (this.player.getHandSum() > this.dealer.getHandSum())))) {
            chipsEarned = this.player.getBet() * 2;
            this.player.setChips(this.player.getChips() + chipsEarned);

            System.out.println("Player " + this.player.getName() + " wins!!");
        } else {
            System.out.println("Player " + this.player.getName() + " loses..");
        }

        // Print an empty line.
        System.out.println();
        System.out.println("---------------PLAYED CARDS--------------");
        this.dealer.printHand(true);
        this.player.printHand(true);
        System.out.println("--------------------SUMS-----------------");
        System.out.println("Dealer's Sum = " + this.dealer.getHandSum());
        System.out.println(this.player.getName() + "'s Sum = " + this.player.getHandSum());
        System.out.println("---------------PLAYER'S SCORE------------");
        this.player.printPlayerStats();
        System.out.println("=========================================");

        this.dealer.clearHand();
        this.player.clearHand();

        this.player.setBet(0);

        // Print an empty line.
        System.out.println();
    }

    /**
     * Player chooses if he wants to ontinue playing. Otherwise, the game is
     * over.
     *
     * @return boolean: True-game continues, False-game over.
     */
    private boolean keepPlaying() {
        // Prompt use if he wants to keep playing.
        System.out.println("-----------------------------------------");
        System.out.print("Player " + this.player.getName() + ", do you want to keep playing? (Type 'Y' or 'N'): ");
        final String keepPlaying = this.scanner.nextLine();
        System.out.println("-----------------------------------------");

        // Print an empty line.
        System.out.println();

        if (keepPlaying.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    /**
     * Player if he has not ran out of chips, he can choose if he wants to
     * continue playing. Otherwise, the game is over.
     */
    private void finaliseGame() {
        // Check if player is allowed and wants to keep playing.
        if (this.player.getChips() > 0 && keepPlaying()) {
            if (this.dealer.getDeck().checkShuffleNeeded()) {
                System.out.println("-----------------------------------------");
                System.out.println("Deck re-shuffled");
                System.out.println("-----------------------------------------");

                // Print an empty line.
                System.out.println();
            }
            this.gameCounter++;
            playGame();
        } else {
            if (this.player.getChips() <= 0) {
                System.out.print("Sorry, you ran out of chips. ");
            }

            System.out.println("Thanks for playing! Bye bye!");
            System.out.println();
            this.player.printPlayerStats();
            System.out.println("============================GAME OVER=================================");
        }
    }
}