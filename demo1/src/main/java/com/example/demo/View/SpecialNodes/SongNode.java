package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SongNode extends VBox {

    private Label firstLabel;
    private Label genreLabel;
    private ImageView imageView;
    private Song theSong;
    private boolean clickable;
    private Button removeSongButton;
    private HBox buttonHBox;
    private ImageView removeSongImageView;

    private boolean buttonHovered = false;

    FadeTransition fadeUp;
    FadeTransition fadeDown;

    public SongNode(Song song, int width, int imgHeight, boolean clickable, boolean hasButton) {
        super(1.5);
        setPadding(new Insets(10));

        theSong = song;
        this.clickable = clickable;

        setMaxWidth(width);

        // Image & ImageView
        Image removeSongImage = new Image(getClass().getResourceAsStream("/com/example/demo/remove_ico.png"));
        removeSongImageView = new ImageView(removeSongImage);
        removeSongImageView.setFitWidth(16);
        removeSongImageView.setPreserveRatio(true);

        //Button
        if(hasButton)
        {
            removeSongButton = new Button("", removeSongImageView);
            removeSongButton.setStyle("-fx-background-color: transparent;");
            addHoverEffect(removeSongButton);
        }
        else
        {
            removeSongButton = new Button();
            removeSongButton.setMaxHeight(0);
            removeSongButton.setMaxWidth(0);
            removeSongButton.setMinHeight(0);
            removeSongButton.setMinWidth(0);
            removeSongButton.setPrefHeight(0);
            removeSongButton.setPrefWidth(0);
        }


        //Labels
        firstLabel = (clickable) ? new Label(song.getName()) : new Label(song.getArtist());
        genreLabel = new Label(song.getGenre());

        firstLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-font-family: Arial ");
        genreLabel.setStyle("-fx-font-size: 13");

        // HBox
        buttonHBox = new HBox(3);
        buttonHBox.getChildren().addAll(genreLabel, removeSongButton);

        // Image View
        imageView = new ImageView(song.getImage());
        imageView.setFitHeight(imgHeight);
        imageView.setPreserveRatio(true);

        Rectangle clip = new Rectangle(width, imgHeight);
        clip.setArcWidth (10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

        getChildren().addAll(imageView, firstLabel, buttonHBox);

        if (clickable) createEffects();
        //setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }

    public void resetSong(Song song) {
        theSong = song;
        imageView.setImage(song.getImage());

        if (!clickable) firstLabel.setText(theSong.getArtist());
        genreLabel.setText(theSong.getGenre());

        buttonHBox.getChildren().setAll(genreLabel, removeSongButton);
        getChildren().setAll(imageView, firstLabel, genreLabel);

    }

    private void createEffects() {
        fadeUp = new FadeTransition(Duration.millis(200), this);
        fadeUp.setToValue(1.0);

        fadeDown = new FadeTransition(Duration.millis(200), this);
        fadeDown.setToValue(.7);

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                if(!buttonHovered)
                {
                    fadeDown.play();
                }

            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                fadeUp.play();
            }
        });
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
                buttonHovered = true;
                fadeUp.play();
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleDown.play();
                buttonHovered = false;
            }
        });
    }


    public Song getTheSong() {
        return theSong;
    }

    public Button getRemoveSongButton() {
        return removeSongButton;
    }
}
