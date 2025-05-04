package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.TuneUser;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class FriendsButton extends Button {

    public FriendsButton(TuneUser tuneUser) {
        super();
        this.setText(tuneUser.getUsername());
        this.setShape(new Circle(1000));
        this.setMinSize(100, 100);
        this.setMaxSize(100, 100);
        this.setPrefSize(100, 100);
        this.setStyle("-fx-background-color: #0c0c2c; -fx-font-size: 19; -fx-font-family: Arial; -fx-font-weight: bold");

        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), this);
        scaleUp.setToX(1.03);
        scaleUp.setToY(1.03);

        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.1), this);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleUp.play();
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleDown.play();
            }
        });
    }
}
