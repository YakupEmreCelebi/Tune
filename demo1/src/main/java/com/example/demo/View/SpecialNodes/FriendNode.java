package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.TuneUser;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class FriendNode extends VBox {

    private TuneUser friend;

    public FriendNode(TuneUser friend) {
        super(10);

        this.friend = friend;

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));

        Image friendProfileImage = new Image(getClass().getResourceAsStream("/com/example/demo/megadeth_ico.jpg"));
        ImageView friendProfileImageView = new ImageView(friendProfileImage);
        friendProfileImageView.setPreserveRatio(true);
        friendProfileImageView.setFitWidth(100);
        friendProfileImageView.setClip(new Circle(50, 50, 50));

        Label friendName = new Label(friend.getUsername());
        friendName.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        friendName.setAlignment(Pos.CENTER);
        friendName.setTextAlignment(TextAlignment.CENTER);

        this.getChildren().addAll(friendProfileImageView, friendName);

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

    public TuneUser getFriend() {
        return friend;
    }
}
