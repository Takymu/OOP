package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

class PiceryTest {
    @Test
    public void pictest() {
        Config config;
        try {
            Gson gson = new Gson();
            config = gson.fromJson(new FileReader("config.json"), Config.class);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        Picery picery = new Picery(3, 3, 10, config);
        picery.run();
        for (int i = 0; i < 20; i++) {
            picery.queueDish(new Dish("pizza", i));
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        picery.endOfDay();
    }

}