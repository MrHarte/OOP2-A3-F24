package com.champlain.oop2assignment3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


/**
 * Controller class for managing the deck and hand of cards in the user interface.
 * <p>
 * This class handles user interactions with the deck, including shuffling,
 * sorting, scoring, and drawing cards. It updates the UI components to reflect
 * the current state of the deck and hand.
 * </p>
 */
public class DeckController {


    /**
     * TextArea for displaying the current state of the deck.
     */
    @FXML
    private TextArea aDeckTextArea;

    /**
     * TextArea for displaying the current hand of cards.
     */
    @FXML
    private TextArea aHandTextArea;

    /**
     * ChoiceBox for selecting the sorting strategy for the deck.
     */
    @FXML
    private ChoiceBox<String> aSortStrategyChoiceBox;

    /**
     * ChoiceBox for selecting the scoring strategy for the hand.
     */
    @FXML
    private ChoiceBox<String> aScoreStrategyChoiceBox;

    /**
     * Label for displaying the current score based on the selected scoring strategy.
     */
    @FXML
    private Label aScoreLabel;

    /**
     * The deck of cards being managed by this controller.
     */
    private final Deck aDeck = Deck.getInstance();

    /**
     * The hand of cards being managed by this controller.
     */
    private final Hand aHand = new Hand();

    /**
     * Initializes the controller and sets up the UI components.
     * This method is called after the FXML file has been loaded.
     * Tests the overridden equals method from Card class
     */
    public void initialize() {
        this.displayCardCollections();
        this.aSortStrategyChoiceBox.getItems().addAll("Rank First", "Suit First");
        this.aScoreStrategyChoiceBox.getItems().addAll("Simple Count", "Number Of Aces");

        // Create two identical cards
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.ACE, Suit.SPADES);

        // Create a different card
        Card card3 = new Card(Rank.KING, Suit.HEARTS);

        // Test equals
        boolean areEqual1 = card1.equals(card2);  // true
        boolean areEqual2 = card1.equals(card3);  // false

        // Print or log the results
        System.out.println("card1 equals card2: " + areEqual1);
        System.out.println("card1 equals card3: " + areEqual2);
    }

    /**
     * Handles the event when the shuffle button is clicked.
     * Shuffles the deck and updates the displayed card collections.
     */
    @FXML
    protected void onShuffleButtonClick() {
        this.aDeck.shuffle();
        this.displayCardCollections();
    }

    /**
     * Handles the event when the sort button is clicked.
     * Sorts the deck based on the selected sorting strategy from the choice box.
     * 
     * <p>The method performs the following actions:</p>
     * <ul>
     *   <li>Retrieves the selected sorting strategy from the choice box</li>
     *   <li>If no strategy is selected, displays an error alert to the user</li>
     *   <li>For "Rank First" strategy, sorts the deck using RankFirstComparator which prioritizes card ranks</li>
     *   <li>For "Suit First" strategy, sorts the deck using SuitFirstComparator which prioritizes card suits</li>
     *   <li>Updates the display to show the sorted deck</li>
     * </ul>
     * 
     * @throws Alert displays an error alert if no sorting strategy is selected
     */
    @FXML
    protected void onSortButtonClick() {
        String choice = this.aSortStrategyChoiceBox.getValue();
        if (choice == null) {
            Alert selectionErrorAlert = new Alert(Alert.AlertType.ERROR, "Please choose a sorting strategy first.");
            selectionErrorAlert.showAndWait();
        } else {
            switch (choice) {
                case "Rank First":
                    this.aDeck.sort(new RankFirstComparator());
                    break;
                case "Suit First":
                    this.aDeck.sort(new SuitFirstComparator());
                    break;
                default:
                    this.aDeckTextArea.setText("This should not happen! You messed up.");
                    break;
            }
            this.displayCardCollections();
        }
    }



    /**
     * Handles the event triggered when the "Score" button is clicked in the UI.
     * <p>
     * This method checks which scoring strategy is currently selected in the
     * {@code aScoreStrategyChoiceBox}. If no strategy is selected, it displays an
     * error alert prompting the user to make a selection. Otherwise, it applies
     * the corresponding scoring strategy to the player's hand ({@code aHand}) and
     * updates the score label ({@code aScoreLabel}) to show the result.
     * </p>
     *
     * <p>
     * Currently supported strategies include:
     * <ul>
     *   <li><b>Simple Count</b> — Uses {@link SimpleCountStrategy} to count all cards in the hand.</li>
     *   <li><b>Number Of Aces</b> — Uses {@link numberOfAces} to count only the aces in the hand.</li>
     * </ul>
     * </p>
     *
     * <p>
     * If an unexpected or unsupported value is encountered in the choice box,
     * a default message is displayed indicating that an error occurred.
     * </p>
     *
     * @FXML
     * This method is automatically called by the JavaFX framework when the user
     * clicks the "Score" button, as defined by the {@code fx:id} link in the FXML file.
     */
    @FXML
    protected void onScoreButtonClick() {
        String choice = this.aScoreStrategyChoiceBox.getValue();
        if (choice == null) {
            Alert selectionErrorAlert = new Alert(Alert.AlertType.ERROR, "Please choose a scoring strategy first.");
            selectionErrorAlert.showAndWait();
        } else {
            switch (choice) {
                case "Simple Count":

                    SimpleCountStrategy aStrategy = new SimpleCountStrategy();
                    int score = aStrategy.calculateScore(aHand);


                    this.aScoreLabel.setText("Simple count " + score);
                    break;
                case "Number Of Aces":
                    numberOfAces strategy = new numberOfAces();
                    int score2 = strategy.calculateScore(aHand);
                    this.aScoreLabel.setText("Number of aces " + score2);
                    break;
                default:
                    this.aScoreLabel.setText("This should not happen! You messed up.");
                    break;
            }
        }
    }

    /**
     * Handles the event when the draw button is clicked.
     * Draws a card from the deck and adds it to the player's hand.
     * Displays an alert if there are no more cards in the deck.
     */
    @FXML
    protected void onDrawButtonClick() {
        if (!this.aDeck.isEmpty()) {
            this.aHand.addCard(this.aDeck.draw());
        } else {
            Alert selectionErrorAlert = new Alert(Alert.AlertType.INFORMATION, "There are no more cards in the deck.");
            selectionErrorAlert.showAndWait();
        }
        this.displayCardCollections();
    }

    /**
     * Updates the text areas to display the current state of the deck and hand.
     */
    private void displayCardCollections() {
        this.aDeckTextArea.setText(this.aDeck.toString());
        this.aHandTextArea.setText(this.aHand.toString());
    }
}