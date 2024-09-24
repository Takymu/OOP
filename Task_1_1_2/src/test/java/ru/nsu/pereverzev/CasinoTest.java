package ru.nsu.pereverzev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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