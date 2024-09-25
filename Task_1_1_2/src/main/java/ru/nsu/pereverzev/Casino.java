package ru.nsu.pereverzev;

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

    public Card getCard(String new_owner) {
        int casin_card_count = 0;
        for (int i = 0; i < Constants.decksize; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_card_count++;
            }
        }
        int[] casin_ids = new int[casin_card_count + 1];
        int casin_ids_i = 0;
        for (int i = 0; i < Constants.decksize; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_ids[casin_ids_i] = i;
                casin_ids_i++;
            }
        }
        long id = Math.round(Math.random() * (double) casin_card_count);
        bank[casin_ids[(int) id]].setOwner(new_owner);
        return bank[casin_ids[(int) id]];
    }
}
