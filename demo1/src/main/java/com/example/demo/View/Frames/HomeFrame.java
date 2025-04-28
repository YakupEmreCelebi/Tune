package com.example.demo.View.Frames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.NavigateBar;
import com.example.demo.View.SpecialNodes.NodeScroller;
import com.example.demo.View.SpecialNodes.SongVBox;
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

public class HomeFrame extends Scene {

    private NavigateBar navigateBar;
    private VBox container;
    private TextField searchBar;
    private NodeScroller friendsScroller;

    public HomeFrame(Song song) {
        super(new HBox(40), getScreenWidth(), getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        // Create Navigate Bar
        navigateBar = new NavigateBar();

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

        ArrayList<Button> friendsButtons = new ArrayList<Button>();

        // Add elements to container
        container.getChildren().addAll(searchBar, new SongVBox(song.getImage(), song.getArtist(), song.getGenre()));

        // Layout
        HBox layout = (HBox) getRoot();
        layout.setOnMousePressed(new ChangeFocus());
        layout.getChildren().addAll(navigateBar, container);
    }

    private static double getScreenWidth() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    private class FriendsButton extends Button {

        public FriendsButton(String text) {
            super(text);
            this.setShape(new Circle(1000));
            this.setMinSize(80, 80);
            this.setMaxSize(80, 80);
            this.setPrefSize(80, 80);
            this.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 2;" +
                    " -fx-font-size: 19; -fx-font-family: Arial; -fx-font-weight: bold");
        }
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

}

