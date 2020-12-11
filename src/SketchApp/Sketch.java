package SketchApp;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Sketch {
    public static final double SHAPE_LENGTH = 40;

    private GraphicsContext gc;
    private Canvas canvas;

    private SimpleListProperty<Shape> shapeListProperty;
    private String label;

    public Sketch(String label) {
        ArrayList<Shape> list = new ArrayList<Shape>();
        ObservableList<Shape> observableList = (ObservableList<Shape>) FXCollections.observableArrayList(list);
        shapeListProperty = new SimpleListProperty<Shape>(observableList);

        canvas = new Canvas(800, 400);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(5);
        gc.setStroke(Color.BLACK);
        this.label = label;


    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void start(double x, double y){
        gc.beginPath();
        gc.moveTo(x,y);
        gc.stroke();
    }

    public void drag(double x, double y){
        gc.lineTo(x,y);
        gc.stroke();
    }

    public String getLabel() {
        return this.label;
    }

    public void addShape(double x, double y)
    {
        double shapeX = x - SHAPE_LENGTH/2;
        double shapeY = y - SHAPE_LENGTH/2;
        Shape newShape = null;

        if (Main.model.getCurrentShape() == Main.model.SQUARE) {
            newShape = new Rectangle(shapeX, shapeY, SHAPE_LENGTH, SHAPE_LENGTH);
        } else if (Main.model.getCurrentShape() == Main.model.CIRCLE) {
            newShape = new Circle(x, y, SHAPE_LENGTH / 2);
        } else if (Main.model.getCurrentShape() == Main.model.TRIANGLE) {
            newShape = new Polygon();
            double height = (SHAPE_LENGTH * Math.sqrt(3))/2;
            Double[] points = new Double[]{
                    x - SHAPE_LENGTH/2, y + height/2,
                    x, y - height/2,
                    x + SHAPE_LENGTH/2, y + height/2
            };
            ((Polygon)newShape).getPoints().addAll(points);
        }
        shapeListProperty.add(newShape);
        Main.model.shapeListProperty().add(newShape);
    }

    public SimpleListProperty<Shape> shapeListProperty(){
        return shapeListProperty;
    }
}
