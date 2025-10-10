package com.champlain.oop2assignment3;

public class testFeature4 {
    /**
     * Tests the Singleton pattern for the Deck class.
     * Creates two Deck instances and checks if they refer to the same object.
     */
    public void testSingleton() {
        Deck deck1 = Deck.getInstance();
        Deck deck2 = Deck.getInstance();
        if (deck1 == deck2) {
            System.out.println("PASS: Both references point to the same instance.");
        } else {
            System.out.println("FAIL: Different instances detected.");
        }
    }

    /**
     * Tests the equals() method for the Card class.
     * Compares three cards for equality and prints the results.
     */
    public void testEquals() {
        Card card1 = new Card(Rank.QUEEN, Suit.CLUBS);
        Card card2 = new Card(Rank.QUEEN, Suit.CLUBS);
        Card card3 = new Card(Rank.ACE, Suit.DIAMONDS);
        System.out.println("card1 equals card2: " + card1.equals(card2)); // should print true
        System.out.println("card1 equals card3: " + card1.equals(card3)); // should print false
    }

    /**
     * Main method to run the tests.
     */
    public static void main(String[] args) {
        testFeature4 tester = new testFeature4();
        tester.testSingleton();
        tester.testEquals();
    }
}
