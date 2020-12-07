package SketchApp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddSketchWidget {
    private final Stage dialog;

    public AddSketchWidget(Stage primaryStage) {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Add a new sketch"));

        TextField tf = new TextField();
        Button saveButton = new Button("Save");
        saveButton.setOnAction((event -> {
            Main.model.sketchNamesProperty().add(tf.getCharacters().toString());
            dialog.close();
        }));

        dialogVbox.getChildren().add(tf);
        dialogVbox.getChildren().add(saveButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
    }

    public void showModal() {
        dialog.show();
    }
}
