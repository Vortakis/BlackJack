package main.model;

import java.util.ArrayList;

/**
 * Hand Model Class. Objects of this class represent the cards a player/dealer
 * holds in hand.
 *
 * @author Nikos Pavlou
 */
public class Hand {

    /** Cards in the hand of a player. */
    private final ArrayList<Card> hand;

    /**
     * Constructor for Hand Model Class.
     */
    public Hand() {
        this.hand = new ArrayList<>();
    }

    /**
     * @return list of cards that exist in the hand.
     */
    public ArrayList<Card> getCardsInHand() {
        return this.hand;
    }

    /**
     * Discards all the cards from the hand.
     */
    public void clearHand() {
        this.hand.clear();
    }

    /**
     * Adds a card from the deck to the hand.
     *
     * @param card drawn from the deck.
     * @return boolean whether sum of the cards in hand is below or equal to 21.
     */
    public boolean addCard(final Card card) {
        // Add card in the hand.
        this.hand.add(card);

        // If the sum of the card values is over 21, return false.
        if (getHandSum() > 21) {
            return false;
        }

        // Player can still play.
        return true;
    }

    /**
     * Calculate the sum of the card values in hand.
     *
     * @return int the sum.
     */
    public int getHandSum() {
        // Sum of card values.
        int sum = 0;

        // Number of Aces.
        int numOfAces = 0;

        // Card value.
        int cardValue;

        // Loop through the cards in the hand.
        for (int cardIndex = 0; cardIndex < this.hand.size(); cardIndex++) {
            // Get value of the card (1 to 13).
            cardValue = this.hand.get(cardIndex).getValue();

            if (cardValue == 1) {
                // If Ace then increase counter of aces and add 11 to the sum.
                numOfAces++;
                sum += 11;
            } else if (cardValue >= 10) {
                // If 10,J,Q,K, then add 10 to the sum.
                sum += 10;
            } else {
                // Otherwise add the card value to the sum.
                sum += cardValue;
            }
        }

        // If the sum exceeds 21 and there are aces in the player's hand.
        while ((numOfAces > 0) && (sum > 21)) {
            // Remove one Ace from the counter.
            numOfAces--;

            // Set Ace's value to 1(subtract 10 cause 11 has already been
            // added).
            sum -= 10;
        }

        // Return sum.
        return sum;
    }

    /**
     * Render the cards in the hand.
     *
     * @param showFirstCard boolean if this card is face-up(true) or
     *            face-down(false).
     */
    public void printHand(final boolean showFirstCard) {
        System.out.println("-----------------");
        // Loop through the cards in the hand.
        for (int cardIndex = 0; cardIndex < this.hand.size(); cardIndex++) {
            // If first card is hidden, don't display it.
            if (!showFirstCard && cardIndex == 1) {
                System.out.println("[FACE DOWN]");
            } else {
                System.out.println(this.hand.get(cardIndex).toString());
            }
        }
        System.out.println("-----------------");
    }

}