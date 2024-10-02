package ru.nsu.pereverzev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class CardTest {

    @Test
    void getPrintName_open() {
        Card card = new Card(10, Suit.SPADES, "queen");
        assertEquals("<Закрытая карта>", card.getPrintName());
        card.open();
        assertEquals("Дама Пики (10)", card.getPrintName());
    }

    @Test
    void setgetOwner() {
        Card card = new Card(10, Suit.SPADES, "Queen");
        assertEquals(card.getOwner(), "casino");
        card.setOwner("player");
        assertEquals(card.getOwner(), "player");
    }


    @Test
    void getsetPrice() {
        Card card = new Card(10, Suit.SPADES, "default");
        assertEquals(card.getPrice(), 10);
        card.setPrice(1);
        assertEquals(card.getPrice(), 1);
    }

    @Test
    void getName() {
        Card card = new Card(10, Suit.SPADES, "Queen");
        assertEquals(card.getName(), "Queen");
    }

    @Test
    void getSuit() {
        Card card = new Card(10, Suit.CLUBS, "Queen");
        assertEquals(card.getSuit(), Suit.CLUBS);
    }


}