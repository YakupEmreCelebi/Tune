package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.TuneUser;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import static javafx.stage.Screen.getPrimary;

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
    private String currentFrame;
    private TuneUser currentUser;
    private ImageView tuneImgView;
    private VBox buttonNLabel;

    public NavigateBar(TuneUser currentUser) {

        this.currentUser = currentUser;
        topContents = new VBox(20);

        // Set Navigate Bar VBox (Color, size)
        this.setStyle("-fx-border-style: hidden solid hidden hidden; -fx-border-width: 1.5; -fx-border-color: black;");
        this.setMaxWidth(getScreenWidth() / 5.5);
        this.setPrefHeight(getScreenHeight());

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
        homeImageView.setFitWidth(45);

        Image profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/profile_ico.png"));
        profileImageView = new ImageView(profileImage);
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitWidth(45);

        Image tuneImage = new Image(getClass().getResourceAsStream("/com/example/demo/tune_ico.png"));
        tuneImageView = new ImageView(tuneImage);
        tuneImageView.setPreserveRatio(true);
        tuneImageView.setFitWidth(45);

        Image settingsImage = new Image(getClass().getResourceAsStream("/com/example/demo/settings_ico.png"));
        settingsImageView = new ImageView(settingsImage);
        settingsImageView.setPreserveRatio(true);
        settingsImageView.setFitWidth(45);
    }

    private void createButtons() {
        homeButton = new NavBarButton("Home", homeImageView);
        profileButton = new NavBarButton("Profile", profileImageView);
        tuneButton = new NavBarButton("Tune", tuneImageView);
        settingsButton = new NavBarButton("Settings", settingsImageView);

    }

    private void createLabel() {
        label = new Label("Tune");
        label.setPadding(new Insets(20));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
    }

    public void resetUsersTune(TuneUser currentUser) {
        Image img = (!currentUser.getTuneExistence()) ? new Image(getClass().getResourceAsStream("/com/example/demo/plus_ico.png")) : currentUser.getTuneSong().getImage();
        tuneImgView.setImage(img);
        addTune.setGraphic(tuneImgView);
        buttonNLabel.getChildren().set(0, addTune);
    }

    public void createUsersTune() {
        usersTune = new BorderPane();
        usersTune.setPrefSize(50,65);
        buttonNLabel = new VBox(20);


        Image img;

        if (!currentUser.getTuneExistence())
        {
            img = new Image(getClass().getResourceAsStream("/com/example/demo/plus_ico.png"));

        }
        else
        {
            img = currentUser.getTuneSong().getImage();
        }


        tuneImgView = new ImageView(img);
        tuneImgView.setPreserveRatio(true);
        tuneImgView.setFitHeight(140);

        Circle clip = new Circle(70,70,70);
        tuneImgView.setClip(clip);

        addTune = new Button();
        addTune.setGraphic(tuneImgView);
        Circle circleClip = new Circle(71, 72, 73);
        addTune.setClip(circleClip);

        addTune.setStyle("-fx-background-color: transparent;");

        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), addTune);
        scaleUp.setToX(1.03);
        scaleUp.setToY(1.03);

        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.1), addTune);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        addTune.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    scaleUp.play();
                    if(tuneImgView.getRotate() % 360 == 0)
                    {
                        animateButtonImage(0,1440, 2.5, tuneImgView);
                    }
                    else
                    {
                        animateButtonImage(tuneImgView.getRotate(),1440,2, tuneImgView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addTune.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                scaleDown.play();
            }
        });



        Label usersTuneLabel = new Label("Your Tune");

        usersTuneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        usersTuneLabel.setAlignment(Pos.CENTER);

        buttonNLabel.setAlignment(Pos.CENTER);
        buttonNLabel.getChildren().setAll(addTune,usersTuneLabel);

        usersTune.setCenter(buttonNLabel);
    }

    private void animateButtonImage(double start, double end, double duration, ImageView imageView) {
        Transition buttonImageAnim = new Transition() {
            {
                setCycleDuration(Duration.seconds(duration));
            }

            @Override
            protected void interpolate(double frac) {
                double value = start + (end - start) * frac * frac;
                imageView.setRotate(value);
            }
        };
        buttonImageAnim.play();
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!button.getText().equals(currentFrame))
                {
                    button.setStyle("-fx-background-color: #bfbfbf; -fx-border-radius: 10; -fx-background-radius: 10");
                }

            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(!button.getText().equals(currentFrame))
                {
                    button.setStyle("-fx-background-color: transparent; -fx-border-radius: 10; -fx-background-radius: 10");
                }

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
    public Button getAddTuneButton() {
        return addTune;
    }

    class NavBarButton extends Button {

        private final double BUTTON_WIDTH = getScreenWidth() / 5.5 -5 ;
        private final double BUTTON_HEIGHT = 45;

        private FadeTransition fadeUp;
        private FadeTransition fadeDown;

        public NavBarButton(String name, ImageView imgView) {
            super(name, imgView);
            this.setGraphicTextGap(10);
            this.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 22));
            this.setStyle("-fx-background-color: transparent; -fx-border-radius: 10; -fx-background-radius: 10");
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

    public void setCurrentFrame(String currentFrame) {
        this.currentFrame = currentFrame;
    }

    private static double getScreenWidth() {
        return getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return getPrimary().getVisualBounds().getHeight();
    }

}

