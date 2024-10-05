package ru.nsu.pereverzev;

import java.io.IOException;
import java.io.OutputStream;

/**
 * class is abstraction for printing strings in any output stream.
 */
public class Output {
    static OutputStream os;

    public static void outSetStream(OutputStream stream) {
        os = stream;
    }

    public static void write(String str) throws IOException {
        os.write(str.getBytes());
    }

    public static void flush() throws IOException {
        os.flush();
    }

}
