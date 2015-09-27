package main;

import java.util.Scanner;

import main.game.GameEngine;

/**
 * BlackJack Base Class.
 *
 * @author Nikos Pavlou
 */
public class BlackJack {

    /**
     * @param args command line arguments when run application: none.
     */
    public static void main(final String[] args) {
        final String playerName;
        String numOfDecks;
        String numOfChips;

        /**
         * START OF APPLICATION.
         */

        // Welcome message
        System.out.println("Welcome to BlackJack!");
        System.out.println("Press any Enter to continue...");
        try {
            System.in.read();
        } catch (final Exception e) {
        }
        System.out.println("Excelent!");

        // Scanner to read the command-line inputs.
        final Scanner scanner = new Scanner(System.in);

        // Prompt for the player's name.
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();

        // Prompt the user to set the number of decks.
        System.out.print("Enter the number of Decks, enter '0' for Default(4 decks): ");
        numOfDecks = scanner.nextLine();
        // Default is set to 4.
        if (numOfDecks.equals("")) {
            numOfDecks = "4";
        }
        System.out.println("Number of Decks: " + numOfDecks);

        // Prompt the user to set the number of chips to start with.
        System.out.print("Enter the number of Chips you want to buy, enter '0' for Default(100 chips): ");
        numOfChips = scanner.nextLine();
        // Default is set to 100.
        if (numOfChips.equals("")) {
            numOfChips = "100";
        }
        System.out.println("Initilal number of Chips: " + numOfChips);

        // Start the game message.
        System.out.println("Good Job! Lets start the game!");
        System.out.println("Press any Enter to continue...");
        try {
            System.in.read();
        } catch (final Exception e) {
        }

        /**
         * GAME STARTS HERE.
         */

        // An instance of the game.
        final GameEngine game = new GameEngine();

        // Initialise game.
        game.initialiseGame(playerName, Integer.parseInt(numOfDecks), Integer.parseInt(numOfChips));

    }
}