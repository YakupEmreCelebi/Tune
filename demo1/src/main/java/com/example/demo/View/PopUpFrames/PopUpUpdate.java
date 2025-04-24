package com.example.demo.View.PopUpFrames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUpUpdate extends PopUp{

    Label label;
    TextField textField;
    Button updateButton;

    public PopUpUpdate(String title, String textAreaPrompt, String buttonText) {
        super();

        label = new Label(title);
        textField = new TextField();
        textField.setPromptText(textAreaPrompt);
        updateButton = new Button(buttonText);

        VBox layout = (VBox) getRoot();

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(label, textField, updateButton);

        // Close when button pressed
        updateButton.setOnAction(e -> {
            Stage currentStage = (Stage) textField.getScene().getWindow();
            currentStage.close();
        });
    }


}
