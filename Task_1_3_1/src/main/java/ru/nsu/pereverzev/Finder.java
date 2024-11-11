package ru.nsu.pereverzev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.InputStream;

public class Finder {
    public ArrayList<Integer> find(String filename, String target) throws IOException {
        int size = target.length() * 100;
        byte[] bufstr = target.getBytes(StandardCharsets.UTF_8);
        InfiniteArray arr = new InfiniteArray(size * 2);
        InputStream inp = new FileInputStream(filename);
        int byteRead = -1;
        boolean finded = false;
        long startpos = 0;
        long curposfile = 0;
        int curposstr = 0;

        while ((byteRead = inp.read()) != -1) {
            //arr.set(curposfile, (byte)byteRead);
            byte byt = (byte)byteRead;
            startpos++;
            if(!finded) {
                if(byt == bufstr[0]) {
                    finded = true;
                } else {
                    continue;
                }
            }
            while(finded) {
                curposfile++;
                byt = (byte)
            }
        }
        return null;
    }
}
