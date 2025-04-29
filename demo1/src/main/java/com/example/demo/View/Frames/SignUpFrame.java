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

import static javafx.stage.Screen.getPrimary;

public class SignUpFrame extends Scene {

    private Button signupButton;
    private TextField usernameTextField;
    private TextField emailTextField;
    private PasswordField passwordField;
    private Label tuneLabel;
    private Label registrationLabel;
    private Label emailLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label warningLabel;
    private ImageView backgroundImageView;

    public SignUpFrame() {
        super(new StackPane(), getScreenWidth(), getScreenHeight());
        this.getStylesheets().add(getClass().getResource("welcomeFrameStyle.css").toExternalForm());

        // Label for the text
        tuneLabel = new Label("Tune");
        registrationLabel = new Label("Registration");
        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");
        emailLabel = new Label("Email");
        warningLabel = new Label("Username or email already exists");

        usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
        passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
        emailLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
        warningLabel.setStyle("-fx-font-size: 15; -fx-text-fill: red; ");
        warningLabel.setVisible(false);

        // Buttons
        signupButton = new GeneralButton("Sign Up");

        // Text fields for username and password
        usernameTextField = new TextField();
        passwordField = new PasswordField();
        emailTextField = new TextField();

        usernameTextField.setPromptText("Username");
        passwordField.setPromptText("Password");
        emailTextField.setPromptText("Email");

        usernameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #8e8989; -fx-border-width: 1px; -fx-border-radius: 5px;");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: #8e8989; -fx-border-width: 1px; -fx-border-radius: 5px;");
        emailTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #8e8989; -fx-border-width: 1px; -fx-border-radius: 5px;");

        usernameTextField.setMaxWidth(getScreenWidth()/2 - 70);
        passwordField.setMaxWidth(getScreenWidth()/2 - 70);
        emailTextField.setMaxWidth(getScreenWidth()/2 - 70);
        usernameTextField.setPrefHeight(38);
        passwordField.setPrefHeight(38);
        emailTextField.setPrefHeight(38);

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
        VBox emailVBox = new VBox(20);
        VBox allElementsVBox = new VBox(35);

        allElementsVBox.setMaxWidth(getScreenWidth()/2);
        allElementsVBox.setMaxHeight(570);
        allElementsVBox.setStyle("-fx-border-color: #9f9b9b; -fx-border-width: 2px; -fx-border-radius: 8px;");

        usernameVBox.getChildren().addAll(usernameLabel, usernameTextField);
        passwordVBox.getChildren().addAll(passwordLabel, passwordField);
        emailVBox.getChildren().addAll(emailLabel, emailTextField);
        allElementsVBox.getChildren().addAll(usernameVBox, passwordVBox, emailVBox, warningLabel, signupButton);

        usernameVBox.setAlignment(Pos.CENTER_LEFT);
        passwordVBox.setAlignment(Pos.CENTER_LEFT);
        emailVBox.setAlignment(Pos.CENTER_LEFT);
        allElementsVBox.setAlignment(Pos.CENTER);
        usernameLabel.setAlignment(Pos.CENTER);

        usernameVBox.setPadding(new Insets(0,0,0,35));
        passwordVBox.setPadding(new Insets(0,0,0,35));
        emailVBox.setPadding(new Insets(0,0,0,35));

        // Layout
        StackPane layout = (StackPane) getRoot();
        layout.getChildren().addAll(backgroundImageView, allElementsVBox);

    }

    private static double getScreenWidth() {
        return getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return getPrimary().getVisualBounds().getHeight();
    }

    // Getters for Buttons
    public Button getSignupButton() {
        return signupButton;
    }

    public String getEmailTextFieldText() {
        return emailTextField.getText();
    }

    public String getUsernameTextFieldText() {
        return usernameTextField.getText();
    }
    public String getPasswordTextFieldText() {
        return passwordField.getText();
    }

    public Label getWarningLabel() {
        return warningLabel;
    }
}
