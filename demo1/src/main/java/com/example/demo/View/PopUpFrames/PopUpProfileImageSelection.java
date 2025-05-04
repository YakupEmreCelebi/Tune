package com.example.demo.View.PopUpFrames;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

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
    private Button selectButton;

    private HBox hBox1;
    private HBox hBox2;

    public PopUpProfileImageSelection() {
        super(1000, 700);

        selectedImageIndex = 0;

        createImages();

        selectButton = new Button("Select Image");

        hBox1 = new HBox(10);
        hBox2 = new HBox(10);

        hBox1.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);
        hBox2.getChildren().addAll(imageView5, imageView6, imageView7, imageView8);

        VBox layout = (VBox) getRoot();
        layout.setSpacing(80);
        layout.setPadding(new Insets(100,10,30,75));
        layout.setStyle("-fx-background-color: black;");

        layout.getChildren().addAll(hBox1, hBox2, selectButton);


    }

    private void createImages() {

        Image image1 = new Image(getClass().getResourceAsStream("/com/example/demo/duman_ico.jpg"));
        imageView1 = new ImageView(image1);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(200);
        imageView1.setClip(new Circle(80,80,80));
        imageView1.setOnMouseEntered(event -> {imageView1.setOpacity(0.5);});
        imageView1.setOnMouseExited(event -> {imageView1.setOpacity(1);});
        imageView1.setOnMouseClicked(event -> {setSelectedImageIndex(1);});

        Image image2 = new Image(getClass().getResourceAsStream("/com/example/demo/duman1_ico.jpg"));
        imageView2 = new ImageView(image2);
        imageView2.setPreserveRatio(true);
        imageView2.setFitWidth(200);
        imageView2.setClip(new Circle(80, 80, 80));
        imageView2.setOnMouseEntered(event -> {imageView2.setOpacity(0.5);});
        imageView2.setOnMouseExited(event -> {imageView2.setOpacity(1);});
        imageView2.setOnMouseClicked(event -> {setSelectedImageIndex(2);});

        Image image3 = new Image(getClass().getResourceAsStream("/com/example/demo/duman2_ico.jpg"));
        imageView3 = new ImageView(image3);
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(200);
        imageView3.setClip(new Circle(80, 80, 80));
        imageView3.setOnMouseEntered(event -> {imageView3.setOpacity(0.5);});
        imageView3.setOnMouseExited(event -> {imageView3.setOpacity(1);});
        imageView3.setOnMouseClicked(event -> {setSelectedImageIndex(3);});

        Image image4 = new Image(getClass().getResourceAsStream("/com/example/demo/senikendimesakladım_ico.jpg"));
        imageView4 = new ImageView(image4);
        imageView4.setPreserveRatio(true);
        imageView4.setFitWidth(200);
        imageView4.setClip(new Circle(80, 80, 80));
        imageView4.setOnMouseEntered(event -> {imageView4.setOpacity(0.5);});
        imageView4.setOnMouseExited(event -> {imageView4.setOpacity(1);});
        imageView4.setOnMouseClicked(event -> {setSelectedImageIndex(4);});

        Image image5 = new Image(getClass().getResourceAsStream("/com/example/demo/metallica_ico.jpg"));
        imageView5 = new ImageView(image5);
        imageView5.setPreserveRatio(true);
        imageView5.setFitWidth(200);
        imageView5.setClip(new Circle(80, 80, 80));
        imageView5.setOnMouseEntered(event -> {imageView5.setOpacity(0.5);});
        imageView5.setOnMouseExited(event -> {imageView5.setOpacity(1);});
        imageView5.setOnMouseClicked(event -> {setSelectedImageIndex(5);});

        Image image6 = new Image(getClass().getResourceAsStream("/com/example/demo/şebnemferah_ico.jpg"));
        imageView6 = new ImageView(image6);
        imageView6.setPreserveRatio(true);
        imageView6.setFitWidth(200);
        imageView6.setClip(new Circle(80, 80, 80));
        imageView6.setOnMouseEntered(event -> {imageView6.setOpacity(0.5);});
        imageView6.setOnMouseExited(event -> {imageView6.setOpacity(1);});
        imageView6.setOnMouseClicked(event -> {setSelectedImageIndex(6);});

        Image image7 = new Image(getClass().getResourceAsStream("/com/example/demo/megadeth_ico.jpg"));
        imageView7 = new ImageView(image7);
        imageView7.setPreserveRatio(true);
        imageView7.setFitWidth(200);
        imageView7.setClip(new Circle(80, 80, 80));
        imageView7.setOnMouseEntered(event -> {imageView7.setOpacity(0.5);});
        imageView7.setOnMouseExited(event -> {imageView7.setOpacity(1);});
        imageView7.setOnMouseClicked(event -> {setSelectedImageIndex(7);});

        Image image8 = new Image(getClass().getResourceAsStream("/com/example/demo/judaspriest_ico.jpg"));
        imageView8 = new ImageView(image8);
        imageView8.setPreserveRatio(true);
        imageView8.setFitWidth(200);
        imageView8.setClip(new Circle(80, 80, 80));
        imageView8.setOnMouseEntered(event -> {imageView8.setOpacity(0.5);});
        imageView8.setOnMouseExited(event -> {imageView8.setOpacity(1);});
        imageView8.setOnMouseClicked(event -> {setSelectedImageIndex(8);});

    }

    public void setSelectedImageIndex(int selectedImageIndex) {
        this.selectedImageIndex = selectedImageIndex;
    }

    public Button getSelectButton() {
        return selectButton;
    }

    public int getSelectedImageIndex() {
        return selectedImageIndex;
    }
}
