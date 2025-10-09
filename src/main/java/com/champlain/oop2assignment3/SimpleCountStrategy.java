package com.champlain.oop2assignment3;

public class SimpleCountStrategy implements ScoringStrategy{

    @Override
    public int calculateScore(CardCollection pCards) {
        if (pCards == null) {
            throw new NullPointerException("Card collection is null");
        }
        int score = 0;
        for (Card c : pCards) {
            score++;
        }
        return score;
    }
}
