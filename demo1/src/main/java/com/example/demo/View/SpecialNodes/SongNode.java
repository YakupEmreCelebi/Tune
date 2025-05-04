package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SongNode extends VBox {

    private Label artistLabel;
    private Label genreLabel;
    private ImageView imageView;

    public SongNode(Song song, int width, int imgHeight) {

        super(10);

        setMaxWidth(width);

        //Labels
        artistLabel = new Label(song.getArtist());
        genreLabel = new Label(song.getGenre());

        artistLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 12");

        ImageView imageView = new ImageView(song.getImage());
        imageView.setFitHeight(imgHeight);

        imageView.setPreserveRatio(true);

        getChildren().addAll(imageView, artistLabel, genreLabel);

        setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }
}
