package com.champlain.oop2assignment3;

import java.util.Comparator;

public class SuitFirstComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        int compareSuit = card2.getSuit().ordinal() - card1.getSuit().ordinal();

        if (compareSuit != 0) {
            return compareSuit;
        }
        return card2.getRank().ordinal() - card1.getRank().ordinal();
    }
}
