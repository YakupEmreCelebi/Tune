package com.example.demo.View.PopUpFrames;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.swing.*;

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

    Rectangle overlay;
    String choice = "None";
    HBox currentHBox = null;


    public PopUpQuestion(String question, String option1, String option2, String option3, String option4, String buttonText) {
        super(1000,600);

        // Button
        nextButton = new Button(buttonText);
        nextButton.setStyle("-fx-background-color: #B694EA; -fx-text-fill: white; -fx-font-size: 15; -fx-font-family: Arial; -fx-font-weight: bold; -fx-border-radius: 15; -fx-background-radius: 15");
        nextButton.setPrefWidth(500);
        nextButton.setPrefHeight(44);
        nextButton.setTranslateY(10);
        addHoverEffect(nextButton);



        // Image HBoxes
        imageHBox1 = new HBox();
        imageHBox2 = new HBox();

        // Question elements
        questionLabel = new Label(question);
        questionLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22; -fx-font-weight: bold; ");

        questionHBox = new HBox();
        questionHBox.getChildren().add(questionLabel);
        questionHBox.setStyle("-fx-background-color: transparent");
        questionHBox.setPrefHeight(60);
        questionHBox.setAlignment(Pos.CENTER);
        questionHBox.setPadding(new Insets(0,0,0,10));

        // Option 1 elements
        option1Label = new Label(option1);
        option1Label.setStyle("-fx-text-fill: #887EB9; -fx-font-size: 18");

        option1HBox = new HBox();
        setHBox(option1HBox, option1Label);


        // Option 2 elements
        option2Label = new Label(option2);
        option2Label.setStyle("-fx-text-fill: #887EB9; -fx-font-size: 18");

        option2HBox = new HBox();
        setHBox(option2HBox, option2Label);

        // Option 3 elements
        option3Label = new Label(option3);
        option3Label.setStyle("-fx-text-fill:  #887EB9; -fx-font-size: 18");

        option3HBox = new HBox();
        setHBox(option3HBox, option3Label);

        // Option 4 elements
        option4Label = new Label(option4);
        option4Label.setStyle("-fx-text-fill: #887EB9; -fx-font-size: 18");

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
        mainVBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10");
        mainVBox.setPadding(new Insets(0,30,0,30));

        createImages();

        imageHBox1.getChildren().addAll(backgroundImageView1, backgroundImageView2);
        imageHBox2.getChildren().addAll(backgroundImageView3, backgroundImageView4);
        imageVBox.getChildren().addAll(imageHBox1, imageHBox2);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView5, mainVBox);
        stackPane.setAlignment(mainVBox, Pos.CENTER);
        stackPane.setAlignment(backgroundImageView5, Pos.CENTER);


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

        Image backgroundImage5 = new Image(getClass().getResourceAsStream("/com/example/demo/questionpopup_background.jpg"));
        backgroundImageView5 = new ImageView(backgroundImage5);
        backgroundImageView5.setFitHeight(600);
        backgroundImageView5.setFitWidth(1000);
        backgroundImageView5.setPreserveRatio(false);

    }

    public void setHBox(HBox hBox, Label label){
        hBox.setOnMouseClicked(mouseEvent -> {currentHBox = hBox;
            clearHBoxChoice(option1HBox);
            clearHBoxChoice(option2HBox);
            clearHBoxChoice(option3HBox);
            clearHBoxChoice(option4HBox);

            if(currentHBox == hBox){
                hBox.setStyle("-fx-border-color: #887EB9; -fx-border-width: 2; -fx-background-color: #F2F0FE; -fx-background-radius: 3; -fx-border-radius: 3");
            }

            if(currentHBox == option1HBox){
                choice = option1Label.getText();
            }
            if(currentHBox == option2HBox){
                choice = option2Label.getText();
            }
            if(currentHBox == option3HBox){
                choice = option3Label.getText();
            }
            if(currentHBox == option4HBox){
                choice = option4Label.getText();
            }

            System.out.println(choice);

            });

        hBox.getChildren().add(label);
        hBox.setStyle("-fx-border-color: #d1d1d1; -fx-border-width: 1; -fx-background-color: transparent; -fx-background-radius: 3; -fx-border-radius: 3");
        hBox.setPrefHeight(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,0,10));

        addHoverEffect(hBox);

    }

    private void clearHBoxChoice(HBox hBox){

        hBox.setStyle("-fx-border-color: #d1d1d1; -fx-border-width: 1; -fx-background-color: transparent; -fx-background-radius: 3; -fx-border-radius: 3");
    }

    private void addHoverEffect(HBox hBox) {
        hBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                hBox.setStyle("-fx-border-color: #887EB9; -fx-border-width: 2; -fx-background-color: #F2F0FE; -fx-background-radius: 3; -fx-border-radius: 3");
            }
        });

        hBox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(currentHBox != hBox){
                    hBox.setStyle("-fx-border-color: #d1d1d1; -fx-border-width: 1; -fx-background-color: transparent; -fx-background-radius: 3; -fx-border-radius: 3");
                }

            }
        });
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

    public Button getNextButton() {
        return nextButton;
    }

    public String getChoice() {
        return choice;
    }
}
