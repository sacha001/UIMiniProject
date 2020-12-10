package SketchApp;

import javafx.beans.property.SimpleListProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Sketch {
    private GraphicsContext gc;
    private Canvas canvas;

    private SimpleListProperty<Shape> shapes;
    private String label;

    public Sketch(String label) {
        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        //gc.fill();
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
}
