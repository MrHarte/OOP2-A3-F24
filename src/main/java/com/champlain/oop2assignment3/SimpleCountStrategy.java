package com.champlain.oop2assignment3;
/**
 * <br>
 * <p>Represents the {@code scoring strategy} a user can choose</p>
 * <i>
 *  to display the {@code score based on the number of cards in a hand}.
 * </i>
 *
 *  @author Dieudonne B.
 */
public class SimpleCountStrategy implements ScoringStrategy{

    /**<p>
     ** Calculates the score of cards in the parameter {@code pCards}
     * </p>
     * @return the score of cards in a hand
     * */
    @Override
    public int calculateScore(CardCollection pCards) {
        int keepScore = 0;

        if (pCards != null) {
            for (Card ignored : pCards) {
                keepScore++;
            }
        }
        return keepScore;
    }
}