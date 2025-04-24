package com.example.demo.Frames;

import com.example.demo.SpecialNodes.NavigateBar;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeFrame extends Scene {

    private Label label;
    private NavigateBar navigateBar;



    public HomeFrame() {
        super(new VBox(), 800, 600);

        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        Label label = new Label("HOME PAGE");

        VBox layout = (VBox) getRoot();
        layout.setSpacing(20);

        navigateBar = new NavigateBar();

        layout.getChildren().addAll(navigateBar, label);
    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }
}

