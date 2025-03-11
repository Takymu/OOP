package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.Gson;

class PiceryTest {
    @Test
    public void pictest() {
        Gson gson = new Gson();
        Picery picery = new Picery(3, 3, 10);
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