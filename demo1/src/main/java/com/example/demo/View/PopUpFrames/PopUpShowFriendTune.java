package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopUpShowFriendTune {

    HBox buttonHBox;
    VBox songVBox;
    VBox noteVBox;
    Button listenButton;
    Button addButton;
    Label songNameLabel;
    Label artistLabel;
    Image songImage;
    ImageView imageView;

    public PopUpShowFriendTune(Song song, TuneUser user) {
        super();

        buttonHBox = new HBox();
        songVBox = new VBox();
        noteVBox = new VBox();

        listenButton = new Button("Listen To This Song");
        addButton = new Button("Add Your Favorites");

        songNameLabel = new Label(song.getName());
        artistLabel = new Label(song.getArtist());

        Image songImage = song.getImage();
        imageView = new ImageView(songImage);


        songVBox.getChildren().addAll(imageView, songNameLabel, artistLabel);


    }
}
