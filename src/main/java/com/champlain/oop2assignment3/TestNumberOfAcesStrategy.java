package com.champlain.oop2assignment3;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestNumberOfAcesStrategy {

    @Test
    public void testCalculateScore_normalHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS));

        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(2, score, "Score should be 2 for a hand with 2 aces");
    }

    @Test
    public void testCalculateScore_noAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand.addCard(new Card(Rank.THREE, Suit.DIAMONDS));

        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(0, score, "Score should be 0 for a hand with no aces");
    }

    @Test
    public void testCalculateScore_emptyHand() {
        Hand hand = new Hand();

        ScoringStrategy strategy = new NumberOfAcesStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(score, 0, "Score should be 0 for an empty hand");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testCalculateScore_nullHand() {
        ScoringStrategy strategy = new NumberOfAcesStrategy();
        strategy.calculateScore(null); // Should throw NullPointerException
    }
}
