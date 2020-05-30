package net.sknv.qooq.cards;

import java.util.*;
import java.util.function.Function;

/** Represents an actual, physical card deck.
 *  Features include:
 *  <ul>
 *      <li>Standard 52 card deck.</li>
 *      <li>Shuffle method.</li>
 *      <li>Cards drawn are removed from the deck so odds as real as possible.</li>
 *      <li>Only allows you put back cards that came from the same deck.</li>
 *      <li>Reset method to put back all drawn cards and re-shuffle deck.</li>
 *  </ul>
 *
 * Constructor requires a {@link Function} to define the value of each card as per the game's rules.
 * <p>
 *     Usage example, constructing a deck for blackjack:
 * </p>
 * <pre>
 *  Deck deck1 = new Deck("blackjack", card -> {
 *      int id = card.getCardId();
 *      if (id == 11 || id == 12 || id == 13) return 10;
 *      return id;
 *  });
 * </pre>
 * Each {@code Card} only supports one value.
 * <br>
 * In the example above, J, Q, and K are set with value 10. The ACE is set with value 1 and the double value functionality
 * will have to be configured on the {@code Blackjack} class.
 *
 * @see Card
 */
public class Deck {

    private final String name;
    private final UUID deckId;
    private int cardsRemaining;

    /*
    * LinkedList used here because we want:
    *   - add() -> O(1), puts at the end;
    *   - poll() -> O(1), takes from the start;
    * */
    private final LinkedList<Card> cards = new LinkedList<>();
    private final LinkedList<Card> drawn = new LinkedList<>();

    /**
     * Constructs a new deck.
     *
     * @param name  A name for this deck
     * @param score A {@link Function} that consumes a {@link Card} and returns it's value as per your game's rules.
     * */
    public Deck(String name, Function<Card,Integer> score) {
        this.deckId = UUID.randomUUID();
        this.name = name;
        for (int i = 0; i<52; i++) {
            cards.add(new Card(i, deckId, score));
        }
        updateStats();
        shuffle();
    }

    /**
     * Draw one Card from the top of the Deck.
     * @return A Card object.
     * */
    public Card drawCardAndKeep() throws NoSuchElementException {
        if (cards.poll() == null) throw new NoSuchElementException("All cards were drawn!");
        Card draw = cards.poll();
        drawn.add(draw);
        updateStats();
        return draw;
    }

    /**
     * Draw one Card from the top of the Deck and put it back on the bottom.
     * @return A Card object.
     * */
    public Card drawCardAndPutBack() throws NoSuchElementException {
        if (cards.poll() == null) throw new NoSuchElementException("All cards were drawn!");
        Card draw = cards.poll();
        cards.add(draw);
        return draw;
    }

    /**
     * Shuffles the deck
     * */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Puts all cards back in the deck and shuffles it.
     * */
    public void resetDeck() {
        cards.addAll(drawn);
        updateStats();
        shuffle();
    }

    private void updateStats() {
        cardsRemaining = cards.size();
    }
    /**
     * @return Number of cards remaining in this deck.
     * */
    public int getCardsRemaining() {
        return cardsRemaining;
    }
    /**
     * @return Number of cards that have already been drawn.
     * */
    public int getCardsDrawn() {
        return 52-cardsRemaining;
    }
    /**
     * @param card  The card you want to put back in.
     * @throws IllegalArgumentException If you try to add a card that did not come from this deck.
     * */
    public void returnCardToDeck(Card card) throws IllegalArgumentException {
        if (card.getDeckId().compareTo(deckId) != 0) throw new IllegalArgumentException("The card you're trying to add doesn't belong to this deck!");
        drawn.remove(card);
        cards.add(card);
    }

    @Override
    public String toString() {
        return name;
    }
}
