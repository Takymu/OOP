package ru.nsu.pereverzev;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.timer.Timer;
import java.util.TimerTask;

public class Interactor extends Application {
    static Controller controller;
    static Viewer viewer;
    static int winWidth;
    static int winHeigh;


    public  Interactor setWinSize(int winWidtha, int winHeigha) {
        winWidth = winWidtha;
        winHeigh = winHeigha;
        return this;
    }

    public Interactor setController(Controller controllera) {
        controller = controllera;
        return this;
    }

    public Interactor setViewer(Viewer viewera) {
        viewer = viewera;
        return this;
    }

    @Override
    public void start(Stage primaryStage) {
        Parent frf;
        try {
            frf = FXMLLoader.load(getClass().getClassLoader().getResource("example.fxml"));
        }
        catch (Exception e) {
            System.err.println("no fxml file: ");
            System.err.println(e.getMessage());
            return;
        }

        Group root = new Group();

        Group rects = new Group();
        root.getChildren().add(frf);
        root.getChildren().add(rects);
        Scene scene = new Scene(root, winWidth, winHeigh);
        scene.setOnKeyPressed(event -> {
            controller.setSnakeWay(event.getCode().toString().charAt(0));
        });


        primaryStage.setScene(scene);

        Timeline timeline = getTimeline(primaryStage, rects);
        timeline.play();
    }

    private static Timeline getTimeline(Stage primaryStage, Group rects) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                event -> {
                    controller.moveSnake();
                    if(controller.isDefeat()) {
                        viewer.defeat();
                    }
                    if(controller.isWin()) {
                        viewer.win();
                    }
                    viewer.updateScene(rects, winWidth, winHeigh);
                    primaryStage.show();
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }

    public void launchApp() {
        launch();
    }
}
