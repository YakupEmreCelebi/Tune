package com.example.demo.View.Frames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class ProfileFrame extends Scene {

    private NavigateBar navigateBar;
    private ArrayList<Node> friendNodes;
    private ArrayList<Node> favSongNodes;
    private ArrayList<Node> recentTunedNodes;
    private NodeScroller friendScroller;
    private NodeScroller favSongScroller;
    private NodeScroller recentTunedScroller;
    private Button editProfileButton;
    private VBox containerVBox;
    private VBox profileVBox;
    private VBox nodeScrollersVBox;
    private ImageView profileImageView;
    private TuneUser currentUser;
    private Image profileImage;

    public ProfileFrame(TuneUser tuneUser) {
        super(new HBox(40), getScreenWidth() , getScreenHeight());
        currentUser = tuneUser;
        navigateBar = new NavigateBar();
        createImage();

        friendNodes = new ArrayList<Node>();
        favSongNodes = new ArrayList<Node>();
        recentTunedNodes = new ArrayList<Node>();

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

        createNodeScrollers();

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

    private void createNodeScrollers() {
        for (TuneUser friend : currentUser.getFriends()) {
            FriendNode friendBox = new FriendNode(friend);
            friendNodes.add(friendBox);
        }

        AdditionNode addFriendNode = new AdditionNode();
        friendNodes.add(addFriendNode);

        for (Song favSong : currentUser.getFavouriteSongs()) {
            SongNode favSongNode = new SongNode(favSong, 100, 100);
            favSongNodes.add(favSongNode);
        }

        AdditionNode addFavSongNode = new AdditionNode();
        favSongNodes.add(addFavSongNode);

        for (Song recentTunedSong : currentUser.getTunedSongs()) {
            SongNode recentTunedSongNode = new SongNode(recentTunedSong, 100, 100);
            recentTunedNodes.add(recentTunedSongNode);
        }

        AdditionNode addRecentTunedSongNode = new AdditionNode();
        recentTunedNodes.add(addRecentTunedSongNode);

        friendScroller = new NodeScroller("Friends", friendNodes, 750);
        favSongScroller = new NodeScroller("Favorite Songs", favSongNodes, 750);
        recentTunedScroller = new NodeScroller("Recent Tuned Songs", recentTunedNodes, 750);

        nodeScrollersVBox.getChildren().addAll(friendScroller, favSongScroller, recentTunedScroller);
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

    public Button getEditProfileButton() {
        return editProfileButton;
    }
}
