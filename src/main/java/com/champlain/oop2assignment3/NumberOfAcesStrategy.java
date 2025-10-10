package com.champlain.oop2assignment3;

public class NumberOfAcesStrategy implements ScoringStrategy{

    /**
     * Returns a score corresponding to <b>the number of Aces in a hand</b>
     *
     * @return a score of type {@code int}.
     * */
    @Override
    public int calculateScore(CardCollection pCards) {
        int keepScore = 0;
        Card keepCards;
        if (pCards != null) {
            for (Card card : pCards) {
                keepCards = card;
                if (keepCards.getRank() == Rank.ACE) {
                    keepScore++;
                }
            }
        }
        return keepScore;
    }
}