/**
 *
 */
package main.game;

import main.model.Dealer;
import main.model.Deck;
import main.model.Player;

/**
 * Game Engine class.
 *
 * @author Nikos Pavlou
 */
public class GameEngine {

    // Player object.
    private Player player;

    // Dealer object.
    private Dealer dealer;

    // Deck object.
    private Deck deck;

    /**
     * Game Engine constructor.
     */
    public GameEngine() {
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
        this.dealer = new Dealer();
        this.deck = new Deck(numOfDecks);
    }
}
