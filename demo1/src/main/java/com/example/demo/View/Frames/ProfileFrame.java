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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
    private HBox layout;

    public ProfileFrame(TuneUser tuneUser) {
        super(new HBox(40), getScreenWidth() , getScreenHeight());
        currentUser = tuneUser;
        navigateBar = new NavigateBar(currentUser);

        friendNodes = new ArrayList<Node>();
        favSongNodes = new ArrayList<Node>();
        recentTunedNodes = new ArrayList<Node>();

        //Button
        editProfileButton = new Button("Edit Profile");
        editProfileButton.setStyle("-fx-font-size: 18; -fx-background-color: black; -fx-text-fill: white");
        editProfileButton.setTranslateX(20);
        addHoverEffect(editProfileButton);

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
        layout = (HBox) getRoot();
        layout.getChildren().addAll(navigateBar, profileVBox, nodeScrollersVBox);

        // Alignments
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
    }

    public void resetNavigateBar(TuneUser user) {
        currentUser = user;
        navigateBar.resetUsersTune(currentUser);
    }

    private void createImage(){
        profileImageView = new ImageView(currentUser.getProfileImage());
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitWidth(160);
        profileImageView.setClip(new Circle(80, 80, 80));
    }

    public void constructImageContainer() {
        profileVBox.getChildren().clear();
        createImage();
        profileVBox.getChildren().addAll(profileImageView, editProfileButton);
    }

    public void resetUserFriends(TuneUser user) {
        for (int i = 0; i < currentUser.getFriends().size(); i++) {
            TuneUser friend = currentUser.getFriends().get(i);
            if (i > friendNodes.size() - 2) {
                FriendNode friendBox = new FriendNode(friend, true);
                friendNodes.add(i, friendBox);
            }
        }
        friendScroller.setScrollPane(friendNodes);
        nodeScrollersVBox.getChildren().set(0, friendScroller);
    }

    public void resetUserFavSongs(TuneUser user) {
        for (int i = 0; i < currentUser.getFavouriteSongs().size(); i++) {
            Song song = currentUser.getFavouriteSongs().get(i);
            if (i > favSongNodes.size() - 2) {
                SongNode songNode = new SongNode(song, 120, 120, true);
                favSongNodes.add(i, songNode);
            }
        }
        favSongScroller.setScrollPane(favSongNodes);
        nodeScrollersVBox.getChildren().set(1, favSongScroller);
    }

    public void resetUserTunedSongs(TuneUser user) {
        for (int i = 0; i < currentUser.getTunedSongs().size(); i++) {
            Song song = currentUser.getTunedSongs().get(i);
            if (i > recentTunedNodes.size() - 2) {
                SongNode songNode = new SongNode(song, 120, 120, true);
                recentTunedNodes.add(i, songNode);
            }
        }
        recentTunedScroller.setScrollPane(recentTunedNodes);
        nodeScrollersVBox.getChildren().set(2, recentTunedScroller);
    }

    public void createNodeScrollers() {
        for (TuneUser friend : currentUser.getFriends()) {
            FriendNode friendBox = new FriendNode(friend, true);
            friendNodes.add(friendBox);
        }

        AdditionNode addFriendNode = new AdditionNode();
        friendNodes.add(addFriendNode);

        for (Song favSong : currentUser.getFavouriteSongs()) {
            SongNode favSongNode = new SongNode(favSong, 120, 120, true);
            favSongNodes.add(favSongNode);
        }

        AdditionNode addFavSongNode = new AdditionNode();
        favSongNodes.add(addFavSongNode);

        for (Song recentTunedSong : currentUser.getTunedSongs()) {
            SongNode recentTunedSongNode = new SongNode(recentTunedSong, 120, 120, true);
            recentTunedNodes.add(recentTunedSongNode);
        }

        AdditionNode addRecentTunedSongNode = new AdditionNode();
        recentTunedNodes.add(addRecentTunedSongNode);

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

    public Button getEditProfileButton() {
        return editProfileButton;
    }

    public NodeScroller getFavSongScroller() {
        return favSongScroller;
    }

    public NodeScroller getRecentTunedScroller() {
        return recentTunedScroller;
    }
}
