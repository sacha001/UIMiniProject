package SketchApp;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SketchView extends Pane {
    private ToolBar toolBar;
    private ToggleButton circleButton, squareButton, triangleButton;
    private BorderPane root;
    private Label sketchLabel;

    public SketchView() {
        createToolBar();
        root = new BorderPane();
        root.setTop(toolBar);
        getChildren().add(root);
//        this.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public void setBorderPaneCenter(Node center) {
        root.setCenter(center);
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

        sketchLabel = new Label();

        toolBar.getItems().add(squareButton);
        toolBar.getItems().add(circleButton);
        toolBar.getItems().add(triangleButton);
        toolBar.getItems().add(sketchLabel);

    }

    public void setSketchLabel(String label) {
        sketchLabel.setText(label);
    }
}
