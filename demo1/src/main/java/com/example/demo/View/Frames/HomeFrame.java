package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.NavigateBar;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeFrame extends Scene {

    private Label label;
    private NavigateBar navigateBar;



    public HomeFrame() {
        super(new VBox(), getScreenWidth(), getScreenHeight());

        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        Label label = new Label("HOME PAGE");

        VBox layout = (VBox) getRoot();
        layout.setSpacing(40);

        navigateBar = new NavigateBar();

        layout.getChildren().addAll(navigateBar, label);
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
}

