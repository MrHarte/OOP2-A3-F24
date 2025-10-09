package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * A comparator that sorts cards primarily by suit and secondarily by rank.
 * 
 * <p>This comparator implements a sorting strategy where cards are first compared
 * by their suit. If two cards have the same suit, they are then compared by
 * their rank.</p>
 */
public class SuitFirstComparator implements Comparator<Card> {
    /**
     * Compares two cards primarily by suit, then by rank if suits are equal.
     * 
     * @param card1 the first card to compare
     * @param card2 the second card to compare
     * @return negative if card1 should come before card2,
     *         positive if card1 should come after card2,
     *         zero if they are equal in both suit and rank
     */
    @Override
    public int compare(Card card1, Card card2) {
        int suitCompare = card1.getSuit().compareTo(card2.getSuit());

        if (suitCompare == 0) {
            return card1.getSuit().compareTo(card2.getSuit());
        }
        return suitCompare;
    }
}
