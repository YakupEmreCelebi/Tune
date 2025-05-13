package com.example.demo.View.PopUpFrames;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.RecursiveTask;

public class PopUpUpdate extends PopUp{

    Label label;
    TextField textField;
    Button updateButton;

    public PopUpUpdate(String title, String textAreaPrompt, String buttonText) {
        super(350, 180);

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
        addHoverEffect(updateButton);


        VBox layout = (VBox) getRoot();

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(labelContainer, textField, updateButton);


    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public String getStringToUpdate() {
        return textField.getText();
    }

    public TextField getTextField() {
        return textField;
    }

    private void addHoverEffect(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), button);
        scaleUp.setToX(1.02);
        scaleUp.setToY(1.02);

        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.1), button);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleUp.play();
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleDown.play();
            }
        });
    }
}
