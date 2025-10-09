package com.champlain.oop2assignment3;

public class NumberOfAcesStrategy implements ScoringStrategy {

    @Override
    public int calculateScore(CardCollection pCards) {
        if (pCards == null) {
            return 0;
        }

        int aceCount = 0;
        for (Card c : pCards) {
            if (c.getRank() == Rank.ACE) {
                aceCount++;
            }
        }
        return aceCount;
    }
}
