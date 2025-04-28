package com.example.demo.View.SpecialNodes;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SongVBox extends VBox {

    private Label artistLabel;
    private Label genreLabel;
    private ImageView imageView;

    public SongVBox(Image image, String artist, String genre) {

        super(10);
        setMaxWidth(150);

        //Labels
        artistLabel = new Label(artist);
        genreLabel = new Label(genre);

        artistLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 12");

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        getChildren().addAll(imageView, artistLabel, genreLabel);

        setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }
}
