package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.GeneralButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginFrame extends Scene {

    private Button loginButton;
    private TextField usernameTextField;
    private PasswordField passwordField;
    private Label tuneLabel;
    private Label loginLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label warningLabel;
    private ImageView backgroundImageView;

    public LoginFrame() {
        super(new StackPane(), getScreenWidth(), getScreenHeight());
        this.getStylesheets().add(getClass().getResource("welcomeFrameStyle.css").toExternalForm());

        // Labels
        tuneLabel = new Label("Tune");
        loginLabel = new Label("Log In");
        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");
        warningLabel = new Label("Incorrect Username or Password");

        usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
        passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
        warningLabel.setStyle("-fx-text-fill: red; -fx-font-size: 15 ");
        warningLabel.setVisible(false);

        // Buttons
        loginButton = new GeneralButton("Login");

        // Text fields for username and password
        usernameTextField = new TextField();
        passwordField = new PasswordField();

        usernameTextField.setPromptText("Username");
        passwordField.setPromptText("Password");

        usernameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #8e8989; -fx-border-width: 1px; -fx-border-radius: 5px;");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: #8e8989; -fx-border-width: 1px; -fx-border-radius: 5px;");
        usernameTextField.setMaxWidth(getScreenWidth()/2 - 70);
        passwordField.setMaxWidth(getScreenWidth()/2 - 70);
        usernameTextField.setPrefHeight(38);
        passwordField.setPrefHeight(38);

        // Image for background
        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/example/demo/TuneBackground.png"));
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(.6);
        backgroundImageView.setFitWidth(1500);
        backgroundImageView.setFitHeight(900);
        backgroundImageView.setPreserveRatio(false);

        // VBox
        VBox usernameVBox = new VBox(20);
        VBox passwordVBox = new VBox(20);
        VBox allElementsVBox = new VBox(35);

        allElementsVBox.setMaxWidth(getScreenWidth()/2);
        allElementsVBox.setMaxHeight(400);
        allElementsVBox.setStyle("-fx-border-color: #9f9b9b; -fx-border-width: 2px; -fx-border-radius: 8px;");

        usernameVBox.getChildren().addAll(usernameLabel, usernameTextField);
        passwordVBox.getChildren().addAll(passwordLabel, passwordField);
        allElementsVBox.getChildren().addAll(usernameVBox, passwordVBox, warningLabel, loginButton);

        usernameVBox.setAlignment(Pos.CENTER_LEFT);
        passwordVBox.setAlignment(Pos.CENTER_LEFT);
        allElementsVBox.setAlignment(Pos.CENTER);
        warningLabel.setAlignment(Pos.CENTER);

        usernameVBox.setPadding(new Insets(0,0,0,35));
        passwordVBox.setPadding(new Insets(0,0,0,35));

        // Layout
        StackPane layout = (StackPane) getRoot();
        layout.getChildren().addAll(backgroundImageView, allElementsVBox);

    }

    private static double getScreenWidth() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    }

    // Getters for Buttons
    public Button getLoginButton() {
        return loginButton;
    }

    public Label getWarningLabel() {
        return warningLabel;
    }

    public String getUsernameTextFieldText() {
        return usernameTextField.getText();
    }

    public String getPasswordTextFieldText() {
        return passwordField.getText();
    }
}
