package fr.amisss.core.exchange.order;

public enum OrderType {

    /** Buying order (the trader is providing the counter currency) */
    BID,
    /** Selling order (the trader is providing the base currency) */
    ASK,
    /**
     * This is to close a short position when trading crypto currency derivatives such as swaps,
     * futures for CFD's.
     */
    EXIT_ASK,
    /**
     * This is to close a long position when trading crypto currency derivatives such as swaps,
     * futures for CFD's.
     */
    EXIT_BID;

    public OrderType getOpposite() {
        switch (this) {
            case BID:
                return ASK;
            case ASK:
                return BID;
            case EXIT_ASK:
                return EXIT_BID;
            case EXIT_BID:
                return EXIT_ASK;
            default:
                return null;
        }
    }
}
