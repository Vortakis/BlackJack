/**
 *
 */
package main.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Deck Model class.
 *
 * @author Nikos Pavlou
 */
public class DeckTest {

    /** Class under test. */
    private Deck deck;

    /** Sample number of decks. */
    private final int sampleNumOfDecks = 4;

    /** Standard size of a deck, excluding Joker. */
    private static final int STANDARD_DECK_SIZE = 52;

    /**
     * Execute before each test method.
     */
    @Before
    public void setUp() {
        this.deck = new Deck(this.sampleNumOfDecks);
    }

    /**
     * Tests {@link Deck#Deck(int)}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Deck object by calling its constructor.</li>
     * <li>Verify it is not null.</li>
     * </ul>
     */
    @Test
    public void testDeckConstructor() {
        // Verify object is created.
        Assert.assertNotNull(this.deck);

        // Calculate number of cards in the deck.
        final int expectedDeckSize = this.sampleNumOfDecks * STANDARD_DECK_SIZE;

        // Verify number of cards in deck.
        Assert.assertEquals(expectedDeckSize, this.deck.getDeckSize());
    }

    /**
     * Tests {@link Deck#checkShuffleNeeded()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Draw cards exactly equal to the 75% of the deck.</li>
     * <li>Call checkShuffleNeeded() method.
     * <li>Verify that the deck has been re-shuffled.</li>
     * </ul>
     */
    @Test
    public void testCheckShuffleNeeded_True() {
        // Calculate cards consumed limit.
        final double cardsConsumedLimit = 0.75 * this.deck.getDeckSize();

        // Draw cards until the deck reaches its limit.
        for (int index = 0; index < cardsConsumedLimit; index++) {
            this.deck.dealCard();
        }

        // Call method and verify that the deck has been re-shuffled.
        Assert.assertTrue(this.deck.checkShuffleNeeded());
    }

    /**
     * Tests {@link Deck#checkShuffleNeeded()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Draw 1 card.</li>
     * <li>Call checkShuffleNeeded() method.
     * <li>Verify that no re-shuffle was necessary.</li>
     * </ul>
     */
    @Test
    public void testCheckShuffleNeeded_False() {
        // Draw 1 card.
        this.deck.dealCard();

        // Call method and verify that there was no re-shuffle.
        Assert.assertFalse(this.deck.checkShuffleNeeded());
    }

    /**
     * Tests {@link Deck#dealCard()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Draw 1 card.</li>
     * <li>Call checkShuffleNeeded() method.
     * <li>Verify that no re-shuffle was necessary.</li>
     * </ul>
     */
    @Test
    public void testDealCard() {
        // Expected size.
        final int expectedDeckSize = this.deck.getDeckSize() - 1;

        // Draw 1 card.
        this.deck.dealCard();

        // Call method and verify that there was no re-shuffle.
        Assert.assertEquals(expectedDeckSize, this.deck.getDeckSize());
    }

    /**
     * Tests {@link Deck#dealCard()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Draw 1 card.</li>
     * <li>Call checkShuffleNeeded() method.
     * <li>Verify that no re-shuffle was necessary.</li>
     * </ul>
     */
    @Test
    public void testGetDeckSize() {
        // Expected size.
        final int expectedDeckSize = this.deck.getDeckSize();

        // Call method and verify that there was no re-shuffle.
        Assert.assertEquals(expectedDeckSize, this.deck.getDeckSize());
    }
}