package ru.nsu.pereverzev;

import java.util.Scanner;

public class IntReader {
    static boolean readConsole = false;
    static String inp;
    static int cid;
    static Scanner console = new Scanner(System.in);
    static int lastreaded;

    public static void enableStringRead(String str) {
        readConsole = true;
        inp = str;
        cid = 0;
    }

    public static int getLastreaded() {
        return lastreaded;
    }

    public static int read() {
        if (readConsole) {
            lastreaded = inp.charAt(cid) - '0';
            cid++;
            return lastreaded;
        } else {
            lastreaded = console.nextInt();
            return lastreaded;
        }
    }
}
