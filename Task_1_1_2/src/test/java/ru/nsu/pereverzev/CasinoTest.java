package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasinoTest {

    @Test
    void getCard() {
        Casino casino = new Casino();
        Card card = casino.getCard("player");
        assertEquals(card.getOwner(), "player");
        card = casino.getCard("diler");
        assertEquals(card.getOwner(), "diler");
    }
}