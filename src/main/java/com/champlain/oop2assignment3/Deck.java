package com.champlain.oop2assignment3;

import java.util.*;

/**
 * Represents a deck of playing cards.
 * <p>
 * This class allows for creating a standard deck, shuffling it, drawing cards,
 * and checking if the deck is empty.
 * </p>
 */
public class Deck extends CardCollection implements CardSource {
    /**
     * The list of cards in the deck.
     */
    private final List<Card> aCards = new ArrayList<>();

    /**
     * singleton instance of Deck
     */
    private static Deck aDeck = null;

    /**
     * Constructs a new Deck containing all standard playing cards.
     * The deck is initialized with one of each rank and suit combination.
     * Constructor set to private for Singleton implementation.
     */
    private Deck() {
        for (Rank currentRank : Rank.values()) {
            for (Suit currentSuit : Suit.values()) {
                this.aCards.add(new Card(currentRank, currentSuit));
            }
        }
    }

    /**
     * Method to return the single instance of Deck. If an instance of Deck does not exist
     * it is created. Otherwise, return the already existing Deck.
     * @return singleton instance of Deck
     * @implNote This method is not thread-safe. It's designed for use in single-threaded applications.
     */
    public static Deck getInstance() {
        if (aDeck == null) {
            aDeck = new Deck();
        }
        return aDeck;
    }

    /**
     * Shuffles the cards in this deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(this.aCards);
    }

    public Card draw() {
        int last = this.aCards.size()-1;
        Card myCard = this.aCards.get(last);
        this.aCards.remove(last);
        return myCard;
    }

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
     * Sorts the cards in the deck using the specified comparator.
     * 
     * <p>This method allows for different sorting strategies to be applied to the deck
     * by passing different comparators that define the desired ordering of cards.</p>
     * 
     * @param comparator the Comparator implementation that defines the sorting criteria
     */
    public void sort(Comparator<Card> comparator) {
        Collections.sort(this.aCards, comparator);
    }
}

