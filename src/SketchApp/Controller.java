package SketchApp;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class Controller {
    private int selectedSketch = -1;
    private int numSketches = 0;
    public enum State {READY, DRAG_SELECTION_STARTED, DRAG_ITEMS_STARTED}
    private State state;

    public Controller() {
        Main.sketchView.addEventHandler(MouseEvent.ANY, new MouseHandler());
        state = State.READY;

        Main.sketchView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY && Main.model.getCurrentShape().equals(Main.model.PENCIL) && selectedSketch != -1) {
                    Sketch currentSketch = Main.model.getCurrentSketch();
                    if(e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                        currentSketch.start(e.getX(),e.getY()-40);
                    }

                }
            }

        });

        Main.sketchView.setOnMouseDragged(new EventHandler<MouseEvent>() {

             @Override
             public void handle(MouseEvent e) {
                 Sketch currentSketch = Main.model.getCurrentSketch();
                 if (e.getButton() == MouseButton.PRIMARY && Main.model.getCurrentShape().equals(Main.model.PENCIL) && selectedSketch != -1) {
                     currentSketch.drag(e.getX(), e.getY()-40);
                 }
             }
        });

        ListView lv = Main.sketchListView.getListView();

        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedSketch = lv.getSelectionModel().getSelectedIndex();
                if (selectedSketch >= 0)
                    Main.model.setCurrentSketch(Main.model.sketchesProperty().get(selectedSketch));
            }
        });

        Main.model.sketchNamesProperty().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                int sketchesPropertySize = Main.model.sketchNamesProperty().getSize();
                if (numSketches < sketchesPropertySize) {
                    lv.getItems().add(Main.model.sketchNamesProperty().get(sketchesPropertySize - 1));
                    lv.getSelectionModel().select(sketchesPropertySize-1);
                    selectedSketch = sketchesPropertySize-1;
                    System.out.println(selectedSketch);
                    SimpleListProperty test = Main.model.sketchesProperty();
                    Main.model.setCurrentSketch(Main.model.sketchesProperty().get(selectedSketch));
                    handleLoadBtn();
                    numSketches++;
                } else {
                    lv.getItems().remove(selectedSketch);
                    numSketches--;
                }
            }
        });

    }

    public class MouseHandler implements EventHandler<MouseEvent>{
        private double prevX = 0, prevY = 0;

        @Override
        public void handle(MouseEvent e) {
            if (!Main.model.getCurrentShape().equals(Main.model.PENCIL) && selectedSketch != -1)
            switch(state)
            {
                case READY:
                    if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                        prevX = e.getSceneX();
                        prevY = e.getSceneY();

                        if (Shape.class.isAssignableFrom(e.getTarget().getClass()))
                        {
                            Shape node = ((Shape) e.getTarget());
                            node.toFront();
                            Main.model.setSelectedShape(node);
                        }
                        else
                        {
                            Main.model.setSelectedShape(null);
                        }
                    }
                    else if (e.getEventType()==MouseEvent.DRAG_DETECTED)
                    {
                        if (Shape.class.isAssignableFrom(e.getTarget().getClass()))
                        {
                            state = State.DRAG_ITEMS_STARTED;
                        }
                        else {
                            state = State.DRAG_SELECTION_STARTED;
                        }
                    }
                    else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
                    {
                        if (e.getTarget().getClass()==Main.model.getCurrentSketch().getCanvas().getClass()) {
                            Main.model.getCurrentSketch().addShape(e.getX(), e.getY());
                        }
                    }
                    break;	//end State.READY

                case DRAG_ITEMS_STARTED:
                    if (e.getEventType()==MouseEvent.MOUSE_DRAGGED)
                    {
                        moveShape(e.getSceneX() - prevX, e.getSceneY() - prevY);
                        prevX = e.getSceneX();
                        prevY = e.getSceneY();
                    }

                    else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
                    {
                        state = State.READY;
                    }
                    break; //end State.DRAG_ITEMS_STARTED
                case DRAG_SELECTION_STARTED:
                    if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
                    {
                        state = State.READY;
                    }
                    break;
            }//end switch(state)
        }
    }

    public void handleLoadBtn() {
        if (selectedSketch != -1) {
            Main.model.shapeListProperty().removeAll(Main.model.shapeListProperty().get());
            Sketch sketch = Main.model.getCurrentSketch();
            Main.sketchView.setBorderPaneCenter(sketch.getCanvas());
            Main.sketchView.setSketchLabel(sketch.getLabel());
            for (Shape s : sketch.shapeListProperty().get())
                Main.model.shapeListProperty().add(s);
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

    private void moveShape(double addX, double addY)
    {
        Shape s = Main.model.getSelectedShape();
        s.setTranslateX(s.getLayoutX() + s.getTranslateX() + addX);
        s.setTranslateY(s.getLayoutY() + s.getTranslateY() + addY);
    }
}
