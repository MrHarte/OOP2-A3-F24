package com.champlain.oop2assignment3;

/**
 * Scoring strategy that counts the number of Aces in a hand.
 * <p>
 * Implements the {@link ScoringStrategy} interface.
 * </p>
 *
 * @author Rohina
 * @see ScoringStrategy
 */
public class NumberOfAcesStrategy implements ScoringStrategy {

    /**
     * Calculates the score for a given collection of cards.
     * <p>
     * The score is equal to the number of cards with rank {@link Rank#ACE}.
     * Returns 0 if the collection is null or empty.
     * </p>
     *
     * @param pCards The collection of cards to score.
     * @return The number of aces in the collection.
     */
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
