package com.example.demo.View.SpecialNodes;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AddFriendNode extends VBox {

    public AddFriendNode() {
        super(10);
        Image plusImg = new Image(getClass().getResourceAsStream("/com/example/demo/plus_ico.png"));
        ImageView plusImgView = new ImageView(plusImg);
        plusImgView.setPreserveRatio(true);
        plusImgView.setFitWidth(110);

        this.getChildren().add(plusImgView);

        createEffects();
    }

    private void createEffects() {
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
