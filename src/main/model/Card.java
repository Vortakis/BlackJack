package main.model;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Card Model Class. Objects of this class represent a single card that exists
 * in a standard 52-card deck. It has a suit and a value.
 *
 * @author Nikos Pavlou
 */
public class Card {

    /**
     * Enum type Suit. Represents the type of suits that exist, associating them
     * with an int id.
     */
    public enum Suit {
        /** Hearts */
        HEARTS(0, "Hearts", '\u2764'),

        /** Spades */
        SPADES(1, "Spades", '\u2660'),

        /** Diamonds */
        DIAMONDS(2, "Diamonds", '\u2666'),

        /** Clubs */
        CLUBS(3, "Clubs", '\u2663');

        /** Suit id. */
        private final int id;

        /** Suit name. */
        private final String name;

        /** Suit symbol. */
        private final char symbol;

        /**
         * Constructor for enum type: Suit.
         *
         * @param id associated with a card suit.
         */
        Suit(final int id, final String name, final char symbol) {
            this.id = id;
            this.name = name;
            this.symbol = symbol;
        }

        /**
         * @return id of suit.
         */
        public int getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return this.name + " " + this.symbol;
        }
    }

    /** Suit field. */
    private final Suit suit;

    /** Value field. */
    private final int value;

    /**
     * Constructor for Card class objects.
     *
     * @param suit of this card.
     * @param value of this card.
     */
    public Card(final Suit suit, final int value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * @return the suit.
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * @return the value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return the value as string.
     */
    public String getValueAsString() {
        switch (this.value) {
            case 1:
                return "Ace(A)";
            case 11:
                return "Jack(J)";
            case 12:
                return "Queen(Q)";
            case 13:
                return "King(K)";
            default:
                if (this.value > 1 && this.value < 11) {
                    return Integer.toString(this.value);
                } else {
                    return "invalid";
                }
        }
    }

    @Override
    public boolean equals(final Object obj) {
        // If object is null return false.
        if (obj == null) {
            return false;
        }

        // If object is not of Card Class return false.
        if (!(obj instanceof Card)) {
            return false;
        }

        // Check if equals.
        final Card other = (Card) obj;
        return new EqualsBuilder().append(this.suit, other.getSuit()).append(this.value, other.getValue()).isEquals();
    }

    @Override
    public String toString() {
        // Example: "Ace(A) of Clubs <SUIT_SYMBOL>"
        return getValueAsString() + " of " + getSuit().toString();
    }
}