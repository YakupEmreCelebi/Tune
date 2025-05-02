package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SongVBox extends VBox {

    private Label artistLabel;
    private Label genreLabel;
    private ImageView imageView;

    public SongVBox(Song song) {

        super(3);
        setMaxWidth(100);

        //Labels
        artistLabel = new Label(song.getArtist());
        genreLabel = new Label(song.getGenre());

        artistLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 12");

        ImageView imageView = new ImageView(song.getImage());
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        getChildren().addAll(imageView, artistLabel, genreLabel);

        setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }
}
