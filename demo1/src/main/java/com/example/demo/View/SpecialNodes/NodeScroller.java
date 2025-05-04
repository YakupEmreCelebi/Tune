package com.example.demo.View.SpecialNodes;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.util.Duration;


import java.util.ArrayList;

public class NodeScroller extends BorderPane {

    private final double SCROLL_PIXELS = .7;

    Label header;
    ScrollPane scrollPane;
    BorderPane leftPane;
    BorderPane rightPane;

    public NodeScroller(String headerString, ArrayList<Node> nodes, int prefWidth) {

        header = new Label(headerString);
        header.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
        header.setPadding(new Insets(20));

        scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        setSides();

        this.setPrefWidth(prefWidth);
        this.setMaxHeight(300);

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

        addHoverEffect(leftScrollButton);
        addHoverEffect(rightScrollButton);


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
            double target = Math.max(0, current - SCROLL_PIXELS);
            scrollPane.setHvalue(Math.max(0, current - SCROLL_PIXELS));
            animateScroller(current, target, 0.2);

        }

    }

    private class ScrollRight implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            double current = scrollPane.getHvalue();
            double target = Math.min(1, current + SCROLL_PIXELS);
            scrollPane.setHvalue(Math.min(1, current + SCROLL_PIXELS));
            animateScroller(current, target, 0.2);
        }

    }

    private void addHoverEffect(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), button);
        scaleUp.setToX(1.07);
        scaleUp.setToY(1.1);

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

    private void animateScroller(double start, double end, double duration) {
        Transition scrollAnim = new Transition() {
            {
                setCycleDuration(Duration.seconds(duration));
            }

            @Override
            protected void interpolate(double frac) {
                double value = start + (end - start) * frac;
                scrollPane.setHvalue(value);
            }
        };
        scrollAnim.play();
    }





}
