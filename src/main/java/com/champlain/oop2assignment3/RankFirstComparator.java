package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * Implements the Comparator interface.
 * Compares two card objects by Rank then Suit if ranks are equal.
 */
public class RankFirstComparator implements Comparator<Card> {
    /**
     * Compares the two cards for the order.
     * @param pCard1 the first object to be compared.
     * @param pCard2 the second object to be compared.
     * @return a value that shows if the first card is smaller, equal to, or bigger
     * than the second card with negative, zero, or positive number.
     */
    @Override
    public int compare(Card pCard1, Card pCard2) {
        // Compares the rank and returns a result
        int rankComparison = pCard1.getRank().compareTo(pCard2.getRank());

        if (rankComparison != 0) {
            return rankComparison;
        }
        // Returns a compared Suits if Ranks are the same
        return pCard1.getSuit().compareTo(pCard2.getSuit());
    }
}
