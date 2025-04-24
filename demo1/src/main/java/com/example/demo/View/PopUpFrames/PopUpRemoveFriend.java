package com.example.demo.View.PopUpFrames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopUpRemoveFriend extends PopUp{

    Label label;
    Button yesButton;
    Button noButton;
    HBox buttonHBox;

    public PopUpRemoveFriend() {
        super();
        label = new Label("Are you sure you want to remove your friend?");
        yesButton = new Button("Yes");
        noButton = new Button("No");
        buttonHBox = new HBox();

        buttonHBox.getChildren().addAll(yesButton, noButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setSpacing(15);

        VBox layout = (VBox) getRoot();

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(label, buttonHBox);
    }
}
