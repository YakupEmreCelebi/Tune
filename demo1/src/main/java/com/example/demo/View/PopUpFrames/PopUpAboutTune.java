package com.example.demo.View.PopUpFrames;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopUpAboutTune extends PopUp{

    Label label;
    Label title;

    public PopUpAboutTune(){
        super(1000,300);

        title = new Label("About Tune");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

        label = new Label(
                "Tune is a music application that allows you to enjoy your favorite tracks and explore new music. You can listen to music, add your friends, and discover their music tastes.\n" +
                        "With Tune, you can easily add your favorite songs and listen to them whenever you like.\n" +
                "\n" +
                "Additionally, Tune offers a song recommendation feature, where you can:\n" +
                "\n" +
                "Get a random song suggestion.\n" +
                "\n" +
                "Use detailed search to find a song based on specific parameters.\n" +
                "\n" +
                "Receive shared music recommendations based on your common interests with friends.\n" +
                "\n" +
                "Whether you're exploring new tunes or enjoying your personal playlist, Tune makes it easy to connect with music and share your love for it with others.");

        VBox layout = (VBox) getRoot();
        layout.getChildren().addAll(title, label);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setSpacing(15);
    }
}
