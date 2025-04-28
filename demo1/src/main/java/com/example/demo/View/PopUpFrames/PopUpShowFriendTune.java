package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class PopUpShowFriendTune extends PopUp{

    HBox buttonHBox;
    VBox songVBox;
    VBox noteVBox;
    HBox songAndLabelHBox;
    Button listenButton;
    Button addButton;
    Label songNameLabel;
    Label artistLabel;
    Label noteTitleLabel;
    Label noteLabel;
    Image songImage;
    ImageView imageView;

    public PopUpShowFriendTune(TuneUser user) {
        super(470, 200);

        // Boxes
        buttonHBox = new HBox(20);
        songVBox = new VBox(3);
        noteVBox = new VBox(10);
        songAndLabelHBox = new HBox(20);

        // Buttons
        listenButton = new Button("Listen To This Song");
        addButton = new Button("Add Your Favorites");
        listenButton.setStyle("-fx-background-color: black; -fx-text-fill: white");
        addButton.setStyle("-fx-background-color: #d5d5d5; -fx-text-fill: black");
        addHoverEffect(addButton);
        addHoverEffect(listenButton);

        // Labels
        songNameLabel = new Label(user.getTuneSong().getName());
        artistLabel = new Label(user.getTuneSong().getArtist());
        noteTitleLabel = new Label(user.getUsername() + "'s feelings about their tune:");
        noteLabel = new Label(user.getTuneNote());
        noteTitleLabel.setStyle("-fx-font-size: 21; -fx-font-weight: bold");
        noteLabel.setStyle("-fx-font-size: 16");
        songNameLabel.setStyle("-fx-font-size: 13; -fx-font-weight: bold");
        artistLabel.setStyle("-fx-font-size: 11");

        // Image
        Image songImage = user.getTuneSong().getImage();
        imageView = new ImageView(songImage);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        Rectangle clip = new Rectangle(100,100);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        imageView.setClip(clip);

        // Add elements to Boxes
        songVBox.getChildren().addAll(imageView, songNameLabel, artistLabel);
        buttonHBox.getChildren().addAll(listenButton, addButton);
        noteVBox.getChildren().addAll(noteTitleLabel, noteLabel);
        songAndLabelHBox.getChildren().addAll(songVBox, noteVBox);

        // Alignments
        buttonHBox.setAlignment(Pos.TOP_RIGHT);
        buttonHBox.setPadding(new Insets(0,20,0,0));

        // Layout
        VBox layout = (VBox) getRoot();
        layout.setSpacing(10);
        layout.setPadding(new Insets(0,0,0,20));
        layout.getChildren().addAll(songAndLabelHBox, buttonHBox);
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

    //Getters
    public Button getAddButton() {
        return addButton;
    }

    public Button getListenButton() {
        return listenButton;
    }
}
