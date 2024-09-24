package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.pereverzev.Main.play;

class MainTest {
    @Test
    void maint() {
        intReader.enableStringRead("00001110001010100102");
        play();
    }
}