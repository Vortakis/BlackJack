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

    /** Number of decks in a game. */
    private final int numOfDecks;

    /** List of all card in a deck. */
    private List<Card> gameDeck;

    /** List of used cards */
    private List<Card> cardsUsed;

    /** Initial number of cards in a game. */
    private int initNumOfCards;

    /** Cards consumed deck limit. */
    private double cardsConsumedLimit;

    /**
     * Constructor for Deck class objects.
     *
     * @param numOfDecks of this card.
     */
    public Deck(final int numOfDecks) {
        this.numOfDecks = numOfDecks;

        // Initialise deck.
        initialiseDeck();

        // Shuffle deck.
        shuffleDeck();
    }

    /**
     * Initialise a Deck for a new game.
     */
    private void initialiseDeck() {
        // Initialise game deck list.
        this.gameDeck = new ArrayList<>();

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

        // Calculate Cards consumed deck limit.
        this.cardsConsumedLimit = 0.75 * this.initNumOfCards;

        // Initialise cards used list.
        this.cardsUsed = new ArrayList<>();
    }

    /**
     * Method to shuffle deck.
     */
    private void shuffleDeck() {
        // Seed for the randomiser.
        final long seed = System.nanoTime();

        // Shuffle.
        Collections.shuffle(this.gameDeck, new Random(seed));
    }

    /**
     * A method that is called to check if shuffle is needed. The deck should be
     * re-shuffled after 75% of the deck has been consumed.
     *
     * @return boolean if deck has been re-shuffled.
     */
    public boolean checkShuffleNeeded() {
        // If number of cards used is more than 75% of the deck, re-shuffle.
        if (this.cardsUsed.size() >= this.cardsConsumedLimit) {
            // Add all used cards back in the deck.
            this.gameDeck.addAll(this.cardsUsed);

            // Clear cards used list.
            this.cardsUsed.clear();

            // Shuffle deck.
            shuffleDeck();

            return true;
        }
        return false;
    }

    /**
     * Deals a card.
     *
     * @return first card from the deck.
     */
    public Card dealCard() {
        // Add card to the used cards list.
        this.cardsUsed.add(this.gameDeck.get(0));

        // Remove and return the first card from the deck.
        return this.gameDeck.remove(0);
    }

    /**
     * Get the size of the deck at its current state.
     *
     * @return number of cards left in the deck.
     */
    public int getDeckSize() {
        return this.gameDeck.size();
    }
}