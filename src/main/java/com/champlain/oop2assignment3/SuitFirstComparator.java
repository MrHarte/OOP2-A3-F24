package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * Implements the Comparator interface.
 * Compares two card objects by Suit then Rank if suits are equal.
 */
public class SuitFirstComparator implements Comparator<Card> {
    /**
     * Compares the two cards for the order.
     * @param pCard1 the first object to be compared.
     * @param pCard2 the second object to be compared.
     * @return a value that shows if the first card is smaller, equal to, or bigger
     * than the second card with negative, zero, or positive number.
     */
    @Override
    public int compare(Card pCard1, Card pCard2) {
        // Compares the suits and return a result
        int suitComparison = pCard1.getSuit().compareTo(pCard2.getSuit());

        if (suitComparison == 0) {
            return suitComparison;
        }
        // Returns a compared Ranks if Suits are the same
        return pCard1.getRank().compareTo(pCard2.getRank());
    }
}
