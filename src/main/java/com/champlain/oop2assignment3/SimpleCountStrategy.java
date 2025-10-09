package com.champlain.oop2assignment3;

public class SimpleCountStrategy implements ScoringStrategy{

    @Override
    public int calculateScore(CardCollection pCards) {
        if (pCards == null) {
            return 0;
        }
        int score = 0;
        for (Card c : pCards) {
            score++;
        }
        return score;
    }
}
