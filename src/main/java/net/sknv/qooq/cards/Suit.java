package net.sknv.qooq.cards;

public enum Suit {

    SPADES      ("♠"),
    CLUBS       ("♣"),
    HEARTS      ("♥"),
    DIAMONDS    ("♦");

    private final String symbol;
    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}