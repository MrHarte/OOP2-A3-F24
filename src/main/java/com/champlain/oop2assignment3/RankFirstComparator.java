package com.champlain.oop2assignment3;

import java.util.Comparator;

public class RankFirstComparator implements Comparator<Card> {
    @Override
    public int compare(Card pCard1, Card pCard2) {
        int rankComparison = pCard1.getRank().compareTo(pCard2.getRank());

        if (rankComparison != 0) {
            return rankComparison;
        }
        return pCard1.getSuit().compareTo(pCard2.getSuit());
    }
}
