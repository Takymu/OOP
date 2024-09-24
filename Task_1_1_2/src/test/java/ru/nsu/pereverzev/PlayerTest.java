package ru.nsu.pereverzev;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class PlayerTest {

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