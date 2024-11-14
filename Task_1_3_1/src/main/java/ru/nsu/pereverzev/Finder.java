package ru.nsu.pereverzev;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * class for finding the substring.
 */
public class Finder {
    /**
     * main method, that is finding the substring.
     */
    public static ArrayList<Long> find(String filename, String target) throws IOException {
        int size = target.length() + 1;
        byte[] bufstr = target.getBytes(StandardCharsets.UTF_8);
        InfiniteArray arr = new InfiniteArray(size);
        InputStream inp = new FileInputStream(filename);
        ArrayList<Long> finded = new ArrayList<>();
        int byteRead = 0;
        long curFilePos = 0;
        long curStartPos = 0;
        long curReqstPos = 0;
        int curStrPos = 0;
        byte curByte = 0;
        int remBytesCurSym = 0;
        long cntSyms = 0;
        boolean byteNeed = true;

        while (true) {
            if (byteNeed) {
                byteRead = inp.read();
                if (byteRead == -1) {
                    break; // FILE ENDED
                }
                curByte = (byte) byteRead;
                if (getOctetNumber(curByte) > 0) {
                    cntSyms++;
                }
                curReqstPos++;
            }

            arr.set(curFilePos, curByte);

            if (curStrPos < bufstr.length && bufstr[curStrPos] == arr.get(curFilePos)) {
                curStrPos++;
                if (curStrPos == bufstr.length) {
                    finded.add(cntSyms - target.length()); // SUCCESS, WE FIND SOMETHING
                }
                curFilePos++;
            } else {
                curStrPos = 0;
                curStartPos++;
                curFilePos = curStartPos;
            }

            byteNeed = curFilePos == curReqstPos;
        }

        return finded;
    }

    /**
     * method for the knowing how much bytes will be next in UTF-8.
     */
    private static int getOctetNumber(byte curbyte) {
        if ((curbyte & 0b10000000) == 0) {
            return 1;
        } else if ((curbyte & 0b11100000) == 0b11000000) {
            return 2;
        } else if ((curbyte & 0xF0) == 0b11100000) {
            return 3;
        } else if ((curbyte & 0b11111000) == 0b11110000) {
            return 4;
        } else {
            return 0;
        }
    }
}
