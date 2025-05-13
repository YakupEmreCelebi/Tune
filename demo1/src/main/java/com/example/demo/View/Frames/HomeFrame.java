package com.example.demo.View.Frames;

import com.example.demo.Model.Database;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class HomeFrame extends Scene {

    private NavigateBar navigateBar;
    private VBox container;
    private TextField searchBar;
    private Button searchButton;
    private HBox searchBarHBox;
    private ImageView searchImageView;
    private NodeScroller friendTunesNodeScroller;
    private NodeScroller songNodeScroller;
    private ArrayList<Node> friendTuneNodes;
    private ArrayList<Node> songNodes;
    private TuneUser currentUser;
    private ArrayList<Song> randomSongs;
    private SongPlayerNode songPlayer;
    private Song currentSong;
    private StackPane stackPane;
    private SearchSongsVBox searchSongsVBox;



    public HomeFrame(TuneUser user, Song theSong, ArrayList<Song> randomSongs, Database database) {
        super(new HBox(40), getScreenWidth(), getScreenHeight());
        //this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        currentSong = theSong;

        stackPane = new StackPane();


        currentUser = user;
        friendTuneNodes = new ArrayList<Node>();
        songNodes = new ArrayList<Node>();

        this.randomSongs = randomSongs;

        // Create Navigate Bar
        navigateBar = new NavigateBar(currentUser);

        // Create friends node Scroller
        createNodeScrollers();
        constructSongPlayer();


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
        searchBar.setMinWidth(350);
        searchBar.setPrefHeight(42);

        // Search Image
        Image searchImage = new Image(getClass().getResourceAsStream("/com/example/demo/search_ico.png"));
        searchImageView = new ImageView(searchImage);
        searchImageView.setFitWidth(25);
        searchImageView.setPreserveRatio(true);

        // Search Button
        searchButton = new Button("", searchImageView);
        searchButton.setStyle("-fx-background-color: transparent;");
        addHoverEffect(searchButton);

        searchBarHBox = new HBox(5);
        searchBarHBox.getChildren().addAll(searchBar, searchButton);
        searchBarHBox.setMaxWidth(400);
        searchBarHBox.setMinWidth(400);
        searchBarHBox.setPrefWidth(400);
        searchBarHBox.setPrefHeight(42);
        searchBarHBox.setAlignment(Pos.CENTER);

        // Add elements to container
        container.getChildren().addAll(searchBarHBox, songPlayer, songNodeScroller, friendTunesNodeScroller);

        searchSongsVBox = new SearchSongsVBox("H", database);
        searchSongsVBox.setVisible(false);
        stackPane.getChildren().addAll(container, searchSongsVBox);

        // Layout
        HBox layout = (HBox) getRoot();
        layout.setOnMousePressed(new ChangeFocus());
        layout.getChildren().addAll(navigateBar, stackPane);
    }

    private void createNodeScrollers() {

        for (Song randomSong : randomSongs) {
            SongNode songNode = new SongNode(randomSong, 120, 120, true, false);
            songNodes.add(songNode);
        }

        songNodeScroller = new NodeScroller("Songs", songNodes, 600);

        for (TuneUser friend : currentUser.getFriends()) {
            FriendNode friendBox = new FriendNode(friend, true, true);
            friendTuneNodes.add(friendBox);
        }

        friendTunesNodeScroller = new NodeScroller("Friends' Tunes", friendTuneNodes, 600);
    }


    public void resetNavigateBar(TuneUser user) {
        currentUser = user;
        navigateBar.resetUsersTune(currentUser);
    }

    public void constructSongPlayer() {

        // Create Song Player slider
        songPlayer = new SongPlayerNode(currentSong);
        songPlayer.setAlignment(Pos.CENTER);

    }

    private void addHoverEffect(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), button);
        scaleUp.setToX(1.05);
        scaleUp.setToY(1.05);

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


    private class ChangeFocus implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent mouseEvent) {
            if(searchBar.isFocused())
            {
                searchSongsVBox.setVisible(false);
                searchBar.clear();
            }
            if (searchSongsVBox.isVisible()) {
                searchSongsVBox.setVisible(false);
                searchBar.clear();
            }
            getRoot().requestFocus();
        }
    }

    public void resetUserFriends(TuneUser user) {
        currentUser = user;
        for (int i = 0; i < currentUser.getFriends().size(); i++) {
            TuneUser friend = currentUser.getFriends().get(i);
            if (i > friendTuneNodes.size() - 1) {
                FriendNode friendBox = new FriendNode(friend, true, true);
                friendTuneNodes.add(i, friendBox);
            }
        }

        friendTunesNodeScroller.setScrollPane(friendTuneNodes);
        container.getChildren().set(3, friendTunesNodeScroller);
    }

    public void removeUserFriends(TuneUser user) {
        currentUser = user;
        boolean removed = false;
        for (int i = 0; i < friendTuneNodes.size() && !removed; i++) {
            if (i != friendTuneNodes.size() - 1) {
                FriendNode friendNode = (FriendNode) friendTuneNodes.get(i);
                if (currentUser.getFriends().size() > i) {
                    if (!friendNode.getFriend().getUsername().equals(currentUser.getFriends().get(i).getUsername())) {
                        friendTuneNodes.remove(i);
                        removed = true;
                    }
                } else {
                    friendTuneNodes.remove(i);
                    removed = true;
                }
            }
        }
        friendTunesNodeScroller.setScrollPane(friendTuneNodes);
        container.getChildren().set(3, friendTunesNodeScroller);
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

    public Button getSearchButton() {
        return searchButton;
    }

    public SearchSongsVBox getSearchSongsVBox() {
        return searchSongsVBox;
    }

    public String getSearchBarText() {
        return searchBar.getText();
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public Slider getSlider() {
        return songPlayer.getSlider();
    }
}


