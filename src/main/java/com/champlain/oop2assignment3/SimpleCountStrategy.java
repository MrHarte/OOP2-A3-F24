package com.champlain.oop2assignment3;

/**
 * A scoring strategy that calculates the score of a hand by counting
 * the total number of cards.
 * <p>
 * Each card in the hand contributes one point to the score.
 * </p>
 *
 * @author Rohina
 * @see ScoringStrategy
 */

public class SimpleCountStrategy implements ScoringStrategy{

    /**
     * Calculates the score based on the number of cards in the hand.
     *
     * @param pCards the collection of cards to calculate the score for
     * @return the number of cards in {@code pCards}
     * @implNote This strategy simply iterates over the card collection
     * and counts each card; it does not depend on rank or suit.
     */
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
