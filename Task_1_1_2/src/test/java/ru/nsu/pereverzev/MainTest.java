package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

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


    @Test
    void getCard() {
        Casino casino = new Casino();
        Card card = casino.getCard("player");
        assertEquals(card.getOwner(), "player");
        card = casino.getCard("diler");
        assertEquals(card.getOwner(), "diler");
    }

    @Test
    void getCardsList() {
        Player p1 = new Player();
        Card c1 = new Card(3, Suit.HEARTS, "default");
        c1.open();
        p1.addCardScore(c1);
        assertEquals(3, p1.getScore());
        Card c2 = new Card(9, Suit.SPADES, "default");
        c1.open();
        c2.open();
        p1.addCardScore(c2);
        assertEquals(12, p1.getScore());
        assertEquals("Тройка Черви (3), Девятка Пики (9)", p1.getCardsList());
        assertFalse(p1.isBlackjack());

        Player p2 = new Player();
        Card c3 = new Card(11, Suit.DMNDS, "ace");
        Card c4 = new Card(10, Suit.CLUBS, "king");
        c3.open();
        c4.open();
        p2.addCardScore(c3);
        p2.addCardScore(c4);
        assertEquals(p2.getCardsList(), "Туз Бубны (11), Король Трефы (10)");
        assertTrue(p2.isBlackjack());
    }

}