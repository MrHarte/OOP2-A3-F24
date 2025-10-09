package com.champlain.oop2assignment3;

/**
 * A simple implementation of the {@link ScoringStrategy} interface that
 * calculates a score based solely on the number of cards in a given collection.
 * <p>
 * This strategy does not take card ranks, suits, or values into account —
 * it simply returns the total count of cards. It can be used as a basic or
 * fallback scoring method.
 * </p>
 */
public class SimpleCountStrategy implements ScoringStrategy {
    @Override
    public int calculateScore(CardCollection pCards) {
        int Count = 0;
        for (Card card : pCards) {
            Count++;
        }
        return Count;
    }

}
