package ru.nsu.pereverzev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String substr = "бра";
        try {
            ArrayList<Long> substids = Finder.find("testfile", substr);
            for (long id : substids) {
                System.out.println(id);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}