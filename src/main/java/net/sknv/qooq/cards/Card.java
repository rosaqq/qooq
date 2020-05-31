package net.sknv.qooq.cards;

import java.util.UUID;
import java.util.function.Function;
/**
 * Represents a playing card and should only be generated from inside a {@link Deck}.
 * */
public class Card {

    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;


    private final UUID deckId;
    private final int cardId;
    private final Suit suit;
    private final String name;

    private final int value;

    /**
     * Constructs a new {@code Card}, associated with a specific Deck.
     *
     * @param id    Card ID in deck, from 0 to 51
     * @param deckId A UUID from the {@link Deck} this card belongs to.
     * @param score A function that returns the value of each card as per the game's rules. See {@link Deck} for an example.
     * */
    public Card(int id, UUID deckId, Function<Card, Integer> score) {

        if ( !(0 <= id && id < 52)) {
            throw new IllegalArgumentException("Card ID must be within [0, 51]");
        }
        // This prevents users from adding cards to decks they don't belong to
        this.deckId = deckId;
        // Get card suit
        suit = Suit.values()[id % 4 + 1];
        // Normalize ID to [1, 13] since we already know the suit of the card
        cardId = id % 13 + 1;
        name = generateName(id, suit);
        this.value = score.apply(this);
    }

    private String generateName(int value, Suit suit) {
        String name = "";

        // first take care of value
        switch (value) {
            case ACE:
                name += "A";
                break;
            case JACK:
                name += "J";
                break;
            case QUEEN:
                name += "Q";
                break;
            case KING:
                name += "K";
                break;
            default:
                name += value;
        }

        // now add the suit
        name += suit.getSymbol();
        return name;
    }

    /**
     * @return The card's Suit
     * */
    public Suit getSuit() {
        return suit;
    }
    /**
     * @return The card's value, as defined for each game.
     * */
    public int getValue() {
        return value;
    }
    /**
     * @return The card's ID. 1 = ACE; 11, 12, 13 = J, Q, K.
     * */
    public int getCardId() {
        return cardId;
    }
    /**
     * @return The card's unique deck ID.
     * */
    public UUID getDeckId() {
        return deckId;
    }
    @Override
    public String toString() {
        return name;
    }
}
