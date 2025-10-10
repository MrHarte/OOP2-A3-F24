package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * A {@link java.util.Comparator} class that implements the comparator interface for comparing Card objects.
 * <p>
 *     This comparator orders cards firstly by their rank in ascending order.
 * </p>
 * @implNote The comparison relies on the ordinal values of the {@code Rank} enum.
 *
 * @see java.util.Comparator
 */

public class RankFirstComparator implements Comparator<Card> {
    /**
     * Compares two @code Card objects.
     * @param card1 The first card to be compared.
     * @param card2 The second card to be compared.
     * @return A negative integer, zero or a positive integer as the first card is less
     * than, equal to, or greater than the second card.
     */
    @Override
    public int compare(Card card1, Card card2) {
        int compareRank = card1.getRank().ordinal() - card2.getRank().ordinal();

        if (compareRank != 0) {
            return compareRank;
        }
        return card1.getSuit().ordinal() - card2.getSuit().ordinal();
    }
}
