package com.champlain.oop2assignment3;

import java.util.Comparator;
import java.util.Objects;

/**
 * Sorts cards by suit first, then rank.
 * @implNote Uses enum order for Suit and Rank.
 * @since 1.0
 */
public final class SuitFirstComparator implements Comparator<Card> {
    @Override
    public int compare(Card a, Card b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        int bySuit = a.getSuit().compareTo(b.getSuit());
        if (bySuit != 0) return bySuit;

        return a.getRank().compareTo(b.getRank());
    }
}
