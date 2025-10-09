package com.champlain.oop2assignment3;

import java.util.Comparator;

public class RankFirstComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        int compareRank = card2.getRank().ordinal() - card1.getRank().ordinal();

        if (compareRank != 0) {
            return compareRank;
        }
        return card2.getSuit().ordinal() - card1.getSuit().ordinal();
    }
}
