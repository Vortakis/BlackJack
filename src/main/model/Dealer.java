package main.model;

/**
 * Dealer Class.
 *
 * @author Nikos Pavlou
 */
public class Dealer extends Hand {

    /** Deck of a game, hold by the dealer. */
    private Deck deck;

    /**
     * Dealer constructor method.
     *
     * @param numOfDecks the number of decks in the game.
     */
    public Dealer(final int numOfDecks) {
        super();
        this.deck = new Deck(numOfDecks);

        System.out.println("Dealer shuffled and placed " + numOfDecks + " in the 'shoe'.");
    }

    /**
     * @return the deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(final Deck deck) {
        this.deck = deck;
    }

    /**
     * Adds a card from the deck to the hand.
     *
     * @param card drawn from the deck.
     * @return boolean whether sum of the cards in hand is below or equal to 21.
     */
    public boolean addCard() {
        // Dealer adds a card.
        return super.addCard(this.deck.dealCard());
    }

    /**
     * @return deal one Card.
     */
    public Card dealCard() {
        return this.deck.dealCard();
    }

    /**
     * Prints Dealer's hand.
     */
    @Override
    public void printHand(final boolean showFirstCard) {
        System.out.println("Dealer");
        super.printHand(showFirstCard);
    }
}
