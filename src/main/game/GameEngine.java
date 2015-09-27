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

    // Game counter.
    private final int gameCounter = 1;

    // Player object.
    private Player player;

    // Dealer object.
    private Dealer dealer;

    // Scanner to read the command-line inputs.
    final Scanner scanner;

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

    public void playGame() {

        // Make a bet.

        System.out.print("Player " + this.player.getName() + ", please make a bet: ");
        final String bet = this.scanner.nextLine();
        this.player.setBet(Integer.parseInt(bet));

        // Print an empty line.
        System.out.println();

        // First 2 cards

        System.out.println("Game: " + this.gameCounter);
        this.player.printPlayerStats();

        for (int times = 0; times < 2; times++) {
            this.player.addCard(this.dealer.dealCard());
            this.dealer.addCard();
        }

        this.dealer.printHand(false);
        this.player.printHand(true);

        // Print an empty line.
        System.out.println();

        // Players Turn
        final boolean playerIsSafe = playersTurn();

        // Dealers Turn
        boolean dealerIsSafe = true;
        if (playerIsSafe == true) {
            dealerIsSafe = dealersTurn();
        }

        calculateResults(playerIsSafe, dealerIsSafe);

        if (this.player.getChips() > 0) {
            if (this.dealer.getDeck().checkShuffleNeeded()) {
                System.out.println("Deck re-shuffled");
            }
            playGame();
        }
    }

    public boolean playersTurn() {
        boolean playerIsSafe = true;
        String action;

        do {
            System.out.print(this.player.getName() + ", type 'H' if you want to Hit or 'S' if you want to Stand: ");
            action = this.scanner.nextLine();

            // Print an empty line.
            System.out.println();

            if (action.equalsIgnoreCase("h")) {
                playerIsSafe = this.player.addCard(this.dealer.dealCard());
                this.player.printHand(true);
            }
        } while ((playerIsSafe) && (action.equalsIgnoreCase("h")));

        if (playerIsSafe == false) {
            System.out.println("Burnt: Sum = " + this.player.getHandSum());
        }

        return playerIsSafe;
    }

    private boolean dealersTurn() {
        boolean dealerIsSafe = true;
        while ((this.dealer.getHandSum() <= 16) && (dealerIsSafe)) {
            dealerIsSafe = this.dealer.addCard();
            this.dealer.printHand(true);
        }

        if (dealerIsSafe == false) {
            System.out.println("Burnt: Sum = " + this.dealer.getHandSum());
        }

        return dealerIsSafe;
    }

    private void calculateResults(final boolean playerIsSafe, final boolean dealerIsSafe) {
        final int chipsEarned;
        if (playerIsSafe == false
                || ((dealerIsSafe == true) && (this.dealer.getHandSum() >= this.player.getHandSum()))) {
            chipsEarned = this.player.getBet() * 2;
            this.player.setChips(this.player.getChips() + chipsEarned);
        }

        this.dealer.clearHand();
        this.player.clearHand();

        // Print an empty line.
        System.out.println();

        this.player.printPlayerStats();

    }
}
