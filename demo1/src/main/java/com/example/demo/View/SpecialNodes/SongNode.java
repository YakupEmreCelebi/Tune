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
    private boolean clickable;

    public SongNode(Song song, int width, int imgHeight, boolean clickable) {
        super(1.5);
        setPadding(new Insets(10));

        theSong = song;
        this.clickable = clickable;

        setMaxWidth(width);

        //Labels
        firstLabel = (clickable) ? new Label(song.getName()) : new Label(song.getArtist());
        genreLabel = new Label(song.getGenre());

        firstLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 13");

        imageView = new ImageView(song.getImage());
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

    public void resetSong(Song song) {
        theSong = song;
        imageView.setImage(song.getImage());

        if (!clickable) firstLabel.setText(theSong.getArtist());
        genreLabel.setText(theSong.getGenre());

        getChildren().setAll(imageView, firstLabel, genreLabel);


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
