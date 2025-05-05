package com.example.demo.View.PopUpFrames;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PopUpQuestion extends PopUp{

    ImageView backgroundImageView1;
    ImageView backgroundImageView2;
    ImageView backgroundImageView3;
    ImageView backgroundImageView4;
    ImageView backgroundImageView5;

    HBox imageHBox1;
    HBox imageHBox2;
    VBox imageVBox;
    VBox mainVBox;

    HBox questionHBox;
    HBox option1HBox;
    HBox option2HBox;
    HBox option3HBox;
    HBox option4HBox;

    Label questionLabel;
    Label option1Label;
    Label option2Label;
    Label option3Label;
    Label option4Label;

    Button nextButton;


    public PopUpQuestion(String question, String option1, String option2, String option3, String option4) {
        super(1000,600);

        // Button
        nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 15; -fx-font-family: Arial; -fx-font-weight: bold");
        nextButton.setPrefWidth(500);
        nextButton.setPrefHeight(50);
        addHoverEffect(nextButton);

        // Image HBoxes
        imageHBox1 = new HBox();
        imageHBox2 = new HBox();

        // Question elements
        questionLabel = new Label(question);
        questionLabel.setStyle("-fx-text-fill: #290029; -fx-font-size: 21; -fx-font-weight: bold; ");

        questionHBox = new HBox();
        questionHBox.getChildren().add(questionLabel);
        questionHBox.setStyle("-fx-border-color: black; -fx-border-width: 3; -fx-background-color: transparent;  -fx-background-radius: 5; -fx-border-radius: 5");
        questionHBox.setPrefHeight(60);
        questionHBox.setAlignment(Pos.CENTER);
        questionHBox.setPadding(new Insets(0,0,0,10));

        // Option 1 elements
        option1Label = new Label(option1);
        option1Label.setStyle("-fx-text-fill: #290029; -fx-font-size: 18");

        option1HBox = new HBox();
        setHBox(option1HBox, option1Label);

        // Option 2 elements
        option2Label = new Label(option2);
        option2Label.setStyle("-fx-text-fill: #290029; -fx-font-size: 18");

        option2HBox = new HBox();
        setHBox(option2HBox, option2Label);

        // Option 3 elements
        option3Label = new Label(option3);
        option3Label.setStyle("-fx-text-fill:  #290029; -fx-font-size: 18");

        option3HBox = new HBox();
        setHBox(option3HBox, option3Label);

        // Option 4 elements
        option4Label = new Label(option4);
        option4Label.setStyle("-fx-text-fill: #290029; -fx-font-size: 18");

        option4HBox = new HBox();
        if(!option4.equals(""))
        {
            setHBox(option4HBox, option4Label);
        }

        // Image VBox
        imageVBox = new VBox();
        imageVBox.setPrefWidth(1000);
        imageVBox.setPrefHeight(600);

        // Main VBox
        mainVBox = new VBox(16);
        mainVBox.getChildren().addAll(questionHBox, option1HBox, option2HBox, option3HBox, option4HBox, nextButton);
        mainVBox.setMaxWidth(500);
        mainVBox.setMaxHeight(500);
        mainVBox.setAlignment(Pos.CENTER);

        createImages();

        imageHBox1.getChildren().addAll(backgroundImageView1, backgroundImageView2);
        imageHBox2.getChildren().addAll(backgroundImageView3, backgroundImageView4);
        imageVBox.getChildren().addAll(imageHBox1, imageHBox2);


        Rectangle overlay = new Rectangle(1100, 600, Color.rgb(0, 0, 0, 0.7));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView5, mainVBox);
        stackPane.setAlignment(mainVBox, Pos.CENTER);


        //Layout
        VBox layout = (VBox) getRoot();
        layout.setSpacing(10);
        layout.getChildren().addAll(stackPane);


    }

    private void createImages(){

        GaussianBlur blur = new GaussianBlur(3);

        Image backgroundImage1 = new Image(getClass().getResourceAsStream("/com/example/demo/qb.png"));
        backgroundImageView1 = new ImageView(backgroundImage1);
        backgroundImageView1.setFitHeight(300);
        backgroundImageView1.setPreserveRatio(true);
        //backgroundImageView1.setEffect(blur);
       // backgroundImageView1.setOpacity(0.8);

        Image backgroundImage2 = new Image(getClass().getResourceAsStream("/com/example/demo/qb.png"));
        backgroundImageView2 = new ImageView(backgroundImage2);
        backgroundImageView2.setFitHeight(300);
        backgroundImageView2.setPreserveRatio(true);


        Image backgroundImage3 = new Image(getClass().getResourceAsStream("/com/example/demo/qb.png"));
        backgroundImageView3 = new ImageView(backgroundImage3);
        backgroundImageView3.setFitHeight(300);
        backgroundImageView3.setPreserveRatio(true);

        Image backgroundImage4 = new Image(getClass().getResourceAsStream("/com/example/demo/qb.png"));
        backgroundImageView4 = new ImageView(backgroundImage4);
        backgroundImageView4.setFitHeight(300);
        backgroundImageView4.setPreserveRatio(true);

        Image backgroundImage5 = new Image(getClass().getResourceAsStream("/com/example/demo/TuneBackground.png"));
        backgroundImageView5 = new ImageView(backgroundImage5);
        backgroundImageView5.setFitHeight(600);
        backgroundImageView5.setFitWidth(1000);
        backgroundImageView5.setPreserveRatio(false);

    }

    public void setHBox(HBox hBox, Label label){
        hBox.getChildren().add(label);
        hBox.setStyle("-fx-border-color: black; -fx-border-width: 3; -fx-background-color: transparent; -fx-background-radius: 5; -fx-border-radius: 5");
        hBox.setPrefHeight(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,0,10));
        addHoverEffect(hBox);

    }

    private void addHoverEffect(HBox hBox) {
        hBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                hBox.setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-color: transparent; -fx-background-radius: 5; -fx-border-radius: 5");
            }
        });

        hBox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                hBox.setStyle("-fx-border-color: black; -fx-border-width: 3; -fx-background-color: transparent; -fx-background-radius: 5; -fx-border-radius: 5");
            }
        });
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                button.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 15; -fx-font-family: Arial; -fx-font-weight: bold");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 15; -fx-font-family: Arial; -fx-font-weight: bold");
            }
        });
    }
}
