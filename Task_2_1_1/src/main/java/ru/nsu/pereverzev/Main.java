package ru.nsu.pereverzev;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> lst = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replace('[',' ').replace(']', ' ');

        for (String number : input.split(",")) {
            number = number.replace(" ", "");
            lst.add(Integer.parseInt(number));
        }
        System.out.println(MultyThreadFind.find(lst, 16));
    }
}