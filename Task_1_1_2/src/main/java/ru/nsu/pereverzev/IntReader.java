package ru.nsu.pereverzev;

import java.util.Scanner;

/**
 * This class is used for switching input to text string while testing.
 * every read() call returns next number from input string, like nextInt() function
 */

public class IntReader {
    static boolean readConsole = false;
    static String inp;
    static int cid;
    static Scanner console = new Scanner(System.in);
    static int lastreaded;

    /**
     * method call switches input from terminal to string.
     *
     * @param str - string, that will be used for input emulation, must ends with "2"
     */

    public static void enableStringRead(String str) {
        readConsole = true;
        inp = str;
        cid = 0;
    }

    /**
     * returns last readed symbol, input buffer doesn't shift.
     */

    public static int getLastreaded() {
        return lastreaded;
    }

    /**
     * method emulate reading next one-number integer from terminal.
     *
     * @return int is returned, input buffer shift for one symbol
     */

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
