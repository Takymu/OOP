package ru.nsu.pereverzev;


/**
 * class emulates the behaviour of casino.
 */

public class Casino {
    Card[] bank;

    Casino() {
        bank = new Card[Constants.decksize];
        int size = Constants.decksize / 4; // count of cards in deck
        supportInitialize(Suit.CLUBS, 0);
        supportInitialize(Suit.SPADES, size);
        supportInitialize(Suit.CLUBS, size * 2);
        supportInitialize(Suit.CLUBS, size * 3);
    }

    private void supportInitialize(Suit suit, int start) {
        int curPrice = 2;
        for (int i = start; i < start + 9; i++) {
            bank[i] = new Card(curPrice, suit, "default");
            curPrice++;
        }
        bank[start + 9] = new Card(10, suit, "jack");
        bank[start + 10] = new Card(10, suit, "queen");
        bank[start + 11] = new Card(10, suit, "king");
        bank[start + 12] = new Card(11, suit, "ace");
    }

    /**
     * method used to getting yet unused random selected card from deck.
     */
    public Card getCard(String newOwner) {
        int casinCardCount = 0;
        for (int i = 0; i < Constants.decksize; i++) {
            if (bank[i].getOwner() == "casino") {
                casinCardCount++;
            }
        }
        int[] casin_ids = new int[casinCardCount + 1];
        int casinIdsId = 0;
        for (int i = 0; i < Constants.decksize; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_ids[casinIdsId] = i;
                casinIdsId++;
            }
        }
        long id = Math.round(Math.random() * (double) casinCardCount);
        bank[casin_ids[(int) id]].setOwner(newOwner);
        return bank[casin_ids[(int) id]];
    }
}
