package com.example.demo.View.Frames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.*;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class FriendProfileFrame extends Scene {

    private NavigateBar navigateBar;
    private ArrayList<Node> friendNodes;
    private ArrayList<Node> favSongNodes;
    private ArrayList<Node> recentTunedNodes;
    private NodeScroller friendScroller;
    private NodeScroller favSongScroller;
    private NodeScroller recentTunedScroller;
    private VBox containerVBox;
    private VBox profileVBox;
    private VBox nodeScrollersVBox;
    private ImageView profileImageView;
    private TuneUser currentFriend;
    private Image profileImage;
    private TuneUser currentUser;

    public FriendProfileFrame(TuneUser friend, TuneUser currentUser) {
        super(new HBox(40), getScreenWidth() , getScreenHeight());
        currentFriend = friend;
        currentUser = currentUser;
        navigateBar = new NavigateBar(currentUser);

        friendNodes = new ArrayList<Node>();
        favSongNodes = new ArrayList<Node>();
        recentTunedNodes = new ArrayList<Node>();


        //VBoxes
        profileVBox = new VBox();
        profileVBox.setSpacing(3);
        profileVBox.setPadding(new Insets(70,0,0,20));
        constructImageContainer();

        nodeScrollersVBox = new VBox();
        nodeScrollersVBox.setSpacing(50);
        nodeScrollersVBox.setPadding(new Insets(60,0,0,0));

        System.out.println(getScreenWidth());


        createNodeScrollers();

        // Layout
        HBox layout = (HBox) getRoot();
        layout.getChildren().addAll(navigateBar, profileVBox, nodeScrollersVBox);

        // Alignments
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
    }



    private void createImage(){
        profileImageView = new ImageView(currentFriend.getProfileImage());
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitWidth(160);
        profileImageView.setClip(new Circle(80, 80, 80));
    }

    public void constructImageContainer() {
        profileVBox.getChildren().clear();
        createImage();

        Label friendName = new Label(currentFriend.getUsername());
        friendName.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        friendName.setAlignment(Pos.CENTER);
        friendName.setTextAlignment(TextAlignment.CENTER);
        profileVBox.getChildren().addAll(profileImageView, friendName);
    }

    private void createNodeScrollers() {
        for (TuneUser friend : currentFriend.getFriends()) {
            FriendNode friendBox = new FriendNode(friend, false);
            friendNodes.add(friendBox);
        }

        for (Song favSong : currentFriend.getFavouriteSongs()) {
            SongNode favSongNode = new SongNode(favSong, 120, 120, true);
            favSongNodes.add(favSongNode);
        }

        for (Song recentTunedSong : currentFriend.getTunedSongs()) {
            SongNode recentTunedSongNode = new SongNode(recentTunedSong, 120, 120, true);
            recentTunedNodes.add(recentTunedSongNode);
        }

        friendScroller = new NodeScroller("Friends", friendNodes,(int) (getScreenWidth() - getScreenWidth()/5.5 - 370));
        favSongScroller = new NodeScroller("Favorite Songs", favSongNodes, 750);
        recentTunedScroller = new NodeScroller("Recent Tuned Songs", recentTunedNodes, 750);

        nodeScrollersVBox.getChildren().addAll(friendScroller, favSongScroller, recentTunedScroller);
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

    private static double getScreenWidth() {
        return getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return getPrimary().getVisualBounds().getHeight();
    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    public NodeScroller getFriendScroller() {
        return friendScroller;
    }

    public NodeScroller getFavSongScroller() {
        return favSongScroller;
    }

    public NodeScroller getRecentTunedScroller() {
        return recentTunedScroller;
    }
}
