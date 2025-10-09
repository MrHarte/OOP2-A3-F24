package com.champlain.oop2assignment3;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link SimpleCountStrategy}.
 * <p>
 * These tests verify that the strategy correctly counts the number of cards
 * in a hand and handles empty or null hands properly.
 * </p>
 *
 * @author Rohina
 * @see SimpleCountStrategy
 */
public class TestSimpleCountStrategy {

    /**
     * Tests SimpleCountStrategy with a normal hand containing 3 cards.
     */
    @Test
    public void testCalculateScore_normalHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        hand.addCard(new Card(Rank.THREE, Suit.CLUBS));

        ScoringStrategy strategy = new SimpleCountStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(3, score, "Score should be 3 for a hand with 3 cards");
    }

    /**
     * Tests SimpleCountStrategy with an empty hand.
     */
    @Test
    public void testCalculateScore_emptyHand() {
        Hand hand = new Hand();

        ScoringStrategy strategy = new SimpleCountStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(0, score, "Score should be 0 for an empty hand");
    }

    /**
     * Tests SimpleCountStrategy with a null hand.
     */
    @Test
    public void testCalculateScore_nullHand() {
        ScoringStrategy strategy = new SimpleCountStrategy();
        int score = strategy.calculateScore(null);

        assertEquals(0, score, "Score should be 0 for a null hand");
    }
}
