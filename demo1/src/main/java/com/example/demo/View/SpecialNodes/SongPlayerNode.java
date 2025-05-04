package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Song;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


public class SongPlayerNode extends HBox {

    SongNode songNode;
    Slider slider;
    ImageView playImageView;
    ImageView nextImageView;
    ImageView previousImageView;
    VBox playerVBox;
    HBox buttonsHBox;
    HBox sliderHBox;
    HBox songTitleHBox;
    Button playButton;
    Button nextButton;
    Button previousButton;
    Label songLabel;


    public SongPlayerNode(Song song) {
        super(30);
        setPrefHeight(200);

        // StyleSheet
        this.getStylesheets().add(getClass().getResource("slider.css").toExternalForm());

        // Images
        createImages();

        // Circles for end and start point for slider
        Circle startCircle = new Circle(7.5);
        startCircle.setFill(Color.BLACK);
        Circle endCircle = new Circle(7.5);
        endCircle.setFill(Color.BLACK);
        startCircle.setTranslateX(4.1);
        endCircle.setTranslateX(-4.2);

        // Buttons
        playButton = new Button("", playImageView);
        nextButton = new Button("", nextImageView);
        previousButton = new Button("", previousImageView);

        playButton.setStyle("-fx-background-color: transparent;");
        nextButton.setStyle("-fx-background-color: transparent;");
        previousButton.setStyle("-fx-background-color: transparent;");

        addHoverEffect(playButton);
        addHoverEffect(nextButton);
        addHoverEffect(previousButton);

        // Slider
        slider = new Slider();
        slider.setPrefWidth(400);
        slider.setMaxWidth(400);
        slider.getStyleClass().add("custom-slider");

        // Label
        songLabel = new Label(song.getName());
        songLabel.setAlignment(Pos.CENTER_LEFT);
        songLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15 ));

        // HBoxes
        buttonsHBox = new HBox(3);
        buttonsHBox.getChildren().addAll(previousButton, playButton, nextButton);
        buttonsHBox.setAlignment(Pos.CENTER);

        sliderHBox = new HBox();
        sliderHBox.getChildren().addAll( startCircle, slider, endCircle);
        sliderHBox.setAlignment(Pos.CENTER);

        songTitleHBox = new HBox();
        songTitleHBox.getChildren().addAll(songLabel);
        songTitleHBox.setAlignment(Pos.CENTER_LEFT);

        // Song Node
        songNode = new SongNode(song, 150,150, false);

        playerVBox = new VBox(8);
        playerVBox.getChildren().addAll(songTitleHBox, sliderHBox, buttonsHBox);
        playerVBox.setAlignment(Pos.CENTER);

        getChildren().addAll(songNode, playerVBox);
    }

    private void createImages(){

        Image playImage = new Image(getClass().getResourceAsStream("/com/example/demo/play_ico.png"));
        playImageView = new ImageView(playImage);
        playImageView.setPreserveRatio(true);
        playImageView.setFitWidth(40);

        Image nextImage = new Image(getClass().getResourceAsStream("/com/example/demo/right-arrow2.png"));
        nextImageView = new ImageView(nextImage);
        nextImageView.setPreserveRatio(true);
        nextImageView.setFitWidth(30);

        Image previousImage = new Image(getClass().getResourceAsStream("/com/example/demo/left-arrow2.png"));
        previousImageView = new ImageView(previousImage);
        previousImageView.setPreserveRatio(true);
        previousImageView.setFitWidth(30);
    }

    private void addHoverEffect(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), button);
        scaleUp.setToX(1.05);
        scaleUp.setToY(1.08);

        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.1), button);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleUp.play();
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleDown.play();
            }
        });
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public Slider getSlider() {
        return slider;
    }
}
