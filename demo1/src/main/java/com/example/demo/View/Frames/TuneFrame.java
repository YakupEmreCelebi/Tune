package com.example.demo.View.Frames;

import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.NavigateBar;
import com.example.demo.View.SpecialNodes.SongNode;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static javafx.stage.Screen.getPrimary;

public class TuneFrame extends Scene {

    NavigateBar navigateBar;
    ImageView backgroundImageView;
    ImageView tuneImageView;
    ImageView detailedTuneImageView;
    ImageView tuneWithFriendImageView;
    TuneButton instantTuneButton;
    TuneButton detailedTuneButton;
    TuneButton tuneWithFriendButton;
    VBox lastTunedSongVBox;
    VBox recentTunedSongVBox;
    Label lastTunedSongLabel;
    TuneUser tuneUser;
    Label noOfTunedSongsLabel;
    String noOfTunedSongs;
    Label songYouDiscoveredByTuningLabel;
    Button seeRecentTunedSongsButton;

    public TuneFrame(TuneUser tuneUser) {
        super(new StackPane() , getScreenWidth(),getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        this.tuneUser = tuneUser;

        createImages();
        navigateBar = new NavigateBar();

        //Buttons
        instantTuneButton = new TuneButton("Instant Tune", 680, 150, tuneImageView);
        tuneWithFriendButton = new TuneButton("Tune with a\n    Friend", 1130, 180, detailedTuneImageView);
        detailedTuneButton = new TuneButton("Detailed Tune",820,430, tuneWithFriendImageView);
        seeRecentTunedSongsButton = new Button("See recent tuned songs");

        //Labels
        lastTunedSongLabel = new Label("Last Tuned Song");
        lastTunedSongLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 21; -fx-font-family: Arial");

        songYouDiscoveredByTuningLabel = new Label("Songs You Discovered By Tuning");
        songYouDiscoveredByTuningLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18; -fx-font-family: Arial");



        //VBoxes
        lastTunedSongVBox = new VBox(3);
        lastTunedSongVBox.setAlignment(Pos.CENTER);

        // !!! Song parameter will change !!!
        lastTunedSongVBox.getChildren().addAll(lastTunedSongLabel, new SongNode(tuneUser.getTuneSong(), 150, 150, true));
        lastTunedSongVBox.setLayoutX(300);
        lastTunedSongVBox.setLayoutY(50);

        recentTunedSongVBox = new VBox(3);
        constructRecentTunedSongs();
        recentTunedSongVBox.setLayoutY(650);
        recentTunedSongVBox.setLayoutX(300);
        recentTunedSongVBox.setStyle("-fx-border-color: black; -fx-border-width: 1px");

        // Pane
        Pane pane = new Pane();
        pane.getChildren().addAll(instantTuneButton, detailedTuneButton, tuneWithFriendButton, lastTunedSongVBox, recentTunedSongVBox);

        // Layout
        StackPane layout = (StackPane) getRoot();
        layout.getChildren().addAll(backgroundImageView, pane, navigateBar);

        // Alignments
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
        StackPane.setAlignment(backgroundImageView, Pos.TOP_RIGHT);
        StackPane.setAlignment(pane, Pos.CENTER);
    }

    public class TuneButton extends Button {

        public TuneButton(String text, int x, int y, ImageView imageView) {
            super(text, imageView);
            this.setShape(new Circle(1000));
            this.setMinSize(160, 160);
            this.setMaxSize(160, 160);
            this.setPrefSize(160, 160);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 2;" +
                    " -fx-font-size: 19; -fx-font-family: Arial; -fx-font-weight: bold");

            this.setContentDisplay(ContentDisplay.TOP);
            this.setGraphicTextGap(6);

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

    public void constructRecentTunedSongs() {
        recentTunedSongVBox.getChildren().clear();
        noOfTunedSongs = String.valueOf(tuneUser.getTunedSongs().size());
        noOfTunedSongsLabel = new Label(noOfTunedSongs);

        recentTunedSongVBox.getChildren().addAll(noOfTunedSongsLabel, songYouDiscoveredByTuningLabel, seeRecentTunedSongsButton);
    }

    private void createImages(){

        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/example/demo/Tune_Background.png"));
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(getScreenWidth() - getScreenWidth() / 5.5);
        backgroundImageView.setFitHeight(getScreenHeight());
        backgroundImageView.setPreserveRatio(false);

        Image tuneImage = new Image(getClass().getResourceAsStream("/com/example/demo/instanttune_ico.png"));
        tuneImageView = new ImageView(tuneImage);
        tuneImageView.setPreserveRatio(true);
        tuneImageView.setFitWidth(60);

        Image detailedTuneImage = new Image(getClass().getResourceAsStream("/com/example/demo/detailedtune_ico.png"));
        detailedTuneImageView = new ImageView(detailedTuneImage);
        detailedTuneImageView.setPreserveRatio(true);
        detailedTuneImageView.setFitWidth(60);

        Image tuneWithFriendImage = new Image(getClass().getResourceAsStream("/com/example/demo/tunewithafriend_ico.png"));
        tuneWithFriendImageView = new ImageView(tuneWithFriendImage);
        tuneWithFriendImageView.setPreserveRatio(true);
        tuneWithFriendImageView.setFitWidth(60);
    }

    // Get Screen dimensions
    private static double getScreenWidth() {
        return getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return getPrimary().getVisualBounds().getHeight();
    }

    // Getters
    public NavigateBar getNavigateBar() {
        return navigateBar;
    }
}
