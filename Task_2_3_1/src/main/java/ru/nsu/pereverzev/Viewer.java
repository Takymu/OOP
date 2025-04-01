package ru.nsu.pereverzev;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

public class Viewer {
    private Board board;
    boolean end;

    Viewer(Board board) {
        this.board = board;
        end = false;
    }

    public void win() {
        end = true;
    }

    public void defeat() {
        end = true;
    }

    public void updateScene(Group rects, int wwidth, int wheigh) {
        if(end) {
            return;
        }
        int boardWidth = board.getWidth();
        int boardHeigh = board.getHeight();

        int wrec = wwidth / boardWidth;
        int hrec = wheigh / boardHeigh;

        rects.getChildren().clear();
        for(int x = 0; x < wwidth; x += wrec) {
            for(int y = 0; y < wheigh; y += hrec) {
                Rectangle rect = new Rectangle(x, y, wrec-2, hrec-2);
                rect.setFill(Color.WHITESMOKE);
                rects.getChildren().add(rect);
            }
        }

        ArrayList<Pair<Integer, Integer>> body = board.getSnake().getBody();
        for(Pair<Integer, Integer> coords : body) {
            int x = coords.getValue() * wrec;
            int y = coords.getKey() * hrec;
            Rectangle rect = new Rectangle(x, y, wrec - 2, hrec - 2);
            rect.setFill(Color.GREEN);
            rects.getChildren().add(rect);
        }
        int xhead = board.getSnake().getHead().getValue() * wrec;
        int yhead = board.getSnake().getHead().getKey() * hrec;
        Rectangle recthead = new Rectangle(xhead, yhead, wrec - 2, hrec - 2);
        recthead.setFill(Color.YELLOW);
        rects.getChildren().add(recthead);

        int xtail = board.getSnake().getTail().getValue() * wrec;
        int ytail = board.getSnake().getTail().getKey() * hrec;
        Rectangle rectail = new Rectangle(xtail, ytail, wrec - 2, hrec - 2);
        rectail.setFill(Color.BLUE);
        rects.getChildren().add(rectail);

        for(Pair<Integer, Integer> eat : board.eatArray) {
            int xeat = eat.getValue() * wrec;
            int yeat = eat.getKey() * hrec;
            Rectangle recteat = new Rectangle(xeat, yeat, wrec - 2, hrec - 2);
            recteat.setFill(Color.RED);
            rects.getChildren().add(recteat);
        }
    }
}
