package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.model.Card.Suit;

/**
 * Deck Model Class. Objects of this class represent the deck used in a game. It
 * consists of Card objects and it might have multiple decks within one game.
 *
 * @author Nikos Pavlou
 */
public class Deck {

    /** List of all card in a deck. */
    private final List<Card> gameDeck = new ArrayList<>();

    /** Number of decks in a game. */
    private final int numOfDecks;

    /** Initial number of cards in a game. */
    private int initNumOfCards;

    /** Number of cards used */
    private int cardsUsed;

    /**
     * Constructor for Deck class objects.
     *
     * @param numOfDecks of this card.
     */
    public Deck(final int numOfDecks) {
        this.numOfDecks = numOfDecks;
        initialiseDeck();
    }

    /**
     * Initialise a Deck for a new game.
     */
    public void initialiseDeck() {
        // Current card.
        Card currentCard;

        // Loop through all decks/all suits/all cards in a deck, and add them in
        // this game deck.
        for (int deckIndex = 0; deckIndex < this.numOfDecks; deckIndex++) {
            for (int suitIndex = 0; suitIndex < 4; suitIndex++) {
                for (int cardIndex = 1; cardIndex < 14; cardIndex++) {
                    currentCard = new Card(Suit.values()[suitIndex], cardIndex);
                    this.gameDeck.add(currentCard);
                }
            }
        }

        // Total number of cards in this game deck.
        this.initNumOfCards = this.gameDeck.size();
        // Reset number of cards used.
        this.cardsUsed = 0;

        shuffleDeck();
    }

    /**
     * Method to shuffle deck.
     */
    public void shuffleDeck() {
        // Seed for the randomiser.
        final long seed = System.nanoTime();

        // Shuffle.
        Collections.shuffle(this.gameDeck, new Random(seed));
    }

    /**
     * A method that is called to check if shuffle is needed. The deck should be
     * re-shuffled after 75% of the deck has been consumed.
     */
    public void checkShuffleDeck() {

    }
}