package com.example.demo.View.PopUpFrames;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class PopUpProfileImageSelection extends PopUp {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private int selectedImageIndex;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;

    ArrayList<Button> buttons;

    private HBox hBox1;
    private HBox hBox2;

    public PopUpProfileImageSelection() {
        super(1000, 700);

        selectedImageIndex = 0;

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

        for (Button button : buttons) {
            addHoverEffect(button);
            button.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
        }


        hBox1 = new HBox(25);
        hBox2 = new HBox(25);

        hBox1.getChildren().addAll(button1, button2, button3, button4);
        hBox2.getChildren().addAll(button5, button6, button7, button8);

        // Layout
        VBox layout = (VBox) getRoot();
        layout.setSpacing(100);
        layout.setPadding(new Insets(100,10,30,75));
        layout.setStyle("-fx-background-color: black;");

        layout.getChildren().addAll(hBox1, hBox2);


    }

    private void createImagesAndButtons() {

        Image image1 = new Image(getClass().getResourceAsStream("/com/example/demo/duman_ico.jpg"));
        imageView1 = new ImageView(image1);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(160);
        imageView1.setClip(new Circle(80,80,80));
        button1 = new Button("", imageView1);
        button1.setShape(new Circle(1000));
        button1.setMinSize(160, 160);
        button1.setMaxSize(160, 160);
        button1.setPrefSize(160, 160);
        imageView1.setOnMouseEntered(event -> {imageView1.setOpacity(0.5);});
        imageView1.setOnMouseExited(event -> {imageView1.setOpacity(1);});
        imageView1.setOnMouseClicked(event -> {setSelectedImageIndex(1);});

        Image image2 = new Image(getClass().getResourceAsStream("/com/example/demo/duman1_ico.jpg"));
        imageView2 = new ImageView(image2);
        imageView2.setPreserveRatio(true);
        imageView2.setFitWidth(160);
        imageView2.setClip(new Circle(80, 80, 80));
        button2 = new Button("", imageView2);
        button2.setShape(new Circle(1000));
        button2.setMinSize(160, 160);
        button2.setMaxSize(160, 160);
        imageView2.setOnMouseEntered(event -> {imageView2.setOpacity(0.5);});
        imageView2.setOnMouseExited(event -> {imageView2.setOpacity(1);});
        imageView2.setOnMouseClicked(event -> {setSelectedImageIndex(2);});

        Image image3 = new Image(getClass().getResourceAsStream("/com/example/demo/duman2_ico.jpg"));
        imageView3 = new ImageView(image3);
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(160);
        imageView3.setClip(new Circle(80, 80, 80));
        button3 = new Button("", imageView3);
        button3.setShape(new Circle(1000));
        button3.setMinSize(160, 160);
        button3.setMaxSize(160, 160);
        imageView3.setOnMouseEntered(event -> {imageView3.setOpacity(0.5);});
        imageView3.setOnMouseExited(event -> {imageView3.setOpacity(1);});
        imageView3.setOnMouseClicked(event -> {setSelectedImageIndex(3);});

        Image image4 = new Image(getClass().getResourceAsStream("/com/example/demo/senikendimesakladım_ico.jpg"));
        imageView4 = new ImageView(image4);
        imageView4.setPreserveRatio(true);
        imageView4.setFitWidth(160);
        imageView4.setClip(new Circle(80, 80, 80));
        button4 = new Button("", imageView4);
        button4.setShape(new Circle(1000));
        button4.setMinSize(160, 160);
        button4.setMaxSize(160, 160);
        imageView4.setOnMouseEntered(event -> {imageView4.setOpacity(0.5);});
        imageView4.setOnMouseExited(event -> {imageView4.setOpacity(1);});
        imageView4.setOnMouseClicked(event -> {setSelectedImageIndex(4);});

        Image image5 = new Image(getClass().getResourceAsStream("/com/example/demo/metallica_ico.jpg"));
        imageView5 = new ImageView(image5);
        imageView5.setPreserveRatio(true);
        imageView5.setFitWidth(160);
        imageView5.setClip(new Circle(80, 80, 80));
        button5 = new Button("", imageView5);
        button5.setShape(new Circle(1000));
        button5.setMinSize(160, 160);
        button5.setMaxSize(160, 160);
        imageView5.setOnMouseEntered(event -> {imageView5.setOpacity(0.5);});
        imageView5.setOnMouseExited(event -> {imageView5.setOpacity(1);});
        imageView5.setOnMouseClicked(event -> {setSelectedImageIndex(5);});

        Image image6 = new Image(getClass().getResourceAsStream("/com/example/demo/şebnemferah_ico.jpg"));
        imageView6 = new ImageView(image6);
        imageView6.setPreserveRatio(true);
        imageView6.setFitWidth(160);
        imageView6.setClip(new Circle(80, 80, 80));
        button6 = new Button("", imageView6);
        button6.setShape(new Circle(1000));
        button6.setMinSize(160, 160);
        button6.setMaxSize(160, 160);
        imageView6.setOnMouseEntered(event -> {imageView6.setOpacity(0.5);});
        imageView6.setOnMouseExited(event -> {imageView6.setOpacity(1);});
        imageView6.setOnMouseClicked(event -> {setSelectedImageIndex(6);});

        Image image7 = new Image(getClass().getResourceAsStream("/com/example/demo/megadeth_ico.jpg"));
        imageView7 = new ImageView(image7);
        imageView7.setPreserveRatio(true);
        imageView7.setFitWidth(160);
        imageView7.setClip(new Circle(80, 80, 80));
        button7 = new Button("", imageView7);
        button7.setShape(new Circle(1000));
        button7.setMinSize(160, 160);
        button7.setMaxSize(160, 160);
        imageView7.setOnMouseEntered(event -> {imageView7.setOpacity(0.5);});
        imageView7.setOnMouseExited(event -> {imageView7.setOpacity(1);});
        imageView7.setOnMouseClicked(event -> {setSelectedImageIndex(7);});

        Image image8 = new Image(getClass().getResourceAsStream("/com/example/demo/judaspriest_ico.jpg"));
        imageView8 = new ImageView(image8);
        imageView8.setPreserveRatio(true);
        imageView8.setFitWidth(160);
        imageView8.setClip(new Circle(80, 80, 80));
        button8 = new Button("", imageView8);
        button8.setShape(new Circle(1000));
        button8.setMinSize(160, 160);
        button8.setMaxSize(160, 160);
        imageView8.setOnMouseEntered(event -> {imageView8.setOpacity(0.5);});
        imageView8.setOnMouseExited(event -> {imageView8.setOpacity(1);});
        imageView8.setOnMouseClicked(event -> {setSelectedImageIndex(8);});

    }

    public void setSelectedImageIndex(int selectedImageIndex) {
        this.selectedImageIndex = selectedImageIndex;
    }

    public int getSelectedImageIndex() {
        return selectedImageIndex;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
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
