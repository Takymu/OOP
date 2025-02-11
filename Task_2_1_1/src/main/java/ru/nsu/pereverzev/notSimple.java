package ru.nsu.pereverzev;

public class notSimple {
    public static boolean check(int number) {
        if (number <= 1) {
            return true;
        }

        if (number == 2) {
            return false;
        }

        for (int i = 2; i * i <= number; i += 2) {
            if (number % i == 0) {
                return true;
            }
        }

        return false;
    }
}
