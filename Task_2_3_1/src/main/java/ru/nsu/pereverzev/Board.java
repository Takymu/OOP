package ru.nsu.pereverzev;

//import ru.nsu.pereverzev.Pixel;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Board {
    int width;
    int height;
    int eatX;
    int eatY;
    int score;
    Pixel[][] screen;
    Snake snake;
    Random random;
    ArrayList <Pair<Integer, Integer>> eatArray;
    Board(int width, int height, int eatcnt, int score) {
        this.width = width;
        this.height = height;
        this.screen = new Pixel[width][height];
        this.eatArray = new ArrayList<>();
        this.score = score;
        this.random = new Random();
        int i = 0;
        while (i < eatcnt) {
            int eatX = abs(random.nextInt()) % width;
            int eatY = abs(random.nextInt()) % height;
            Pair<Integer, Integer> neweat = new Pair<>(eatY, eatX);
            if (isEat(neweat) == -1) {
                eatArray.add(neweat);
                i++;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int isEat(Pair<Integer, Integer> cord) {
        for(int i = 0; i < eatArray.size(); i++) {
            if(cord.getValue() == eatArray.get(i).getValue() &&
                cord.getKey() == eatArray.get(i).getKey()) {
                return i;
            }
        }
        return -1;
    }

    public void updateEat(int i) {
        int eatX = abs(random.nextInt()) % width;
        int eatY = abs(random.nextInt()) % height;
        Pair<Integer, Integer> eat = new Pair<>(eatY, eatX);
        while(isEat(eat) != -1) {
            eatX = abs(random.nextInt()) % width;
            eatY = abs(random.nextInt()) % height;
            eat = new Pair<>(eatY, eatX);
        }
        eatArray.set(i, eat);
    }

    public int getEatX() {
        return eatX;
    }

    public int getEatY() {
        return eatY;
    }

    void setSnake(Snake snake) {
        this.snake = snake;
    }

    Snake getSnake() {
        return this.snake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel getPixel(int x, int y) {
        return screen[x][y];
    }
}


