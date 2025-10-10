package com.champlain.oop2assignment3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Controller class for managing the deck and hand of cards in the user interface.
 * <p>
 * This version is defensive: it attempts multiple strategies (reflection or direct
 * list sorting) so your RankFirstComparator and SuitFirstComparator will work
 * without changing other classes.
 * </p>
 */
public class DeckController {
    @FXML
    private TextArea aDeckTextArea;

    @FXML
    private TextArea aHandTextArea;

    @FXML
    private ChoiceBox<String> aSortStrategyChoiceBox;

    @FXML
    private ChoiceBox<String> aScoreStrategyChoiceBox;

    @FXML
    private Label aScoreLabel;

    private final Deck aDeck = new Deck();
    private final Hand aHand = new Hand();

    /**
     * Called by JavaFX after FXML load.
     */
    public void initialize() {
        // populate choices
        this.aSortStrategyChoiceBox.getItems().addAll("Rank First", "Suit First");
        this.aScoreStrategyChoiceBox.getItems().addAll("Simple Count", "Number Of Aces");

        // optional: pre-select first items
        if (!this.aSortStrategyChoiceBox.getItems().isEmpty()) {
            this.aSortStrategyChoiceBox.setValue(this.aSortStrategyChoiceBox.getItems().get(0));
        }
        if (!this.aScoreStrategyChoiceBox.getItems().isEmpty()) {
            this.aScoreStrategyChoiceBox.setValue(this.aScoreStrategyChoiceBox.getItems().get(0));
        }

        this.displayCardCollections();
    }

    @FXML
    protected void onShuffleButtonClick() {
        this.aDeck.shuffle();
        this.displayCardCollections();
    }

    @FXML
    protected void onSortButtonClick() {
        String choice = this.aSortStrategyChoiceBox.getValue();
        if (choice == null) {
            new Alert(Alert.AlertType.ERROR, "Please choose a sorting strategy first.").showAndWait();
            return;
        }

        Comparator<Card> comparator;
        switch (choice) {
            case "Rank First":
                comparator = new RankFirstComparator();
                break;
            case "Suit First":
                comparator = new SuitFirstComparator();
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Unknown sorting strategy: " + choice).showAndWait();
                return;
        }

        boolean sorted = sortDeckWithComparator(comparator);
        if (!sorted) {
            new Alert(Alert.AlertType.ERROR,
                    "Could not sort the deck. Please ensure your Deck class exposes a public " +
                            "getCards() that returns a List<Card>, or a public sort(Comparator<Card>) method, " +
                            "or an addCard(Card) / add(Card) method to rebuild the deck.").showAndWait();
        }

        this.displayCardCollections();
    }

    @FXML
    protected void onScoreButtonClick() {
        String choice = this.aScoreStrategyChoiceBox.getValue();
        if (choice == null) {
            new Alert(Alert.AlertType.ERROR, "Please choose a scoring strategy first.").showAndWait();
            return;
        }

        switch (choice) {
            case "Simple Count":
                int count = getHandCardCount();
                this.aScoreLabel.setText("Cards in hand: " + count);
                break;
            case "Number Of Aces":
                int aces = countAcesInHand();
                this.aScoreLabel.setText("Number of aces: " + aces);
                break;
            default:
                this.aScoreLabel.setText("This should not happen! You messed up.");
                break;
        }
    }

    @FXML
    protected void onDrawButtonClick() {
        if (!this.aDeck.isEmpty()) {
            this.aHand.addCard(this.aDeck.draw());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "There are no more cards in the deck.").showAndWait();
        }
        this.displayCardCollections();
    }

    /**
     * Refreshes the UI text areas.
     */
    private void displayCardCollections() {
        this.aDeckTextArea.setText(this.aDeck.toString());
        this.aHandTextArea.setText(this.aHand.toString());
    }

    /**
     * Attempts multiple strategies to sort the deck with the provided comparator.
     *
     * @param comparator comparator to use
     * @return true if sorting was applied successfully, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean sortDeckWithComparator(Comparator<Card> comparator) {
        // 1) Try Deck.sort(Comparator) via reflection
        try {
            Method sortMethod = this.aDeck.getClass().getMethod("sort", Comparator.class);
            sortMethod.invoke(this.aDeck, comparator);
            return true;
        } catch (NoSuchMethodException ignored) {
            // not present, try next approach
        } catch (Exception ex) {
            // reflection invocation error — continue to next approach
            ex.printStackTrace();
        }

        // 2) Try Deck.getCards() -> List<Card> and Collections.sort(list, comparator)
        try {
            Method getCardsMethod = this.aDeck.getClass().getMethod("getCards");
            Object listObj = getCardsMethod.invoke(this.aDeck);
            if (listObj instanceof List) {
                List<Card> cards = (List<Card>) listObj;
                Collections.sort(cards, comparator);
                return true;
            }
        } catch (NoSuchMethodException ignored) {
            // not present, try next approach
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 3) Fallback: pull all cards via draw(), sort them, then add back using any add-like method found
        try {
            Method isEmptyMethod = this.aDeck.getClass().getMethod("isEmpty");
            Method drawMethod = this.aDeck.getClass().getMethod("draw");

            List<Card> temp = new ArrayList<>();
            // draw until empty
            while (!(Boolean) isEmptyMethod.invoke(this.aDeck)) {
                Object c = drawMethod.invoke(this.aDeck);
                if (c instanceof Card) {
                    temp.add((Card) c);
                } else {
                    // draw returned something unexpected; abort fallback
                    break;
                }
            }

            // if nothing drawn, nothing to do
            if (temp.isEmpty()) {
                return true;
            }

            Collections.sort(temp, comparator);

            // find an add-like method that accepts a Card parameter
            Method addMethod = findAddCardMethod();
            if (addMethod != null) {
                for (Card card : temp) {
                    addMethod.invoke(this.aDeck, card);
                }
                return true;
            } else {
                // If we didn't find an add-method but getCards exists, try clearing that list and adding back
                try {
                    Method getCardsMethod2 = this.aDeck.getClass().getMethod("getCards");
                    Object listObj2 = getCardsMethod2.invoke(this.aDeck);
                    if (listObj2 instanceof List) {
                        List<Card> cards = (List<Card>) listObj2;
                        cards.clear();
                        cards.addAll(temp);
                        return true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (NoSuchMethodException nsme) {
            // draw/isEmpty missing — cannot do fallback
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // all strategies failed
        return false;
    }

    /**
     * Attempts to locate a method on Deck that can add a Card back into the deck.
     * Looks for method names containing add/push/offer/insert/put and with a single parameter
     * assignable from Card or Object.
     *
     * @return Method object or null if none found
     */
    private Method findAddCardMethod() {
        Method[] methods = this.aDeck.getClass().getMethods();
        for (Method m : methods) {
            Class<?>[] params = m.getParameterTypes();
            if (params.length == 1) {
                // If the parameter type can accept Card instances (e.g. Card or Object)
                if (params[0].isAssignableFrom(Card.class)) {
                    String name = m.getName().toLowerCase();
                    if (name.contains("add") || name.contains("push") || name.contains("offer")
                            || name.contains("insert") || name.contains("put")) {
                        return m;
                    }
                }
            }
        }

        // check declared methods too (in case of non-public)
        Method[] declared = this.aDeck.getClass().getDeclaredMethods();
        for (Method m : declared) {
            Class<?>[] params = m.getParameterTypes();
            if (params.length == 1) {
                if (params[0].isAssignableFrom(Card.class)) {
                    String name = m.getName().toLowerCase();
                    if (name.contains("add") || name.contains("push") || name.contains("offer")
                            || name.contains("insert") || name.contains("put") || name.contains("addcard")) {
                        m.setAccessible(true);
                        return m;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns a List<Card> representing the cards currently in the player's hand.
     * Tries to call Hand.getCards(); otherwise returns an empty list.
     */
    @SuppressWarnings("unchecked")
    private List<Card> getHandCards() {
        try {
            Method getCards = this.aHand.getClass().getMethod("getCards");
            Object listObj = getCards.invoke(this.aHand);
            if (listObj instanceof List) {
                return (List<Card>) listObj;
            }
        } catch (Exception ignored) {
        }
        // If we can't access the real list, attempt a best-effort parse of toString
        // (not ideal, but prevents crashes). Return empty list.
        return new ArrayList<>();
    }

    private int getHandCardCount() {
        List<Card> handCards = getHandCards();
        if (!handCards.isEmpty()) {
            return handCards.size();
        }
        // fallback: try size() or getSize()
        try {
            Method sizeM = this.aHand.getClass().getMethod("size");
            Object r = sizeM.invoke(this.aHand);
            if (r instanceof Integer) return (Integer) r;
        } catch (Exception ignored) {
        }
        try {
            Method sizeM = this.aHand.getClass().getMethod("getSize");
            Object r = sizeM.invoke(this.aHand);
            if (r instanceof Integer) return (Integer) r;
        } catch (Exception ignored) {
        }
        // last fallback: try parsing toString lines
        String s = this.aHand.toString();
        if (s == null || s.isEmpty()) return 0;
        String[] lines = s.split("\\r?\\n");
        int nonEmpty = 0;
        for (String line : lines) if (!line.trim().isEmpty()) nonEmpty++;
        return nonEmpty;
    }

    /**
     * Count aces in hand with best-effort approach.
     * Tries to compare to Rank.ACE, otherwise falls back to string equality.
     */
    private int countAcesInHand() {
        int count = 0;
        List<Card> handCards = getHandCards();
        if (!handCards.isEmpty()) {
            for (Card c : handCards) {
                try {
                    // safest: try enum comparison if Rank exists and has ACE
                    if (c.getRank() == Rank.ACE) {
                        count++;
                        continue;
                    }
                } catch (Throwable ignored) {
                }
                try {
                    if ("ACE".equalsIgnoreCase(String.valueOf(c.getRank()))) {
                        count++;
                    }
                } catch (Throwable ignored) {
                }
            }
            return count;
        }

        // fallback path: no getCards available — examine toString lines for "ACE"
        String s = this.aHand.toString();
        if (s == null || s.isEmpty()) return 0;
        String[] lines = s.split("\\r?\\n");
        for (String line : lines) {
            if (line.toUpperCase().contains("ACE")) count++;
        }
        return count;
    }
}
