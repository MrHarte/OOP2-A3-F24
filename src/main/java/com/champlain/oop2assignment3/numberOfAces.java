package com.champlain.oop2assignment3;
/**
 * A scoring strategy that counts the number of aces in a given hand.
 */
public class numberOfAces implements ScoringStrategy {

        /**
         * Calculates a score based on how many aces are in the given card collection.
         *
         * @param pCards the collection of cards to evaluate
         * @return the number of aces found in {@code pCards}
         */
        @Override
        public int calculateScore(CardCollection pCards) {
            int count = 0;
            for (Card card : pCards) {
                if (card.getRank() == Rank.ACE) {
                    count++;
                }
            }
            return count;
        }


}
