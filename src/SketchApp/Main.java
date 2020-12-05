package SketchApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SplitPane splitPane = new SplitPane();
        VBox left  = new VBox();
        Pane right = new SketchView();

        splitPane.getItems().addAll(left, right);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(splitPane, 1000, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
