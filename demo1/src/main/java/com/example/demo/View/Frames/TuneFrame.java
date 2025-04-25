package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.NavigateBar;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TuneFrame extends Scene {

    NavigateBar navigateBar;
    ImageView backgroundImageView;

    public TuneFrame() {
        super(new StackPane() , getScreenWidth(),getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/example/demo/Tune_Background.png"));
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(getScreenWidth() - getScreenWidth() / 5.5);
        backgroundImageView.setFitHeight(getScreenHeight());
        backgroundImageView.setPreserveRatio(false);

        tuneButton a = new tuneButton("Instant Tune", 680, 150);
        tuneButton b = new tuneButton("Tune with a\n    Friend", 1130, 180);
        tuneButton c = new tuneButton("Detailed Tune",820,430);



        Pane pane = new Pane();
        pane.getChildren().addAll(a, b, c);

        navigateBar = new NavigateBar();

        StackPane layout = (StackPane) getRoot();


        layout.getChildren().addAll(backgroundImageView, pane, navigateBar);
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
        StackPane.setAlignment(backgroundImageView, Pos.TOP_RIGHT);
        StackPane.setAlignment(pane, Pos.CENTER);
        
    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    public class tuneButton extends Button {


        public tuneButton(String text, int x, int y) {
            super(text);
            this.setShape(new Circle(1000));
            this.setMinSize(150, 150);
            this.setMaxSize(150, 150);
            this.setPrefSize(150, 150);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 2;" +
                    " -fx-font-size: 19; -fx-font-family: Arial; -fx-font-weight: bold");

            ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), this);
            scaleUp.setToX(1.03);
            scaleUp.setToY(1.03);

            ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.1), this);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);

            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    scaleUp.play();
                }
            });

            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    scaleDown.play();
                }
            });
        }

    }

    private static double getScreenWidth() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    }
}
