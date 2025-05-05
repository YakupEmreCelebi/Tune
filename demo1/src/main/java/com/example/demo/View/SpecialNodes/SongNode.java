package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SongNode extends VBox {

    private Label firstLabel;
    private Label genreLabel;
    private ImageView imageView;
    private Song theSong;

    public SongNode(Song song, int width, int imgHeight, boolean clickable) {
        super(1.5);
        setPadding(new Insets(10));

        theSong = song;

        setMaxWidth(width);

        //Labels
        firstLabel = (clickable) ? new Label(song.getName()) : new Label(song.getArtist());
        genreLabel = new Label(song.getGenre());

        firstLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 13");

        ImageView imageView = new ImageView(song.getImage());
        imageView.setFitHeight(imgHeight);
        imageView.setPreserveRatio(true);

        Rectangle clip = new Rectangle(width, imgHeight);
        clip.setArcWidth (10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

        getChildren().addAll(imageView, firstLabel, genreLabel);

        if (clickable) createEffects();

        //setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }

    private void createEffects() {
        FadeTransition fadeUp = new FadeTransition(Duration.millis(200), this);
        fadeUp.setToValue(1.0);

        FadeTransition fadeDown = new FadeTransition(Duration.millis(200), this);
        fadeDown.setToValue(.7);

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                fadeDown.play();
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                fadeUp.play();
            }
        });
    }


    public Song getTheSong() {
        return theSong;
    }
}
