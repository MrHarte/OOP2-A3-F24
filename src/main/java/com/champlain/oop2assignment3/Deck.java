package com.champlain.oop2assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a deck of playing cards using the Singleton design pattern.
 * <p>
 * This class ensures that only one instance of the deck exists in the application,
 * preventing card duplication and potential cheating. The deck contains all standard
 * playing cards and supports operations such as shuffling, drawing cards, and
 * checking if the deck is empty.
 * </p>
 */
public class Deck extends CardCollection implements CardSource {
    /**
     * The single instance of the Deck class.
     * This instance is lazily initialized when {@link #getInstance()} is first called.
     */
    private static Deck instance = null;

    /**
     * The list of cards in the deck.
     */
    private final List<Card> aCards = new ArrayList<>();

    /**
     * Private constructor that creates a new Deck containing all standard playing cards.
     * <p>
     * The deck is initialized with one of each rank and suit combination (52 cards total).
     * This constructor is private to prevent external instantiation and enforce the
     * Singleton pattern.
     * </p>
     *
     * @see Rank
     * @see Suit
     */
    Deck() {
        for (Rank currentRank : Rank.values()) {
            for (Suit currentSuit : Suit.values()) {
                this.aCards.add(new Card(currentRank, currentSuit));
            }
        }
    }

    /**
     * Returns the single instance of the Deck class.
     * <p>
     * If the instance does not exist, it is created.
     * This guarantees that only one deck exists throughout the application,
     * preventing card duplication and cheating.
     * </p>
     *
     * @return the singleton instance of the Deck
     */
    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * Resets the deck instance to null, allowing a new deck to be created.
     * <p>
     * This method is intended for testing purposes to reset the singleton
     * state between test cases.
     * </p>
     *
     * @since 1.0
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Shuffles the cards in this deck randomly.
     * <p>
     * Uses {@link Collections#shuffle(List)} to randomize the order of cards.
     * </p>
     */
    public void shuffle() {
        Collections.shuffle(this.aCards);
    }

    /**
     * Draws and removes the top card from the deck.
     * <p>
     * The card at the end of the internal list is considered the "top" of the deck.
     * </p>
     *
     * @return the card drawn from the top of the deck
     * @throws IndexOutOfBoundsException if the deck is empty
     */
    public Card draw() {
        int last = this.aCards.size()-1;
        Card myCard = this.aCards.get(last);
        this.aCards.remove(last);
        return myCard;
    }

    /**
     * Checks whether the deck is empty.
     *
     * @return <code>true</code> if the deck contains no cards, <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return this.aCards.isEmpty();
    }

    /**
     * Returns an iterator over the cards in this deck.
     * <p>
     * The iterator allows traversal of all cards currently in the deck.
     * Modifications to the deck during iteration may result in
     * {@link java.util.ConcurrentModificationException}.
     * </p>
     *
     * @return an iterator for the cards in this deck
     */
    public Iterator<Card> iterator() {
        return this.aCards.iterator();
    }
}