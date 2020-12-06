package SketchApp;

import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SketchListView extends Pane {
    public SketchListView(Stage primaryStage) {
        ToolBar toolbar = new ToolBar();
        Button addSketchBtn = new Button("Add new sketch");
        AddSketchWidget asw = new AddSketchWidget(primaryStage);
        addSketchBtn.setOnAction((e -> { asw.showModal(); }));
        toolbar.getItems().add(addSketchBtn);

        BorderPane root = new BorderPane();
        root.setTop(toolbar);


        ListView listView = new ListView();
        listView.setPrefHeight(500);

        Main.model.sketchNamesProperty().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                listView.getItems().add(Main.model.sketchNamesProperty().get(Main.model.sketchNamesProperty().getSize()-1));
            }
        });

        root.setCenter(listView);
        getChildren().add(root);
    }
}
