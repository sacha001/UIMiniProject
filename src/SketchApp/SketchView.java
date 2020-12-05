package SketchApp;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SketchView extends Pane {
    private ToolBar toolBar;
    private ToggleButton circleButton, squareButton, triangleButton;

    public SketchView() {
        createToolBar();
        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        getChildren().add(root);
    }

    private void createToolBar() {
        toolBar = new ToolBar();
        ToggleGroup shapeButtonGroup = new ToggleGroup();

        //square button
        Image squareImg = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/square.png"));
        squareButton = new ToggleButton("", new ImageView(squareImg));
        squareButton.setUserData("square");
        squareButton.setToggleGroup(shapeButtonGroup);
        squareButton.setTooltip(new Tooltip("Square"));
        squareButton.setSelected(true);

        //circle button
        Image circleImg = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/circle.png"));
        circleButton = new ToggleButton("", new ImageView(circleImg));
        circleButton.setUserData("circle");
        circleButton.setToggleGroup(shapeButtonGroup);
        circleButton.setTooltip(new Tooltip("Circle"));


        //line button
        Image triangleImg = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/triangle.png"));
        triangleButton = new ToggleButton("", new ImageView(triangleImg));
        triangleButton.setToggleGroup(shapeButtonGroup);
        triangleButton.setTooltip(new Tooltip("Triangle"));
        triangleButton.setUserData("triangle");

        toolBar.getItems().add(squareButton);
        toolBar.getItems().add(circleButton);
        toolBar.getItems().add(triangleButton);
    }
}
