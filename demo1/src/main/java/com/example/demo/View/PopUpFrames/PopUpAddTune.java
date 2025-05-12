package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.Song;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PopUpAddTune extends PopUp{

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private Label addYourTuneLabel;
    private Label tuneFeelingLabel;
    private TextArea tuneNoteTextArea;
    private Button addYourTuneButton;
    private ImageView plusImageView;
    private Song song;

    public PopUpAddTune(Song song){
        super(500, 275);

        this.song = song;

        // Image & ImageView
        createImage();

        //HBoxes
        hBox1 = new HBox(8);
        hBox2 = new HBox(8);
        hBox3 = new HBox();

        hBox3.setPadding(new Insets(0,0,0,280));

        //Labels
        addYourTuneLabel = new Label("Add Your Tune:");
        tuneFeelingLabel = new Label("Tune Feeling: ");
        addYourTuneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25");
        tuneFeelingLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25");
        addYourTuneLabel.setMinWidth(20);

        Label songInfo = new Label(song.getName() + "\nby " + song.getArtist());
        songInfo.setStyle("-fx-font-weight: normal; -fx-font-size: 20");

        //Text Field
        tuneNoteTextArea = new TextArea();
        tuneNoteTextArea.setPrefHeight(70);
        tuneNoteTextArea.setPrefWidth(300);
        tuneNoteTextArea.setPromptText("Say something...");
        tuneNoteTextArea.setStyle("-fx-border-radius: 5px; -fx-background-radius: 5px; " +
                "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-border-color: #ccc;");
        tuneNoteTextArea.setWrapText(true);

        //Button
        addYourTuneButton = new Button("Add your tune");
        addYourTuneButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-radius: 5; -fx-font-size: 18px");
        addHoverEffect(addYourTuneButton);

        //Add to HBoxes
        hBox1.getChildren().addAll(addYourTuneLabel, plusImageView, songInfo);
        hBox2.getChildren().addAll(tuneFeelingLabel, tuneNoteTextArea);
        hBox3.getChildren().add(addYourTuneButton);

        //Layout
        VBox layout = (VBox) getRoot();
        layout.setSpacing(10);
        layout.setPadding(new Insets(5,0,0,10));
        layout.getChildren().addAll(hBox1, hBox2, hBox3);
    }

    public void createImage() {
        Image backgroundImage = song.getImage();
        plusImageView = new ImageView(backgroundImage);
        plusImageView.setFitWidth(100);
        plusImageView.setPreserveRatio(true);
    }

    public Button getAddYourTuneButton() {
        return addYourTuneButton;
    }

    public String getTuneNoteTextAreaText() {
        return tuneNoteTextArea.getText();
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
}
