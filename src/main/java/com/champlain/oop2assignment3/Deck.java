package com.champlain.oop2assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a deck of playing cards.
 * <p>
 * This class allows for creating a standard deck, shuffling it, drawing cards,
 * and checking if the deck is empty.
 * </p>
 */
public class Deck extends CardCollection implements CardSource {
    /**
     * The single instance of the Deck (Singleton pattern).
     */
    private static Deck instance;

    /**
     * The list of cards in the deck.
     */
    private final List<Card> aCards = new ArrayList<>();

    /**
     * Private constructor to prevent external instantiation.
     * Constructs a new Deck containing all standard playing cards.
     * The deck is initialized with one of each rank and suit combination.
     */
    private Deck() {
        for (Rank currentRank : Rank.values()) {
            for (Suit currentSuit : Suit.values()) {
                this.aCards.add(new Card(currentRank, currentSuit));
            }
        }
    }

    /**
     * Returns the single instance of the Deck.
     * If the instance doesn't exist, it creates it (lazy initialization).
     * This method is thread-safe.
     *
     * @return the single Deck instance
     */
    public static synchronized Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * Shuffles the cards in this deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(this.aCards);
    }

    /**
     * Draws a card from the deck.
     * Removes and returns the last card in the deck.
     *
     * @return the card that was drawn from the deck, or null if the deck is empty
     */
    public Card draw() {
        if (this.aCards.isEmpty()) {
            return null;
        }
        int last = this.aCards.size()-1;
        Card myCard = this.aCards.get(last);
        this.aCards.remove(last);
        return myCard;
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if there are no cards left in the deck, false otherwise
     */
    public boolean isEmpty() {
        return this.aCards.isEmpty();
    }

    /**
     * Returns an iterator over the cards in this deck.
     *
     * @return an iterator for the cards
     */
    public Iterator<Card> iterator() {
        return this.aCards.iterator();
    }

    /**
     * Prevents cloning of the Singleton instance.
     * This ensures that only one deck can exist in the game.
     *
     * @throws CloneNotSupportedException always, to prevent cloning
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton Deck");
    }
}
