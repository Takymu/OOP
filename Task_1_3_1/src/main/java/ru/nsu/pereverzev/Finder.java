package ru.nsu.pereverzev;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.InputStream;

public class Finder {
    public long[] find(String filename, String target) throws IOException {
        int size = target.length() + 1;
        byte[] bufstr = target.getBytes(StandardCharsets.UTF_8);
        InfiniteArray arr = new InfiniteArray(size);
        InputStream inp = new FileInputStream(filename);
        long[] finded = new long[2];
        int byteRead = 0;
        long curFilePos = 0;
        long curStartPos = 0;
        long curReqstPos = 0;
        int curStrPos = 0;
        byte curByte = 0;
        boolean byteNeed = true;

        while(true) {
            if(byteNeed) {
                byteRead = inp.read();
                if(byteRead == -1) {
                    break; // HANDLE NON SUCCESS
                }
                curByte = (byte)byteRead;
                curReqstPos++;
            }
            arr.set(curFilePos, curByte);
            if(bufstr[curStrPos] == arr.get(curFilePos)) {
                curStrPos++;
                if(curStrPos == bufstr.length) {
                    finded[0] = curStartPos;
                    finded[1] =
                    break; // HANDLE SUCCESS
                }
                curFilePos++;
            } else {
                curStrPos = 0;
                curStartPos++;
                curFilePos = curStartPos;
            }

            if(curFilePos == curReqstPos) {
                byteNeed = true;
            } else {
                byteNeed = false;
            }
        }

        return null;
    }
}
