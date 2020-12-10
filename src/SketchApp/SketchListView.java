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
    private ListView listView;
    public SketchListView(Stage primaryStage) {
        ToolBar toolbar = new ToolBar();
        Button addBtn = new Button("Create");
        Button loadBtn = new Button("Load");
        Button delBtn = new Button("Delete");
        AddSketchWidget asw = new AddSketchWidget(primaryStage, listView);

        addBtn.setOnAction((e -> { asw.showModal(); }));
        loadBtn.setOnAction((e -> { Main.controller.handleLoadBtn(); }));
        delBtn.setOnAction((e -> { Main.controller. handleDelBtn(); }));

        toolbar.getItems().addAll(addBtn, loadBtn, delBtn);

        BorderPane root = new BorderPane();
        root.setTop(toolbar);


        listView = new ListView();
        listView.setPrefHeight(500);

        root.setCenter(listView);
        getChildren().add(root);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
