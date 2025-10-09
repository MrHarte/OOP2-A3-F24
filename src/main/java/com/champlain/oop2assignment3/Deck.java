package com.champlain.oop2assignment3;


import java.util.*;


/**
 * Represents a deck of playing cards.
 * <p>
 * This class allows for creating a single standard deck, shuffling it, drawing cards,
 * and checking if the deck is empty.
 * </p>
 */
public class Deck extends CardCollection implements CardSource {
    /**
     * The list of cards in the deck.
     */
    private final List<Card> aCards = new ArrayList<>();


    /**
     * Contains the sole instance of the {@link Deck} class
     */
    private static Deck aDeckInstance;

    /**
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
     * Gets the instance of {@link Deck} or creates one
     * @return {@code deckInstance}
     */
    public static Deck getInstance() {
        if (aDeckInstance == null) {
            aDeckInstance = new Deck();
        }
        return aDeckInstance;
    }

    /**
     * Shuffles the cards in this deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(this.aCards);
    }


    /**
     * Draws the last card from this deck (the last card is removed).
     *
     * @return The card that was drawn.
     */
    public Card draw() {
        int last = this.aCards.size() - 1;
        Card myCard = this.aCards.get(last);
        this.aCards.remove(last);
        return myCard;
    }


    /**
     * Checks if this Deck is empty.
     *
     * @return True if this deck is empty, false if not.
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
     * Sorts this Deck based according to the specified comparator object.
     *
     * @param pComparator The {@link Comparator} usd to determine the sorting of the deck.
     */
    public void sort(Comparator<Card> pComparator) {
        aCards.sort(pComparator);
    }
}
