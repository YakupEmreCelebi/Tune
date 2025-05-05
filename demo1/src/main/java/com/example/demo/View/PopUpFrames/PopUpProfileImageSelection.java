package com.example.demo.View.PopUpFrames;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class PopUpProfileImageSelection extends PopUp {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;

    Label box1Title;
    Label box2Title;
    Label box3Title;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;

    ArrayList<Button> buttons;

    HBox hBox1;
    HBox hBox2;
    HBox hBox3;

    VBox vBox1;
    VBox vBox2;
    VBox vBox3;

    VBox mainVBox;



    public PopUpProfileImageSelection() {
        super(840,700);

        buttons = new ArrayList<>();
        createImagesAndButtons();

        // Add buttons to arraylist
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        buttons.add(button10);
        buttons.add(button11);
        buttons.add(button12);

        for (Button button : buttons) {
            addHoverEffect(button);
            button.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
        }

        // Labels
        box1Title = new Label("Duman");
        box2Title = new Label("Metallica");
        box3Title = new Label("AC/DC");

        box1Title.setStyle("-fx-font-size: 23; -fx-text-fill: white; -fx-font-weight: bold");
        box2Title.setStyle("-fx-font-size: 23; -fx-text-fill: white; -fx-font-weight: bold");
        box3Title.setStyle("-fx-font-size: 23; -fx-text-fill: white; -fx-font-weight: bold");

        // HBoxes
        hBox1 = new HBox(25);
        hBox2 = new HBox(25);
        hBox3 = new HBox(25);

        hBox1.getChildren().addAll(button1, button2, button3, button4);
        hBox2.getChildren().addAll(button5, button6, button7, button8);
        hBox3.getChildren().addAll(button9, button10, button11, button12);

        //VBoxes
        vBox1 = new VBox(3);
        vBox2 = new VBox(3);
        vBox3 = new VBox(3);

        vBox1.getChildren().addAll(box1Title, hBox1);
        vBox2.getChildren().addAll(box2Title, hBox2);
        vBox3.getChildren().addAll(box3Title, hBox3);

        // Layout

        mainVBox = new VBox(75);
        mainVBox.setPadding(new Insets(100,10,30,60));
        mainVBox.setStyle("-fx-background-color: black;");
        mainVBox.getChildren().addAll(vBox1, vBox2, vBox3);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: black; -fx-border-color: transparent;");


        ((VBox) getRoot()).getChildren().add(scrollPane);

    }

    private void createImagesAndButtons() {

        Image image1 = new Image(getClass().getResourceAsStream("/com/example/demo/duman_ico.jpg"));
        imageView1 = new ImageView(image1);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(160);
        imageView1.setClip(new Circle(80,80,80));
        button1 = new Button("", imageView1);
        button1.setShape(new Circle(1000));
        button1.setMinSize(164, 164);
        button1.setMaxSize(164, 164);

        Image image2 = new Image(getClass().getResourceAsStream("/com/example/demo/duman1_ico.jpg"));
        imageView2 = new ImageView(image2);
        imageView2.setPreserveRatio(true);
        imageView2.setFitWidth(160);
        imageView2.setClip(new Circle(80, 80, 80));
        button2 = new Button("", imageView2);
        button2.setShape(new Circle(1000));
        button2.setMinSize(164, 164);
        button2.setMaxSize(164, 164);

        Image image3 = new Image(getClass().getResourceAsStream("/com/example/demo/duman2_ico.jpg"));
        imageView3 = new ImageView(image3);
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(160);
        imageView3.setClip(new Circle(80, 80, 80));
        button3 = new Button("", imageView3);
        button3.setShape(new Circle(1000));
        button3.setMinSize(164, 164);
        button3.setMaxSize(164, 164);

        Image image4 = new Image(getClass().getResourceAsStream("/com/example/demo/senikendimesakladım_ico.jpg"));
        imageView4 = new ImageView(image4);
        imageView4.setPreserveRatio(true);
        imageView4.setFitWidth(160);
        imageView4.setClip(new Circle(80, 80, 80));
        button4 = new Button("", imageView4);
        button4.setShape(new Circle(1000));
        button4.setMinSize(164, 164);
        button4.setMaxSize(164, 164);

        Image image5 = new Image(getClass().getResourceAsStream("/com/example/demo/metallica_ico.jpg"));
        imageView5 = new ImageView(image5);
        imageView5.setPreserveRatio(true);
        imageView5.setFitWidth(160);
        imageView5.setClip(new Circle(80, 80, 80));
        button5 = new Button("", imageView5);
        button5.setShape(new Circle(1000));
        button5.setMinSize(164, 164);
        button5.setMaxSize(164, 164);

        Image image6 = new Image(getClass().getResourceAsStream("/com/example/demo/metallica2_ico.jpg"));
        imageView6 = new ImageView(image6);
        imageView6.setPreserveRatio(true);
        imageView6.setFitWidth(160);
        imageView6.setClip(new Circle(80, 80, 80));
        button6 = new Button("", imageView6);
        button6.setShape(new Circle(1000));
        button6.setMinSize(164, 164);
        button6.setMaxSize(164, 164);

        Image image7 = new Image(getClass().getResourceAsStream("/com/example/demo/metallica3_ico.jpg"));
        imageView7 = new ImageView(image7);
        imageView7.setPreserveRatio(true);
        imageView7.setFitWidth(160);
        imageView7.setClip(new Circle(80, 80, 80));
        button7 = new Button("", imageView7);
        button7.setShape(new Circle(1000));
        button7.setMinSize(164, 164);
        button7.setMaxSize(164, 164);

        Image image8 = new Image(getClass().getResourceAsStream("/com/example/demo/metallica4_ico.jpg"));
        imageView8 = new ImageView(image8);
        imageView8.setPreserveRatio(true);
        imageView8.setFitWidth(160);
        imageView8.setClip(new Circle(80, 80, 80));
        button8 = new Button("", imageView8);
        button8.setShape(new Circle(1000));
        button8.setMinSize(164, 164);
        button8.setMaxSize(164, 164);

        Image image9 = new Image(getClass().getResourceAsStream("/com/example/demo/acdc_ico.jpg"));
        imageView9 = new ImageView(image9);
        imageView9.setPreserveRatio(true);
        imageView9.setFitWidth(160);
        imageView9.setClip(new Circle(80, 80, 80));
        button9 = new Button("", imageView9);
        button9.setShape(new Circle(1000));
        button9.setMinSize(164, 164);
        button9.setMaxSize(164, 164);

        Image image10 = new Image(getClass().getResourceAsStream("/com/example/demo/acdc2_ico.jpg"));
        imageView10 = new ImageView(image10);
        imageView10.setPreserveRatio(true);
        imageView10.setFitWidth(160);
        imageView10.setClip(new Circle(80, 80, 80));
        button10 = new Button("", imageView10);
        button10.setShape(new Circle(1000));
        button10.setMinSize(164, 164);
        button10.setMaxSize(164, 164);

        Image image11 = new Image(getClass().getResourceAsStream("/com/example/demo/acdc3_ico.jpg"));
        imageView11 = new ImageView(image11);
        imageView11.setPreserveRatio(true);
        imageView11.setFitWidth(160);
        imageView11.setClip(new Circle(80, 80, 80));
        button11 = new Button("", imageView11);
        button11.setShape(new Circle(1000));
        button11.setMinSize(164, 164);
        button11.setMaxSize(164, 164);

        Image image12 = new Image(getClass().getResourceAsStream("/com/example/demo/acdc4_ico.jpg"));
        imageView12 = new ImageView(image12);
        imageView12.setPreserveRatio(true);
        imageView12.setFitWidth(160);
        imageView12.setClip(new Circle(80, 80, 80));
        button12 = new Button("", imageView12);
        button12.setShape(new Circle(1000));
        button12.setMinSize(164, 164);
        button12.setMaxSize(164, 164);


    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    private void addHoverEffect(Button button){
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                // To get different result make background color: transparent
                button.setStyle("-fx-background-color: white; -fx-border-color: white;");

            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
            }
        });
    }
}
