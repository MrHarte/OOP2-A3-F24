package com.champlain.oop2assignment3;

import java.util.Comparator;
import java.util.Objects;

/**
 * Sorts cards by rank first, then suit.
 * @implNote Uses enum order for Rank and Suit.
 * @since 1.0
 */
public final class RankFirstComparator implements Comparator<Card> {
    @Override
    public int compare(Card a, Card b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        int byRank = a.getRank().compareTo(b.getRank());
        if (byRank != 0) return byRank;

        return a.getSuit().compareTo(b.getSuit());
    }
}
