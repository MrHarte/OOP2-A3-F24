package com.champlain.oop2assignment3;

/**
 * <br>
 * <p>Represents the {@code scoring strategies} a user can choose from</p>
 * <i>
 *  to display the kind of table they are working at.
 * </i>
 *
 *  @author Dieudonne B.
 */

public interface ScoringStrategy{
    /**<p>
     ** Calculates the score of cards in the parameter {@code pCards}
     * </p>
     * @return the calculated score
     * */
    int calculateScore(CardCollection pCards);
}
