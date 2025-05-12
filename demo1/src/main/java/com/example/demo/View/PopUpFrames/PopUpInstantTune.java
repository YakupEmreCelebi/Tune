package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.Song;
import com.example.demo.View.SpecialNodes.SongNode;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PopUpInstantTune extends PopUp{

    SongNode songNode;
    Song song;
    Button anotherButton;
    Button addToFavoritesButton;
    VBox layout;
    HBox buttonHBox;

    public PopUpInstantTune(Song song){
        super(400, 350);

        this.song = song;
        songNode = new SongNode(song, 300, 220, false);

        // Buttons
        anotherButton = new Button("Get another song");
        anotherButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-radius: 3; -fx-background-radius: 3");

        addToFavoritesButton = new Button("Add to Favorites");
        addToFavoritesButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-border-radius: 3; -fx-background-radius: 3");

        addHoverEffect(anotherButton);
        addHoverEffect(addToFavoritesButton);

        // Button HBox
        buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(anotherButton, addToFavoritesButton);
        buttonHBox.setAlignment(Pos.CENTER);

        // Layout
        layout = (VBox) getRoot();
        layout.setSpacing(10);
        layout.getChildren().addAll(songNode, buttonHBox);
        layout.setAlignment(Pos.CENTER);
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
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleDown.play();
            }
        });
    }

    public Button getAnotherButton() {
        return anotherButton;
    }

    public Button getAddToFavoritesButton() {
        return addToFavoritesButton;
    }

    public Song getSong() {
        return this.song;
    }

    public void changeSong(Song aSong) {
        this.song = aSong;
        System.out.println(song.getName());
        songNode = new SongNode(song, 300, 220, false);
        layout.getChildren().set(0, songNode);
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
