package com.champlain.oop2assignment3;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link NumberOfAcesStrategy}.
 * <p>
 * These tests verify that the strategy correctly counts the number of aces
 * in a hand and handles empty or null hands properly.
 * </p>
 *
 * @author Rohina
 * @see NumberOfAcesStrategy
 */
public class TestNumberOfAcesStrategy {

    /**
     * Tests NumberOfAcesStrategy with a normal hand containing some aces.
     */
    @Test
    public void testCalculateScore_normalHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS));

        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(score, 2, "Score should be 2 for a hand with 2 aces");
    }

    /**
     * Tests NumberOfAcesStrategy with a hand that has no aces.
     */
    @Test
    public void testCalculateScore_noAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand.addCard(new Card(Rank.THREE, Suit.DIAMONDS));

        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(score, 0, "Score should be 0 for a hand with no aces");
    }

    /**
     * Tests NumberOfAcesStrategy with an empty hand.
     */
    @Test
    public void testCalculateScore_emptyHand() {
        Hand hand = new Hand();

        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(score, 0, "Score should be 0 for an empty hand");
    }

    /**
     * Tests NumberOfAcesStrategy with a null hand.
     */
    @Test
    public void testCalculateScore_nullHand() {
        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(null);

        assertEquals(score, 0, "Score should be 0 for a null hand");
    }
}
