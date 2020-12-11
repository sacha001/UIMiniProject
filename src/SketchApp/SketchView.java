package SketchApp;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class SketchView extends Pane {
    public static final Color FILL_COLOR = Color.BLACK;
    public static final Color SELECTED_FILL_COLOR = Color.BLUE;

    private ToolBar toolBar;
    private ToggleButton circleButton, squareButton, triangleButton, pencilButton;
    private BorderPane root;
    private Label sketchLabel;

    public SketchView() {
        createToolBar();
        root = new BorderPane();
        root.setTop(toolBar);
        Canvas canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        //gc.fill();
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        root.setCenter(canvas);
        getChildren().add(root);
//        this.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY, Insets.EMPTY)));

        Main.model.shapeListProperty().addListener(new ListChangeListener<Shape>() {
            @Override
            public void onChanged(Change<? extends Shape> c) {
                while (c.next())
                {
                    for (Shape s : c.getRemoved())
                        root.getChildren().remove(s);

                    for (Shape s : c.getAddedSubList())
                    {
                        s.setStroke(Color.BLACK);
                        s.setFill(FILL_COLOR);
                        root.getChildren().add(s);
                    }
                }
            }
        });
    }

    public void selectShape(Shape node) {
        node.setFill(SELECTED_FILL_COLOR);
        node.setStrokeWidth(4);
        Main.model.setSelectedShape(node);
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
        squareButton.setToggleGroup(shapeButtonGroup);
        squareButton.setTooltip(new Tooltip("Square"));
        squareButton.setUserData(Main.model.SQUARE);

        //circle button
        Image circleImg = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/circle.png"));
        circleButton = new ToggleButton("", new ImageView(circleImg));
        circleButton.setToggleGroup(shapeButtonGroup);
        circleButton.setTooltip(new Tooltip("Circle"));
        circleButton.setUserData(Main.model.CIRCLE);

        //triangle button
        Image triangleImg = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/triangle.png"));
        triangleButton = new ToggleButton("", new ImageView(triangleImg));
        triangleButton.setToggleGroup(shapeButtonGroup);
        triangleButton.setTooltip(new Tooltip("Triangle"));
        triangleButton.setUserData(Main.model.TRIANGLE);

        //line button
        Image pencilImage = new Image(getClass().getClassLoader().getResourceAsStream("SketchApp/images/pencil.png"));
        pencilButton = new ToggleButton("", new ImageView(pencilImage));
        pencilButton.setToggleGroup(shapeButtonGroup);
        pencilButton.setTooltip(new Tooltip("Pencil"));
        pencilButton.setSelected(true);
        pencilButton.setUserData(Main.model.PENCIL);

        shapeButtonGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (shapeButtonGroup.getSelectedToggle() != null)
                Main.model.setCurrentShape((String) shapeButtonGroup.getSelectedToggle().getUserData());
        });

        sketchLabel = new Label();

        toolBar.getItems().add(pencilButton);
        toolBar.getItems().add(squareButton);
        toolBar.getItems().add(circleButton);
        toolBar.getItems().add(triangleButton);
        toolBar.getItems().add(sketchLabel);

    }

    public void setSketchLabel(String label) {
        sketchLabel.setText(label);
    }
}
