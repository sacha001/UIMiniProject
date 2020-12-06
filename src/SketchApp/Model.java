package SketchApp;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {

    private SimpleListProperty<String> sketchNames;
    private SimpleListProperty<Sketch> sketches;

    public Model() {
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
}
