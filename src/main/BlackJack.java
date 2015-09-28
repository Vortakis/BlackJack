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
        String playerName;
        String numOfDecks = "";
        String numOfChips = "";

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

        // Skip "Enter" input.
        scanner.nextLine();

        // Prompt for the player's name.
        do {
            System.out.print("Enter your name: ");
            playerName = scanner.nextLine();
        } while (playerName.equals(""));

        // Print an empty line.
        System.out.println();

        // Prompt the user to set the number of decks.
        System.out.print("Enter the number of Decks, or press Enter for Default(4 decks): ");
        // Make sure Number of Decks Input gets a valid value.
        boolean invalid = true;
        do {
            try {
                numOfDecks = scanner.nextLine();

                Integer.parseInt(numOfDecks);
                invalid = false;
            } catch (final NumberFormatException nfe) {
                // Default is set to 4.
                if (numOfDecks.equals("")) {
                    numOfDecks = "4";
                    invalid = false;
                } else {
                    // If input was not numeric.
                    System.out.print("Invalid non-numeric value '" + numOfDecks + "'. Please provide a valid one: ");
                    invalid = true;
                }
            }
        } while (invalid);

        System.out.println("Number of Decks: " + numOfDecks);

        // Print an empty line.
        System.out.println();

        // Prompt the user to set the number of chips to start with.
        System.out.print("Enter the number of Chips you want to buy, or press Enter for Default(100 chips): ");
        // Make sure Chips Input gets a valid value.
        invalid = true;
        do {
            try {
                numOfChips = scanner.nextLine();

                Double.parseDouble(numOfChips);
                invalid = false;
            } catch (final NumberFormatException nfe) {
                // Default is set to 100.
                if (numOfChips.equals("")) {
                    numOfChips = "100";
                    invalid = false;
                } else {
                    // If input was not numeric.
                    System.out.print("Invalid non-numeric value '" + numOfChips + "'. Please provide a valid one: ");
                    invalid = true;
                }
            }
        } while (invalid);

        System.out.println("Initilal number of Chips: " + numOfChips);

        // Print an empty line.
        System.out.println();

        // Start the game message.
        System.out.println("Good Job! Lets start the game!");
        System.out.println("Press any Enter to continue...");
        try {
            System.in.read();
        } catch (final Exception e) {
        }

        // Skip "Enter" input.
        scanner.nextLine();

        // Print an empty line.
        System.out.println("=========================================");

        /**
         * GAME STARTS HERE.
         */

        // An instance of the game.
        final GameEngine game = new GameEngine();

        // Initialise game.
        game.initialiseGame(playerName, Integer.parseInt(numOfDecks), Double.parseDouble(numOfChips));

        game.playGame();
    }
}
