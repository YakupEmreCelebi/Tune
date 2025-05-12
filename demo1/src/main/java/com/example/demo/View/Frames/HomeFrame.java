package com.example.demo.View.Frames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.*;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class HomeFrame extends Scene {

    private NavigateBar navigateBar;
    private VBox container;
    private TextField searchBar;
    private NodeScroller friendTunesNodeScroller;
    private NodeScroller songNodeScroller;
    private ArrayList<Node> friendTuneNodes;
    private ArrayList<Node> songNodes;
    private TuneUser currentUser;
    private ArrayList<Song> randomSongs;
    private SongPlayerNode songPlayer;




    public HomeFrame(TuneUser user, Song theSong, ArrayList<Song> randomSongs) {
        super(new HBox(40), getScreenWidth(), getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        currentUser = user;
        friendTuneNodes = new ArrayList<Node>();
        songNodes = new ArrayList<Node>();

        this.randomSongs = randomSongs;

        // Create Navigate Bar
        navigateBar = new NavigateBar(currentUser);

        // Create friends node Scroller
        createNodeScrollers();

        // Create Song Player slider
        songPlayer = new SongPlayerNode(theSong);
        songPlayer.setAlignment(Pos.CENTER);

        // container VBox
        container = new VBox(20);
        container.setPadding(new Insets(20,0,0,0));
        container.setAlignment(Pos.TOP_CENTER);
        container.setPrefWidth(getScreenWidth() - getScreenWidth() / 5.5 - 80);

        // SearchBar
        searchBar = new TextField();
        searchBar.setPromptText("Search Music");
        searchBar.setStyle("-fx-border-radius: 30px; -fx-background-radius: 30px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-border-color: #ccc;");
        searchBar.setMaxWidth(350);
        searchBar.setPrefHeight(42);

        // Add elements to container
        constructContainer();

        // Layout
        HBox layout = (HBox) getRoot();
        layout.setOnMousePressed(new ChangeFocus());
        layout.getChildren().addAll(navigateBar, container);
    }

    private void createNodeScrollers() {

        for (Song randomSong : randomSongs) {
            SongNode songNode = new SongNode(randomSong, 120, 120, true);
            songNodes.add(songNode);
        }

        songNodeScroller = new NodeScroller("Songs", songNodes, 600);

        for (TuneUser friend : currentUser.getFriends()) {
            FriendNode friendBox = new FriendNode(friend, true);
            friendTuneNodes.add(friendBox);
        }

        friendTunesNodeScroller = new NodeScroller("Friends' Tunes", friendTuneNodes, 600);
    }

    public void constructContainer() {
        container.getChildren().clear();
        container.getChildren().addAll(searchBar, songPlayer, songNodeScroller, friendTunesNodeScroller);
    }

    public void resetNavigateBar(TuneUser user) {
        currentUser = user;
        navigateBar.resetUsersTune(currentUser);
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


    private class ChangeFocus implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent mouseEvent) {
            if(searchBar.isFocused())
            {
                getRoot().requestFocus();
                searchBar.clear();
            }
        }
    }

    public ArrayList<Node> getFriendsButtons() {
        return friendTuneNodes;
    }

    public NodeScroller getSongNodeScroller() {
        return songNodeScroller;
    }

    public NodeScroller getFriendTunesNodeScroller() {
        return friendTunesNodeScroller;
    }

    public SongPlayerNode getSongPlayer() {
        return songPlayer;
    }
}

