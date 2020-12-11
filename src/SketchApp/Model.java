package SketchApp;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Model {
    public static final String SQUARE = "SQUARE";
    public static final String CIRCLE = "CIRCLE";
    public static final String TRIANGLE = "TRIANGLE";
    public static final String PENCIL = "PENCIL";
    public static final double SHAPE_LENGTH = 40;

    private SimpleListProperty<String> sketchNames;
    private SimpleListProperty<Sketch> sketches;
    private SimpleListProperty<Shape> shapeListProperty;
    private Sketch currentSketch;
    private Shape selectedShape;
    private String currentShape;

    public Model() {
        ArrayList<Shape> list = new ArrayList<Shape>();
        ObservableList<Shape> observableList = (ObservableList<Shape>) FXCollections.observableArrayList(list);
        shapeListProperty = new SimpleListProperty<Shape>(observableList);
        this.currentShape = PENCIL;

        ArrayList<String> al1 = new ArrayList<String>();
        ObservableList<String> observableList1 = (ObservableList<String>) FXCollections.observableArrayList(al1);
        sketchNames = new SimpleListProperty<>(observableList1);

        ArrayList<Sketch> al2 = new ArrayList<Sketch>();
        ObservableList<Sketch> observableList2 = (ObservableList<Sketch>) FXCollections.observableArrayList(al2);
        sketches = new SimpleListProperty<>(observableList2);
    }

    public SimpleListProperty<String> sketchNamesProperty() {
        return sketchNames;
    }

    public SimpleListProperty<Sketch> sketchesProperty() {
        return sketches;
    }

    public SimpleListProperty<Shape> shapeListProperty(){
        return shapeListProperty;
    }

    public Sketch getCurrentSketch() {
        return currentSketch;
    }

    public void setCurrentSketch(Sketch currentSketch) {
        this.currentSketch = currentSketch;
    }

    public void setCurrentShape(String currentShape) {
        this.currentShape = currentShape;
    }

    public String getCurrentShape() {
        return this.currentShape;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
}
