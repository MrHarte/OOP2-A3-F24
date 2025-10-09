package com.champlain.oop2assignment3;

/**
 * Represents a strategy for calculating the score of a hand of cards.
 * <p>
 * This interface allows the coupier to dynamically switch scoring strategies
 * depending on the type of table or game being played.
 * </p>
 *
 * @author Rohina
 * @see SimpleCountStrategy
 */

public interface ScoringStrategy {

    /**
     * Calculates the score for the given collection of cards.
     *
     * @param pCards the collection of cards to calculate the score for
     * @return the calculated score as an integer
     * @implNote Implementations may use different rules for scoring, e.g.,
     * counting all cards or counting only specific ranks.
     * @throws NullPointerException if {@code pCards} is null
     */
    int calculateScore(CardCollection pCards);
}
