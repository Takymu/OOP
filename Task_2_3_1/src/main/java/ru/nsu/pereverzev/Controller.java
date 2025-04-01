package ru.nsu.pereverzev;

public class Controller {
    Snake snake;
    Board board;
    boolean defeat;
    boolean win;
    Controller(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
        win = false;
        defeat = false;
    }

    public void moveSnake() {
        snake.move(board.getWidth(), board.getHeight());
        defeat = snake.isCollide();
        if(snake.getBody().size() == board.getScore()) {
            win = true;
        }
    }

    public boolean isDefeat() {
        return defeat;
    }

    public boolean isWin() {
        return win;
    }

    public void setSnakeWay(char key) {
        switch (key) {
            case 'A':
                snake.setWay(Way.LEFT);
                break;
            case 'W':
                snake.setWay(Way.UP);
                break;
            case 'S':
                snake.setWay(Way.DOWN);
                break;
            case 'D':
                snake.setWay(Way.RIGHT);
                break;
        }
    }
}
