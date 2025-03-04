package ru.nsu.pereverzev;

public class Main {

    public static void main(String[] args) {
        Picery picery = new Picery(3, 3, 10);
        picery.run();
        for (int i = 0; i < 20; i++) {
            picery.queueDish(new Dish("pizza"));
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        picery.endOfDay();            

    }
}