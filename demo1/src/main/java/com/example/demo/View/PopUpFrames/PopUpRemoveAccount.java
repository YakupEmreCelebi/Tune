package com.example.demo.View.PopUpFrames;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PopUpRemoveAccount extends PopUp{

    Label textLabel;
    Button yesButton;
    Button noButton;
    HBox buttonHBox;

    public PopUpRemoveAccount() {
        super(350, 180);

        //Label
        textLabel = new Label("Are you sure you want\n to delete this account?");
        textLabel.setStyle("-fx-font-size: 20");

        //Buttons
        yesButton = new Button("Yes");
        noButton = new Button("No");
        yesButton.setStyle("-fx-font-size: 15; -fx-background-color: black; -fx-text-fill: white;");
        noButton.setStyle("-fx-font-size: 15; -fx-background-color: #d5d5d5; -fx-text-fill: black;");
        yesButton.setPrefWidth(60);
        noButton.setPrefWidth(60);
        addHoverEffect(yesButton);
        addHoverEffect(noButton);

        //HBox
        buttonHBox = new HBox(30);
        buttonHBox.getChildren().addAll(yesButton, noButton);
        buttonHBox.setAlignment(Pos.CENTER);

        VBox layout = (VBox) getRoot();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(textLabel, buttonHBox);
    }

    public Button getNoButton() {
        return noButton;
    }

    public Button getYesButton() {
        return yesButton;
    }

    private void addHoverEffect(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), button);
        scaleUp.setToX(1.03);
        scaleUp.setToY(1.03);

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
