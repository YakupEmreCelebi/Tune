package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.NavigateBar;
import com.example.demo.View.SpecialNodes.NodeScroller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ProfileFrame extends Scene {

    private NavigateBar navigateBar;
    private ArrayList<Node> scrollerNodes;
    private NodeScroller scroller;

    public ProfileFrame() {
        super(new StackPane(), getScreenWidth() , getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        scrollerNodes = new ArrayList<>();


        Button a = new Button("A");
        Button b = new Button("B");
        Button c = new Button("C");
        Button d = new Button("D");

        a.setPrefWidth(200);
        b.setPrefWidth(200);
        c.setPrefWidth(200);
        d.setPrefWidth(200);
        a.setPrefHeight(130);
        b.setPrefHeight(130);
        c.setPrefHeight(130);
        d.setPrefHeight(130);

        scrollerNodes.add(a);
        scrollerNodes.add(b);
        scrollerNodes.add(c);
        scrollerNodes.add(d);

        scroller = new NodeScroller(scrollerNodes);



        StackPane layout = (StackPane) getRoot();

        navigateBar = new NavigateBar();


        layout.getChildren().addAll(navigateBar, scroller);

        StackPane.setAlignment(scroller, Pos.TOP_RIGHT);
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
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

    public NodeScroller getScroller() {
        return scroller;
    }
}
