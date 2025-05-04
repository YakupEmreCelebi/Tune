package com.example.demo.View.SpecialNodes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.util.ArrayList;

public class NodeScroller extends BorderPane {

    private final double SCROLL_PIXELS = .1;

    Label header;
    ScrollPane scrollPane;
    BorderPane leftPane;
    BorderPane rightPane;

    public NodeScroller(String headerString, ArrayList<Node> nodes, int prefWidth) {

        header = new Label(headerString);
        header.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));

        scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        setSides();

        this.setPrefWidth(prefWidth);
        this.setPrefHeight(200);

        setScrollPane(nodes);


        this.setTop(header);
        this.setLeft(leftPane);
        this.setCenter(scrollPane);
        this.setRight(rightPane);

    }

    private void setSides() {

        leftPane = new BorderPane();
        rightPane = new BorderPane();

        Image leftArrowImg = new Image(getClass().getResourceAsStream("/com/example/demo/left-arrow2.png"));
        ImageView leftArrowView = new ImageView(leftArrowImg);
        leftArrowView.setPreserveRatio(true);
        leftArrowView.setFitWidth(45);

        Image rightArrowImg = new Image(getClass().getResourceAsStream("/com/example/demo/right-arrow2.png"));
        ImageView rightArroewView = new ImageView(rightArrowImg);
        rightArroewView.setPreserveRatio(true);
        rightArroewView.setFitWidth(45);

        Button leftScrollButton = new Button("", leftArrowView);
        Button rightScrollButton = new Button("", rightArroewView);

        leftScrollButton.setStyle("-fx-background-color: transparent;");
        rightScrollButton.setStyle("-fx-background-color: transparent;");


        leftScrollButton.setOnAction(new ScrolLeft());
        rightScrollButton.setOnAction(new ScrollRight());

        leftPane.setCenter(leftScrollButton);
        rightPane.setCenter(rightScrollButton);
    }

    public void setScrollPane(ArrayList<Node> nodes) {
        HBox layout = new HBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(nodes);

        scrollPane.setContent(layout);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }


    private class ScrolLeft implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            double current = scrollPane.getHvalue();
            scrollPane.setHvalue(Math.max(0, current - SCROLL_PIXELS));

        }

    }

    private class ScrollRight implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            double current = scrollPane.getHvalue();
            scrollPane.setHvalue(Math.min(1, current + SCROLL_PIXELS));
        }

    }


}
