package com.example.demo.View.SpecialNodes;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class NavigateBar extends BorderPane {

    private Label label;
    private Button homeButton;
    private Button profileButton;
    private Button tuneButton;
    private Button settingsButton;
    private ImageView homeImageView;
    private ImageView profileImageView;
    private ImageView tuneImageView;
    private ImageView settingsImageView;
    private VBox topContents;
    private BorderPane usersTune;
    private Button addTune;

    public NavigateBar() {

        topContents = new VBox(10);

        // Set Navigate Bar VBox (Color, size)
        this.setStyle("-fx-border-style: hidden solid hidden hidden; -fx-border-width: 1.5; -fx-border-color: black;");
        this.setMaxWidth(200);
        this.setPrefHeight(1000);

        createImages(); // Images
        createButtons(); // Buttons
        createLabel(); // Label
        createUsersTune(); // UsersTune

        topContents.getChildren().addAll(label, homeButton, profileButton, tuneButton);

        // Adding label and buttons to VBox
        this.setTop(topContents);
        this.setCenter(usersTune);
        this.setBottom(settingsButton);

        addHoverEffect(homeButton);
        addHoverEffect(profileButton);
        addHoverEffect(tuneButton);
        addHoverEffect(settingsButton);

    }

    private void createImages() {
        Image homeImage = new Image(getClass().getResourceAsStream("/com/example/demo/home_ico.png"));
        homeImageView = new ImageView(homeImage);
        homeImageView.setPreserveRatio(true);
        homeImageView.setFitWidth(25);

        Image profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/profile_ico.png"));
        profileImageView = new ImageView(profileImage);
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitWidth(25);

        Image tuneImage = new Image(getClass().getResourceAsStream("/com/example/demo/tune_ico.png"));
        tuneImageView = new ImageView(tuneImage);
        tuneImageView.setPreserveRatio(true);
        tuneImageView.setFitWidth(25);

        Image settingsImage = new Image(getClass().getResourceAsStream("/com/example/demo/settings_ico.png"));
        settingsImageView = new ImageView(settingsImage);
        settingsImageView.setPreserveRatio(true);
        settingsImageView.setFitWidth(25);
    }

    private void createButtons() {
        homeButton = new NavBarButton("Home", homeImageView);
        profileButton = new NavBarButton("Profile", profileImageView);
        tuneButton = new NavBarButton("Tune", tuneImageView);
        settingsButton = new NavBarButton("Settings", settingsImageView);

    }

    private void createLabel() {
        label = new Label("Tune");
        label.setPadding(new Insets(10));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
    }

    private void createUsersTune() {
        usersTune = new BorderPane();
        usersTune.setPrefSize(50,65);
        VBox buttonNLabel = new VBox(10);

        Image plusImg = new Image(getClass().getResourceAsStream("/com/example/demo/plus_ico.png"));
        ImageView plusImgView = new ImageView(plusImg);
        addTune = new Button("", plusImgView);

        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), addTune);
        scaleUp.setToX(1.03);
        scaleUp.setToY(1.03);

        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.1), addTune);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        addTune.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleUp.play();
            }
        });

        addTune.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleDown.play();
            }
        });

        Label usersTuneLabel = new Label("Your Tune");

        usersTuneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        usersTuneLabel.setAlignment(Pos.CENTER);

        buttonNLabel.getChildren().addAll(addTune,usersTuneLabel);
        buttonNLabel.setAlignment(Pos.CENTER);
        usersTune.setCenter(buttonNLabel);

    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                button.setStyle("-fx-background-color: #dadada;");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                button.setStyle(String.format("-fx-background-color: transparent;"));
            }
        });
    }

    // Getters for Buttons
    public Button getHomeButton() {
        return homeButton;
    }
    public Button getProfileButton() {
        return profileButton;
    }
    public Button getTuneButton() {
        return tuneButton;
    }
    public Button getSettingsButton() {
        return settingsButton;
    }


    class NavBarButton extends Button {

        private final int BUTTON_WIDTH = 200;
        private final int BUTTON_HEIGHT = 40;

        private FadeTransition fadeUp;
        private FadeTransition fadeDown;

        public NavBarButton(String name, ImageView imgView) {
            super(name, imgView);
            this.setGraphicTextGap(10);
            this.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
            this.setAlignment(Pos.BASELINE_LEFT);
            this.setPrefWidth(BUTTON_WIDTH);
            this.setPrefHeight(BUTTON_HEIGHT);
            setHoverEffects();
        }


        private void createHoverEffects() {

            fadeUp = new FadeTransition(Duration.millis(300), this);
            fadeUp.setToValue(6.0);

            fadeDown = new FadeTransition(Duration.millis(300), this);
            fadeDown.setToValue(1);
        }

        private void setHoverEffects() {
            this.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fadeUp.play();
                }
            });

            this.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fadeDown.play();
                }
            });
        }




    }

    private static double getScreenWidth() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    }

}

