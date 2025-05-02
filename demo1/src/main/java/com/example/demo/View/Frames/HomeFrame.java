package com.example.demo.View.Frames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class HomeFrame extends Scene {

    private NavigateBar navigateBar;
    private VBox container;
    private TextField searchBar;
    private ButtonScroller buttonScroller;
    private ArrayList<Button> friendsButtons;


    public HomeFrame(Song song) {
        super(new HBox(40), getScreenWidth(), getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        // Create Navigate Bar
        navigateBar = new NavigateBar();

        // Create friends Button Scroller
        friendsButtons = new ArrayList<>();
        FriendsButton b = new FriendsButton(new TuneUser("fr", "123", "@", 1, null, null));
        friendsButtons.add(b);
        buttonScroller = new ButtonScroller(friendsButtons);


        // container VBox
        container = new VBox(20);
        container.setPadding(new Insets(100,0,0,0));
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle("-fx-border-color: black");
        container.setPrefWidth(getScreenWidth() - getScreenWidth() / 5.5 - 80);

        // SearchBar
        searchBar = new TextField();
        searchBar.setPromptText("Search Music");
        searchBar.setStyle("-fx-border-radius: 30px; -fx-background-radius: 30px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-border-color: #ccc;");
        searchBar.setMaxWidth(350);
        searchBar.setPrefHeight(40);

        // Add elements to container
        container.getChildren().addAll(searchBar, new SongVBox(song), buttonScroller);

        // Layout
        HBox layout = (HBox) getRoot();
        layout.setOnMousePressed(new ChangeFocus());
        layout.getChildren().addAll(navigateBar, container);
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

    public ArrayList<Button> getFriendsButtons() {
        return friendsButtons;
    }
}

