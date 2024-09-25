package ru.nsu.pereverzev;

import static ru.nsu.pereverzev.Main.play;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void maint() {
        IntReader.enableStringRead("00001110001010100101010010101101011111100100101010010101010001012");
        play();
    }
}