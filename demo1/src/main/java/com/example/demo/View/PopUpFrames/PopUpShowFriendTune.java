package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


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
        super(400, 200);

        // Boxes
        buttonHBox = new HBox(20);
        songVBox = new VBox(3);
        noteVBox = new VBox(10);
        songAndLabelHBox = new HBox(10);

        // Buttons
        listenButton = new Button("Listen To This Song");
        addButton = new Button("Add Your Favorites");
        listenButton.setStyle("-fx-background-color: black; -fx-text-fill: white");
        addButton.setStyle("-fx-background-color: #d5d5d5; -fx-text-fill: black");

        // Labels
        songNameLabel = new Label(user.getTuneSong().getName());
        artistLabel = new Label(user.getTuneSong().getArtist());
        noteTitleLabel = new Label(user.getUsername() + "'s feelings about their tune:");
        noteLabel = new Label(user.getTuneNote());
        noteTitleLabel.setStyle("-fx-font-size: 21; -fx-font-weight: bold");
        noteLabel.setStyle("-fx-font-size: 16");

        // Image
        Image songImage = user.getTuneSong().getImage();
        imageView = new ImageView(songImage);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
//        Rectangle clip = new Rectangle(imageView.getFitWidth(),100);
//        clip.setArcWidth(20);
//        clip.setArcHeight(20);
//        imageView.setClip(clip);

        // Add elements to Boxes
        songVBox.getChildren().addAll(imageView, songNameLabel, artistLabel);
        buttonHBox.getChildren().addAll(listenButton, addButton);
        noteVBox.getChildren().addAll(noteTitleLabel, noteLabel);
        songAndLabelHBox.getChildren().addAll(songVBox, noteVBox);

        // Layout
        VBox layout = (VBox) getRoot();
        layout.setSpacing(5);
        layout.setPadding(new Insets(0,0,0,10));
        layout.getChildren().addAll(songAndLabelHBox, buttonHBox);
    }
}
