package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * Comparator to sort cards by suit first, then by rank.
 * Enables dynamic sorting of a Deck by suit priority.
 */
public class SuitFirstComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        // Compare suits first
        int suitCompare = card1.getSuit().compareTo(card2.getSuit());

        // If suits are equal, compare ranks
        if (suitCompare == 0) {
            return card1.getRank().compareTo(card2.getRank());
        }

        return suitCompare;
    }
}
