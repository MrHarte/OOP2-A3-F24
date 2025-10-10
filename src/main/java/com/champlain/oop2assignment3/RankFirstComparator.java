package com.champlain.oop2assignment3;

import java.util.Comparator;

/**
 * Feature 1:
 * A comparator that sorts {@link Card} objects by their rank first, and by suit
 * when ranks are equal.
 * <p>
 * This class allows a poker dealer to easily organize cards based on their
 * rank priority (e.g., ACE, TWO, ... KING) and then by their suit order
 * (CLUBS, DIAMONDS, SPADES, HEARTS).
 * </p>
 *
 * @implNote This class is stateless and can be safely reused across the program.
 */
public class RankFirstComparator implements Comparator<Card>
{
    /**
     * Compares two {@link Card} objects.
     * <ul>
     *   <li>First compares their ranks using {@link Rank#compareTo(Rank)}.</li>
     *   <li>If ranks are equal, compares their suits using {@link Suit#compareTo(Suit)}.</li>
     * </ul>
     *
     * @param pCard1 the first card to compare
     * @param pCard2 the second card to compare
     * @return a negative integer if pCard1 is less than pCard2,
     *         zero if they are equal,
     *         or a positive integer if pCard1 is greater than pCard2
     */
    @Override
    public int compare(Card pCard1, Card pCard2)
    {
        int rankComparison = pCard1.getRank().compareTo(pCard2.getRank());

        if (rankComparison != 0)
        {
            return rankComparison;
        }
        return pCard1.getSuit().compareTo(pCard2.getSuit());
    }
}
