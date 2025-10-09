package com.champlain.oop2assignment3;
/**
 * Defines a common interface for all card scoring strategies.
 * <p>
 * Implementations of this interface provide different ways to calculate
 * a score based on the cards in a given {@link CardCollection}. This design
 * allows for flexibility and easy substitution of various scoring algorithms
 * without changing the rest of the program logic.
 *  </p>
 *  */
public interface ScoringStrategy {
    int calculateScore(CardCollection pCards);
}
