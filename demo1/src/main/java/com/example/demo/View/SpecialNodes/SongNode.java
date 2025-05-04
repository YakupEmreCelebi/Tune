package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SongNode extends VBox {

    private Label firstLabel;
    private Label genreLabel;
    private ImageView imageView;

    public SongNode(Song song, int width, int imgHeight, boolean nameOrArtist) {

        super(1.5);

        setMaxWidth(width);

        //Labels
        firstLabel = (nameOrArtist) ? new Label(song.getName()) : new Label(song.getArtist());
        genreLabel = new Label(song.getGenre());

        firstLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 12");

        ImageView imageView = new ImageView(song.getImage());
        imageView.setFitHeight(imgHeight);
        imageView.setPreserveRatio(true);

        Rectangle clip = new Rectangle(width, imgHeight);
        clip.setArcWidth (10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

        getChildren().addAll(imageView, firstLabel, genreLabel);

        //setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }
}
