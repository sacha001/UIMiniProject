package SketchApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static Model model;
    public static SketchView sketchView;
    public static SketchListView sketchListView;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Model();
        sketchView = new SketchView();
        sketchListView = new SketchListView(primaryStage);
        controller = new Controller();

        SplitPane splitPane = new SplitPane();

        splitPane.getItems().addAll(sketchListView, sketchView);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(splitPane, 1000, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
