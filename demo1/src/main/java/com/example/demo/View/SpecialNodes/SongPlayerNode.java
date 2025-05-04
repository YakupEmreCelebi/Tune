package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class SongPlayerNode extends HBox {

    SongNode songNode;
    Slider slider;
    ImageView playImageView;
    ImageView nextImageView;
    ImageView previousImageView;
    VBox playerVBox;
    HBox buttonsHBox;
    HBox sliderHBox;
    Button playButton;
    Button nextButton;
    Button previousButton;


    public SongPlayerNode(Song song) {
        super(10);
        setPrefHeight(200);

        this.getStylesheets().add(getClass().getResource("slider.css").toExternalForm());

        // Images
        createImages();

        // Circles for end and start point for slider
        Circle startCircle = new Circle(7);
        startCircle.setFill(Color.BLACK);
        Circle endCircle = new Circle(7);
        endCircle.setFill(Color.BLACK);


        // Buttons
        playButton = new Button("", playImageView);
        nextButton = new Button("", nextImageView);
        previousButton = new Button("", previousImageView);
        playButton.setStyle("-fx-background-color: transparent;");
        nextButton.setStyle("-fx-background-color: transparent;");
        previousButton.setStyle("-fx-background-color: transparent;");

        // Slider
        slider = new Slider();
        slider.setPrefWidth(400);
        slider.setMaxWidth(400);
        slider.getStyleClass().add("custom-slider");

        // HBox
        buttonsHBox = new HBox(3);
        buttonsHBox.getChildren().addAll(previousButton, playButton, nextButton);
        buttonsHBox.setAlignment(Pos.CENTER);

        sliderHBox = new HBox();
        sliderHBox.getChildren().addAll( startCircle, slider, endCircle);
        startCircle.setTranslateX(7);
        endCircle.setTranslateX(-7);
        startCircle.setTranslateY(4);
        endCircle.setTranslateY(4);

        // Song Node
        songNode = new SongNode(song, 150,150);



        playerVBox = new VBox(2);
        playerVBox.getChildren().addAll(sliderHBox, buttonsHBox);
        playerVBox.setAlignment(Pos.CENTER);

        getChildren().addAll(songNode, playerVBox);
    }

    private void createImages(){

        Image playImage = new Image(getClass().getResourceAsStream("/com/example/demo/play_ico2.png"));
        playImageView = new ImageView(playImage);
        playImageView.setPreserveRatio(true);
        playImageView.setFitWidth(30);

        Image nextImage = new Image(getClass().getResourceAsStream("/com/example/demo/right-arrow2.png"));
        nextImageView = new ImageView(nextImage);
        nextImageView.setPreserveRatio(true);
        nextImageView.setFitWidth(30);

        Image previousImage = new Image(getClass().getResourceAsStream("/com/example/demo/left-arrow2.png"));
        previousImageView = new ImageView(previousImage);
        previousImageView.setPreserveRatio(true);
        previousImageView.setFitWidth(30);
    }
}
