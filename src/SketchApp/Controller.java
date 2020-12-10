package SketchApp;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Controller {
    private int selectedSketch = -1;
    private int numSketches = 0;

    public Controller() {
        Main.sketchView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    Sketch currentSketch = Main.model.getCurrentSketch();
                    if(e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                        currentSketch.start(e.getX(),e.getY());
                    }

                }
            }

        });

        Main.sketchView.setOnMouseDragged(new EventHandler<MouseEvent>() {

             @Override
             public void handle(MouseEvent e) {
                 Sketch currentSketch = Main.model.getCurrentSketch();
                 if (e.getButton() == MouseButton.PRIMARY) {
                     currentSketch.drag(e.getX(), e.getY());
                 }
             }
        });

        ListView lv = Main.sketchListView.getListView();

        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedSketch = lv.getSelectionModel().getSelectedIndex();
                Main.model.setCurrentSketch(Main.model.sketchesProperty().get(selectedSketch));
            }
        });

        Main.model.sketchNamesProperty().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                int sketchesPropertySize = Main.model.sketchNamesProperty().getSize();
                if (numSketches < sketchesPropertySize) {
                    lv.getItems().add(Main.model.sketchNamesProperty().get(sketchesPropertySize - 1));
                    numSketches++;
                } else {
                    lv.getItems().remove(selectedSketch);
                    numSketches--;
                }
            }
        });

    }

    public void handleLoadBtn() {
        if (selectedSketch != -1) {
            Sketch sketch = Main.model.getCurrentSketch();
            Main.sketchView.setBorderPaneCenter(sketch.getCanvas());
            Main.sketchView.setSketchLabel(sketch.getLabel());
        }
    }

    public void handleDelBtn() {
        if (selectedSketch != -1) {
            Main.model.sketchesProperty().remove(selectedSketch);
            Main.model.sketchNamesProperty().remove(selectedSketch);
            Main.sketchView.setBorderPaneCenter(null);
            Main.sketchView.setSketchLabel("");
            selectedSketch = -1;
        }
    }
}
