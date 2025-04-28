package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.GeneralButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class WelcomeFrame extends Scene {

    private Button loginButton;
    private Button signUpButton;
    private ImageView backgroundImageView;



    public WelcomeFrame() {
        super(new StackPane(), getScreenWidth(), getScreenHeight());
        this.getStylesheets().add(getClass().getResource("welcomeFrameStyle.css").toExternalForm());


        // Labels
        Label tuneLabel = new Label("TUNE");
        tuneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        Label descriptionLabel = new Label("Start listening and\n discovering music");
        descriptionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 35));

        //Buttons
        loginButton = new GeneralButton("Login");
        signUpButton = new GeneralButton("Sign Up");
        loginButton.getStyleClass().add("grayButton");

        // Image & ImageView
        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/example/demo/TuneBackground.png"));
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(getScreenWidth());
        backgroundImageView.setFitHeight(getScreenHeight());
        backgroundImageView.setPreserveRatio(false);

       // Boxes
        VBox vbox = new VBox(40);
        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(loginButton, signUpButton);
        vbox.getChildren().addAll(tuneLabel, descriptionLabel, hbox);

        // Alignments
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);

        // Layout
        StackPane layout = (StackPane) getRoot();
        layout.getChildren().addAll(backgroundImageView, vbox);
    }

    private static double getScreenWidth() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    }

    // getters for Buttons
    public Button getLoginButton() {
        return loginButton;
    }

    public Button getSignUpButton() {
        return signUpButton;
    }
}
