package main.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.model.Card.Suit;

/**
 * Test class for Card Model class.
 *
 * @author Nikos Pavlou
 */
public class CardTest {

    /** Class under test. */
    private Card card;

    /** Sample suit. */
    private Suit sampleSuit = Suit.HEARTS;

    /** Sample value. */
    private final int sampleValue = 1;

    /**
     * Execute before each test method.
     */
    @Before
    public void setUp() {
        this.card = new Card(this.sampleSuit, this.sampleValue);
    }

    /**
     * Tests {@link Suit#getId()}, {@link Suit#toString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Suit enum object of type "HEARTS".</li>
     * <li>Verify getId() returns expected id.</li>
     * <li>Verify toString() returns expected string.</li>
     * </ul>
     */
    @Test
    public void testSuitHearts() {
        // Expected values.
        final int expectedId = 0;
        final String expectedString = "Hearts \u2764";

        // Assign enum type.
        this.sampleSuit = Suit.HEARTS;

        // Verify values.
        Assert.assertEquals(expectedId, this.sampleSuit.getId());
        Assert.assertEquals(expectedString, this.sampleSuit.toString());
    }

    /**
     * Tests {@link Suit#getId()}, {@link Suit#toString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Suit enum object of type "SPADES".</li>
     * <li>Verify getId() returns expected id.</li>
     * <li>Verify toString() returns expected string.</li>
     * </ul>
     */
    @Test
    public void testSuitSpades() {
        // Expected values.
        final int expectedId = 1;
        final String expectedString = "Spades \u2660";

        // Assign enum type.
        this.sampleSuit = Suit.SPADES;

        // Verify values.
        Assert.assertEquals(expectedId, this.sampleSuit.getId());
        Assert.assertEquals(expectedString, this.sampleSuit.toString());
    }

    /**
     * Tests {@link Suit#getId()}, {@link Suit#toString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Suit enum object of type "DIAMONDS".</li>
     * <li>Verify getId() returns expected id.</li>
     * <li>Verify toString() returns expected string.</li>
     * </ul>
     */
    @Test
    public void testSuitDiamonds() {
        // Expected values.
        final int expectedId = 2;
        final String expectedString = "Diamonds \u2666";

        // Assign enum type.
        this.sampleSuit = Suit.DIAMONDS;

        // Verify values.
        Assert.assertEquals(expectedId, this.sampleSuit.getId());
        Assert.assertEquals(expectedString, this.sampleSuit.toString());
    }

    /**
     * Tests {@link Suit#getId()}, {@link Suit#toString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Suit enum object of type "CLUBS".</li>
     * <li>Verify getId() returns expected id.</li>
     * <li>Verify toString() returns expected string.</li>
     * </ul>
     */
    @Test
    public void testSuitClubs() {
        // Expected values.
        final int expectedId = 3;
        final String expectedString = "Clubs \u2663";

        // Assign enum type.
        this.sampleSuit = Suit.CLUBS;

        // Verify values.
        Assert.assertEquals(expectedId, this.sampleSuit.getId());
        Assert.assertEquals(expectedString, this.sampleSuit.toString());
    }

    /**
     * Tests {@link Card#Card(Suit, int)}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object by calling its constructor.</li>
     * <li>Verify it is not null.</li>
     * </ul>
     */
    @Test
    public void testCardConstructor() {
        // Verify object is created.
        Assert.assertNotNull(this.card);
    }

    /**
     * Tests {@link Card#getSuit()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object.</li>
     * <li>Verify getSuit() returns correct suit.</li>
     * </ul>
     */
    @Test
    public void testGetSuit() {
        // Verify value is as expected.
        Assert.assertEquals(this.sampleSuit, this.card.getSuit());
    }

    /**
     * Tests {@link Card#getValue()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object.</li>
     * <li>Verify getValue() returns correct value.</li>
     * </ul>
     */
    @Test
    public void testGetValue() {
        // Verify value.
        Assert.assertEquals(this.sampleValue, this.card.getValue());
    }

    /**
     * Tests {@link Card#getValueAsString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object.</li>
     * <li>Assign various card values.</li>
     * <li>Verify getValueAsString() returns correct value.</li>
     * </ul>
     */
    @Test
    public void testGetValueAsString() {
        // Test Ace(A).
        String expectedValue = "Ace(A)";

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());

        // Test Jack(J).
        this.card = new Card(Suit.CLUBS, 11);
        expectedValue = "Jack(J)";

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());

        // Test Queen(Q).
        this.card = new Card(Suit.CLUBS, 12);
        expectedValue = "Queen(Q)";

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());

        // Test King(K).
        this.card = new Card(Suit.CLUBS, 13);
        expectedValue = "King(K)";

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());

        // Test numeric card values.
        this.card = new Card(Suit.CLUBS, 7);
        expectedValue = "7";

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());
    }

    /**
     * Tests {@link Card#getValueAsString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object with invalid value.</li>
     * <li>Verify getValueAsString() returns "invalid".</li>
     * </ul>
     */
    @Test
    public void testGetValueAsString_InvalidValue() {
        // Expected value.
        final String expectedValue = "invalid";

        this.card = new Card(Suit.CLUBS, 14);

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());
    }

    /**
     * Tests {@link Card#getValueAsString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object with invalid below 1 value.</li>
     * <li>Verify getValueAsString() returns "invalid".</li>
     * </ul>
     */
    @Test
    public void testGetValueAsString_InvalidNegativeValue() {
        // Expected value.
        final String expectedValue = "invalid";

        this.card = new Card(Suit.CLUBS, 0);

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.getValueAsString());
    }

    /**
     * Tests {@link Card#equals(Object)}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create two identical Card objects.</li>
     * <li>Verify equals() returns true.</li>
     * </ul>
     */
    @Test
    public void testEquals() {
        final Card object = new Card(Suit.HEARTS, 1);

        // Verify value.
        Assert.assertTrue(this.card.equals(object));
    }

    /**
     * Tests {@link Card#equals(Object)}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create Card object null.</li>
     * <li>Verify equals() returns false.</li>
     * </ul>
     */
    @Test
    public void testEquals_null() {
        final Card object = null;

        // Verify value.
        Assert.assertFalse(this.card.equals(object));
    }

    /**
     * Tests {@link Card#equals(Object)}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create invalid object of type int.</li>
     * <li>Verify equals() returns false.</li>
     * </ul>
     */
    @Test
    public void testEquals_WrongClass() {
        final int object = 1;

        // Verify value.
        Assert.assertFalse(this.card.equals(object));
    }

    /**
     * Tests {@link Card#toString()}.
     *
     * Test Objectives:
     * <ul>
     * <li>Create sample Card object.</li>
     * <li>Verify toString() returns correct string.</li>
     * </ul>
     */
    @Test
    public void testToString() {
        // Expected value.
        final String expectedValue = "Ace(A) of Hearts \u2764";

        // Verify value.
        Assert.assertEquals(expectedValue, this.card.toString());
    }
}