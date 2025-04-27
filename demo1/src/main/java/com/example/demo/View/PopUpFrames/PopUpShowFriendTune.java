package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class PopUpShowFriendTune extends PopUp{

    HBox buttonHBox;
    VBox songVBox;
    VBox noteVBox;
    HBox songAndLabelHBox;
    Button listenButton;
    Button addButton;
    Label songNameLabel;
    Label artistLabel;
    Image songImage;
    ImageView imageView;

    public PopUpShowFriendTune(TuneUser user) {
        super(350, 180);

        buttonHBox = new HBox();
        songVBox = new VBox();
        noteVBox = new VBox();
        songAndLabelHBox = new HBox();

        listenButton = new Button("Listen To This Song");
        addButton = new Button("Add Your Favorites");

        songNameLabel = new Label(user.getTuneSong().getName());
        artistLabel = new Label(user.getTuneSong().getArtist());

        Image songImage = user.getTuneSong().getImage();
        imageView = new ImageView(songImage);


        songVBox.getChildren().addAll(imageView, songNameLabel, artistLabel);
        buttonHBox.getChildren().addAll(listenButton, addButton);
        noteVBox.getChildren().addAll(songNameLabel, artistLabel);

        songAndLabelHBox.getChildren().addAll(songVBox, noteVBox);

        VBox layout = (VBox) getRoot();
        layout.getChildren().addAll(songAndLabelHBox, buttonHBox);



    }
}
