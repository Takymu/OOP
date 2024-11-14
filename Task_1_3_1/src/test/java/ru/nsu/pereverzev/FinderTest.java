package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    void findTest1() {
        try {
            String filename = "tstfile.txt";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for (long i = 0; i < 20000; i++) {
                writer.print("aааааaааджaааааaваааиааааaввввввввцвкаааaааааaабра");
            }
            writer.close();
            ArrayList<Long> ls = Finder.find(filename, "бра");
            File toDelete = new File(filename);
            toDelete.delete();
            int id = 0;
            for (long i = 47; i < 20000 * 50 + 47; i+=50) {
                assertEquals(i, ls.get(id));
                id++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void findTest2() {
        try {
            String filename = "tstfile.txt";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print("абракадабра");
            writer.close();
            ArrayList<Long> ls = Finder.find(filename, "бра");
            File toDelete = new File(filename);
            toDelete.delete();
            assertEquals(1, ls.get(0));
            assertEquals(8, ls.get(1));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}