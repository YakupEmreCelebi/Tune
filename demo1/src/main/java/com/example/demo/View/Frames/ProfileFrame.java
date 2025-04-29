package com.example.demo.View.Frames;

import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.NavigateBar;
import com.example.demo.View.SpecialNodes.NodeScroller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class ProfileFrame extends Scene {

    private NavigateBar navigateBar;
    private ArrayList<Node> scrollerNodes;
    private NodeScroller scroller;
    private Button editProfileButton;
    private VBox containerVBox;
    private VBox profileVBox;
    private VBox nodeScrollersVBox;
    private ImageView profileImageView;
    private TuneUser currentUser;
    private Image profileImage;

    public ProfileFrame(TuneUser tuneUser) {
        super(new HBox(40), getScreenWidth() , getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        currentUser = tuneUser;
        navigateBar = new NavigateBar();
        createImage();
        scrollerNodes = new ArrayList<>();

        //Button
        editProfileButton = new Button("Edit Profile");
        editProfileButton.setStyle("-fx-font-size: 18; -fx-background-color: black; -fx-text-fill: white");
        editProfileButton.setTranslateX(20);

        //VBoxes
        profileVBox = new VBox();
        profileVBox.setSpacing(0);
        profileVBox.setPadding(new Insets(100,0,0,30));
        profileVBox.getChildren().addAll(profileImageView, editProfileButton);

        nodeScrollersVBox = new VBox();
        nodeScrollersVBox.setSpacing(10);
        nodeScrollersVBox.setPadding(new Insets(100,0,0,0));
        nodeScrollersVBox.getChildren().addAll(new NodeScroller(scrollerNodes));


        Button a = new Button("A");
        Button b = new Button("B");
        Button c = new Button("C");
        Button d = new Button("D");

//        a.setPrefWidth(200);
//        b.setPrefWidth(200);
//        c.setPrefWidth(200);
//        d.setPrefWidth(200);
//        a.setPrefHeight(130);
//        b.setPrefHeight(130);
//        c.setPrefHeight(130);
//        d.setPrefHeight(130);
//
//        scrollerNodes.add(a);
//        scrollerNodes.add(b);
//        scrollerNodes.add(c);
//        scrollerNodes.add(d);
//
//        scroller = new NodeScroller(scrollerNodes);


        // Layout
        HBox layout = (HBox) getRoot();
        layout.getChildren().addAll(navigateBar, profileVBox, nodeScrollersVBox);

        // Alignments
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
    }

    private void createImage(){
        profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/megadeth_ico.jpg"));
        profileImageView = new ImageView(profileImage);
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitWidth(180);
        profileImageView.setClip(new Circle(80, 80, 80));
    }

    private static double getScreenWidth() {
        return getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return getPrimary().getVisualBounds().getHeight();
    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    public NodeScroller getScroller() {
        return scroller;
    }

    public Button getEditProfileButton() {
        return editProfileButton;
    }
}
