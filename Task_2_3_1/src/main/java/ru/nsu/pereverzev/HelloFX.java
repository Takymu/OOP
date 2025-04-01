package ru.nsu.pereverzev;


public class HelloFX {
    public static void main(String[] args) {
        Board board = new Board(35, 25, 10, 60);
        Snake snake = new Snake(board);
        board.setSnake(snake);
        Viewer viewer = new Viewer(board);
        Controller controller = new Controller(snake, board);
        Interactor interactor = new Interactor()
                .setController(controller)
                .setViewer(viewer)
                .setWinSize(700, 500);
        interactor.launchApp();
    }

}