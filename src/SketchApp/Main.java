package SketchApp;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    public static Model model;
    public static SketchView sketchView;
    public static SketchListView sketchListView;
    public static Controller controller;
    public static final Stage aboutStage = new Stage();
    public static final Stage helpStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Model();
        sketchView = new SketchView();
        sketchListView = new SketchListView(primaryStage);
        controller = new Controller();

        BorderPane root = new BorderPane();
        SplitPane splitPane = new SplitPane();

        //Init Menu Bar
        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");
        Menu help = new Menu("Help");
        MenuItem exit = new MenuItem("Exit");
        MenuItem about = new MenuItem("About");
        MenuItem helpItem = new MenuItem("Help");
        file.getItems().add(exit);
        help.getItems().addAll(about, helpItem);
        mb.getMenus().addAll(file, help);

        //splitPane.getItems().addAll(sketchListView, sketchView);
        root.setTop(mb);
        root.setLeft(sketchListView);
        root.setCenter(sketchView);

        primaryStage.setTitle("Ultimate Sketching App");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.setResizable(false);
        loadSplashScreen(primaryStage);

        //Event handler for exit menu item
        exit.setOnAction((event) -> {
            primaryStage.close();
            aboutStage.close();
            helpStage.close();
        });

        //Event handler for about menu item
        about.setOnAction((event) -> {
            VBox pane = new VBox();
            pane.setAlignment(Pos.TOP_CENTER);

            Label aboutLabel = new Label("\nGroup Members:\n\nSacha Silance\nNoah Newlands\nRishabh Singh\n");
            aboutLabel.setFont(new Font("Lucida Console", 20));

            Image logo = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/logo.png"));

            ImageView iv = new ImageView(logo);
            iv.setFitHeight(200);
            iv.setFitWidth(200);

            pane.getChildren().addAll(aboutLabel, iv);
            Scene aboutScene = new Scene(pane, 400, 400);
            aboutStage.setTitle("About");
            aboutStage.setResizable(false);
            aboutStage.setScene(aboutScene);
            aboutStage.show();
        });

        //Event handler for help menu item
        helpItem.setOnAction((event) -> {
            VBox pane = new VBox();
            pane.setAlignment(Pos.TOP_LEFT);

            Label welcome = new Label("  Welcome to the SNS drawing application!\n  This app can be used to draw and then save sketches into a list, which can then be reloaded later.\n\n");
            welcome.setFont(new Font("  Lucida Console", 30));

            Label rightView = new Label(" - On the righthand side of the window, users can draw their sketches. You can add squares, circles, triangles, or draw lines with your mouse");
            rightView.setFont(new Font("Lucida Console", 20));

            Label leftView = new Label(" - On the lefthand side of the window, you will see the list of all saved sketches and the option to add a new sketch");
            leftView.setFont(new Font("Lucida Console", 20));

            pane.getChildren().addAll(welcome, rightView, leftView);
            Scene helpScene = new Scene(pane, 1800, 500);
            helpStage.setTitle("Help");
            helpStage.setResizable(false);
            helpStage.setScene(helpScene);
            helpStage.show();
        });
    }

    private void loadSplashScreen(Stage primaryStage) throws Exception{
        //create a new window for the splash screen, but hide all buttons/toolbar
        Stage splashStage = new Stage(StageStyle.UNDECORATED);

        //Load splash screen view FXML in a new window
        StackPane splashPane = new StackPane();
        Label txt = new Label("Splash Screen");
        Image logo = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/logo.png"));
        ImageView iv = new ImageView(logo);
        iv.setFitHeight(300);
        iv.setFitWidth(300);
        splashPane.getChildren().add(txt);
        splashPane.getChildren().add(iv);
        Scene splashScene = new Scene(splashPane, 300, 275);
        splashStage.setScene(splashScene);

        //Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), splashPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        //Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splashPane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        //After fade in, start fade out
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });

        //After fade out, close the splash window and open
        fadeOut.setOnFinished((e) -> {
            splashStage.close();
            primaryStage.show();
        });

        splashStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
