package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.TuneUser;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PopUpRemoveFriend extends PopUp{

    Label label;
    Button yesButton;
    Button noButton;
    HBox buttonHBox;

    public PopUpRemoveFriend(TuneUser friend) {
        super(350, 180);

        // Label
        label = new Label("    Are you sure you want to \nremove " + friend.getUsername() + " from your friends?");
        label.setStyle("-fx-font-size: 20");

        // Buttons
        yesButton = new Button("Yes");
        noButton = new Button("No");
        yesButton.setStyle("-fx-font-size: 15; -fx-background-color: black; -fx-text-fill: white;");
        noButton.setStyle("-fx-font-size: 15; -fx-background-color: #d5d5d5; -fx-text-fill: black;");
        yesButton.setPrefWidth(60);
        noButton.setPrefWidth(60);
        addHoverEffect(yesButton);
        addHoverEffect(noButton);

        // Button HBox
        buttonHBox = new HBox(30);
        buttonHBox.getChildren().addAll(yesButton, noButton);
        buttonHBox.setAlignment(Pos.CENTER);

        // Layout
        VBox layout = (VBox) getRoot();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);

        layout.getChildren().addAll(label, buttonHBox);
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

    public Button getYesButton() {
        return yesButton;
    }

    public Button getNoButton() {
        return noButton;
    }
}
