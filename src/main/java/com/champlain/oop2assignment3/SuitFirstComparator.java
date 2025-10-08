package com.champlain.oop2assignment3;

import java.util.Comparator;

public class SuitFirstComparator implements Comparator<Card> {
    @Override
    public int compare(Card pCard1, Card pCard2) {
        int suitComparison = pCard1.getSuit().compareTo(pCard2.getSuit());

        if (suitComparison == 0) {
            return suitComparison;
        }
        return pCard1.getRank().compareTo(pCard2.getRank());
    }
}
