package com.example.demo.View.PopUpFrames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUpUpdate extends PopUp{

    Label label;
    TextField textField;
    Button updateButton;

    public PopUpUpdate(String title, String textAreaPrompt, String buttonText) {
        super();

        // Label
        label = new Label(title);
        label.setStyle("-fx-font-size: 17");

        HBox labelContainer = new HBox(label);
        labelContainer.setMaxWidth(260);
        labelContainer.setAlignment(Pos.CENTER);
        labelContainer.setAlignment(Pos.CENTER_LEFT);

        //Text Field
        textField = new TextField();
        textField.setPromptText(textAreaPrompt);
        textField.setMaxWidth(260);
        textField.setPrefHeight(30);
        textField.setStyle("-fx-background-color: transparent; -fx-border-color: #8e8989; -fx-border-width: 1px; -fx-border-radius: 5px;");

        //Button
        updateButton = new Button(buttonText);
        updateButton.setPrefWidth(260);
        updateButton.setPrefHeight(35);
        updateButton.setStyle("-fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #171616; -fx-text-fill: white; -fx-font-size: 15");


        VBox layout = (VBox) getRoot();

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(labelContainer, textField, updateButton);


    }

    public Button getUpdateButton() {
        return updateButton;
    }
}
