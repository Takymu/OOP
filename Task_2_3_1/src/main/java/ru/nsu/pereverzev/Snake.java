package ru.nsu.pereverzev;

import javafx.util.Pair;

import java.util.ArrayList;

public class Snake {
    ArrayList<Pair<Integer, Integer>> body;
    Board curboard;
    Way way = Way.RIGHT;

    Snake(Board curboard) {
        this.body = new ArrayList<>();
        Pair<Integer, Integer> head = new Pair<>(1, 1);
        this.body.add(head);
        this.curboard = curboard;
    }

    public void setWay(Way way) {
        this.way = way;
    }

    public Way getWay() {
        return way;
    }

    public Pair<Integer, Integer> getHead() {
        return body.getFirst();
    }

    public Pair<Integer, Integer> getTail() {
        return this.body.getLast();
    }

    public void move(int width, int heigh) {
        Way way = getWay();
        Pair<Integer, Integer> head = body.getFirst();
        int headX = head.getValue();
        int headY = head.getKey();

        Pair<Integer, Integer> tail = getTail();
        int tailX = tail.getValue();
        int tailY = tail.getKey();

        for(int i = body.size() - 1; i > 0; i--) {
            body.set(i, body.get(i - 1));
        }
        width--;
        heigh--;
        if(way == Way.DOWN) {
            if(headY == heigh)
                headY = 0;
            else
                headY++;
        } else if(way == Way.UP) {
            if(headY == 0)
                headY = heigh;
            else
                headY--;
        } else if(way == Way.RIGHT) {
            if(headX == width)
                headX = 0;
            else
                headX++;
        } else if(way == Way.LEFT) {
            if(headX == 0)
                headX = width;
            else
                headX--;
        }
        Pair<Integer, Integer> newhead = new Pair<>(headY, headX);
        body.set(0, newhead);
        int id = curboard.isEat(newhead);
        if(id != -1) {
            body.add(new Pair<>(tailX, tailY));
            curboard.updateEat(id);
        }
    }

    public boolean isCollide() {
        for(int i = 1; i < body.size(); i++) {
            Pair<Integer, Integer> p = body.get(i);
            if(getHead().getKey() == p.getKey() &&
                getHead().getValue() == p.getValue()) {

                return true;
            }
        }
        return false;
    }

    public ArrayList<Pair<Integer, Integer>> getBody() {
        return this.body;
    }


}