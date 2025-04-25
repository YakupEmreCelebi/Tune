package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.NavigateBar;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeFrame extends Scene {

    private NavigateBar navigateBar;
    private VBox container;
    private TextField searchBar;

    public HomeFrame() {
        super(new HBox(40), getScreenWidth(), getScreenHeight());

        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());
        container = new VBox();
        container.setPadding(new Insets(100,0,0,0));
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle("-fx-border-color: black");
        container.setPrefWidth(getScreenWidth() - getScreenWidth() / 5.5 - 80);

        searchBar = new TextField();
        searchBar.setPromptText("Search Music");
        searchBar.setStyle("-fx-border-radius: 30px; -fx-background-radius: 30px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-border-color: #ccc;");
        searchBar.setMaxWidth(350);
        searchBar.setPrefHeight(40);

        container.getChildren().add(searchBar);

        HBox layout = (HBox) getRoot();
        layout.setOnMousePressed(new ChangeFocus());

        navigateBar = new NavigateBar();

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

