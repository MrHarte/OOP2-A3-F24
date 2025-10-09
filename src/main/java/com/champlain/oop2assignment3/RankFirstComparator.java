package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * A comparator that sorts cards primarily by rank and secondarily by suit.
 * 
 * <p>This comparator implements a sorting strategy where cards are first compared
 * by their rank. If two cards have the same rank, they are then compared by
 * their suit.</p>
 */
public class RankFirstComparator implements Comparator<Card> {
    /**
     * Compares two cards primarily by rank, then by suit if ranks are equal.
     * 
     * @param card1 the first card to compare
     * @param card2 the second card to compare
     * @return negative if card1 should come before card2,
     *         positive if card1 should come after card2,
     *         zero if they are equal in both rank and suit
     */
    @Override
    public int compare(Card card1, Card card2) {
        int rankCompare = card1.getRank().compareTo(card2.getRank());

        if (rankCompare == 0) {
            return card1.getSuit().compareTo(card2.getSuit());
        }
        return rankCompare;
    }
}
