package com.example.demo.View.Frames;

import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.NavigateBar;
import com.example.demo.View.SpecialNodes.SongNode;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

import javax.swing.*;

import static javafx.stage.Screen.getPrimary;

public class TuneFrame extends Scene {

    NavigateBar navigateBar;

    ImageView backgroundImageView;
    ImageView tuneImageView;
    ImageView detailedTuneImageView;
    ImageView tuneWithFriendImageView;
    ImageView tuneWithFriendImageView2;

    TuneButton instantTuneButton;
    TuneButton detailedTuneButton;
    TuneButton tuneWithFriendButton;

    VBox lastTunedSongVBox;
    VBox recentTunedSongsVBox;
    VBox tuneWithFriendsVBox;

    Label lastTunedSongLabel;
    Label noOfTuneWithFriendsLabel;
    Label tuneWithFriendTitleLabel;
    Label noOfTunedSongsLabel;
    Label songYouDiscoveredByTuningLabel;

    Button seeRecentTunedSongsButton;

    String noOfTunedSongs;
    String noOfTuneWithFriends;

    TuneUser currentUser;

    public TuneFrame(TuneUser currentUser) {
        super(new StackPane() , getScreenWidth(),getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        // User
        this.currentUser = currentUser;

        // Images
        createImages();

        // Navigate Bar
        navigateBar = new NavigateBar(currentUser);

        //Buttons
        instantTuneButton = new TuneButton("Instant Tune", (int) (getScreenWidth() / 2.258), (int) (getScreenHeight() / 5.44), tuneImageView);
        tuneWithFriendButton = new TuneButton("Tune with a\n    Friend", (int) (getScreenWidth() / 1.396) ,  (int) (getScreenHeight() / 4.533), tuneWithFriendImageView);
        detailedTuneButton = new TuneButton("Detailed Tune", (int) (getScreenWidth() / 1.873),(int) (getScreenHeight() / 1.897), detailedTuneImageView);
        seeRecentTunedSongsButton = new Button("See recent tuned songs");
        seeRecentTunedSongsButton.setStyle("-fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white; -fx-font-size: 13; -fx-background-color: black");
        addHoverEffect(seeRecentTunedSongsButton);

        //Labels
        lastTunedSongLabel = new Label("Last Tuned Song");
        lastTunedSongLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 21; -fx-font-family: Arial;");

        songYouDiscoveredByTuningLabel = new Label("Songs You Discovered By Tuning");
        songYouDiscoveredByTuningLabel.setStyle("-fx-font-size: 13; -fx-font-family: Arial; -fx-text-fill: #939393");

        tuneWithFriendTitleLabel = new Label("Songs you tuned with a friend\n     what a catcher");
        tuneWithFriendTitleLabel.setStyle("-fx-font-size: 13; -fx-font-family: Arial; -fx-text-fill: #939393");

        System.out.println(getScreenHeight());
        System.out.println(getScreenWidth());



        //Last Tuned Song VBox (Song Image)
        lastTunedSongVBox = new VBox(3);
        lastTunedSongVBox.setAlignment(Pos.CENTER);
        lastTunedSongVBox.setLayoutX(350);
        lastTunedSongVBox.setLayoutY(50);

        // !!! Song parameter will change !!!
        constructImageVBox();

        // Recent Tuned Songs VBox
        recentTunedSongsVBox = new VBox(7);
        recentTunedSongsVBox.setLayoutY(getScreenHeight() - 180);
        recentTunedSongsVBox.setLayoutX(getScreenWidth() / 5.5 + 20);
        recentTunedSongsVBox.setMaxHeight(150);
        recentTunedSongsVBox.setMinHeight(150);
        recentTunedSongsVBox.setPadding(new Insets(20,60,20,15));
        recentTunedSongsVBox.setStyle("-fx-border-color: #989898; -fx-border-width: 1px; -fx-background-radius: 5; -fx-border-radius: 5");
        constructRecentTunedSongs();

        // Tune With Friends VBox
        tuneWithFriendsVBox = new VBox(3);
        tuneWithFriendsVBox.setLayoutX(getScreenWidth() - 270);
        tuneWithFriendsVBox.setLayoutY(50);
        tuneWithFriendsVBox.setPadding(new Insets(20,60,20,15));
        tuneWithFriendsVBox.setStyle("-fx-border-color: #989898; -fx-border-width: 1px; -fx-background-radius: 5; -fx-border-radius: 5");
        constructTuneWithFriendsVBox();

        // Pane (add all VBoxes and buttons to pane)
        Pane pane = new Pane();
        pane.getChildren().addAll(instantTuneButton, detailedTuneButton, tuneWithFriendButton, lastTunedSongVBox, recentTunedSongsVBox, tuneWithFriendsVBox);

        // Layout
        StackPane layout = (StackPane) getRoot();
        layout.getChildren().addAll(backgroundImageView, pane, navigateBar);

        // Alignments
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
        StackPane.setAlignment(backgroundImageView, Pos.TOP_RIGHT);
        StackPane.setAlignment(pane, Pos.CENTER);
    }

    public class TuneButton extends Button {

        ImageView imageView;

        public TuneButton(String text, int x, int y, ImageView imageView) {
            super(text, imageView);
            this.imageView = imageView;
            this.setShape(new Circle(1000));
            this.setMinSize(160, 160);
            this.setMaxSize(160, 160);
            this.setPrefSize(160, 160);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 3;" +
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
                    if(imageView.getRotate() == 0 || imageView.getRotate() == 360)
                    {
                        try {
                            animateButtonImage(0,360,1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        animateButtonImage(imageView.getRotate(),360,(360 - imageView.getRotate()) / 360);
                    }

                }
            });

            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    scaleDown.play();
                }
            });
        }

        private void animateButtonImage(double start, double end, double duration) {
            Transition buttonImageAnim = new Transition() {
                {
                    setCycleDuration(Duration.seconds(duration));
                }

                @Override
                protected void interpolate(double frac) {
                    double value = start + (end - start) * frac * frac;
                    imageView.setRotate(value);
                }
            };
            buttonImageAnim.play();
        }
    }


    public void constructRecentTunedSongs() {
        recentTunedSongsVBox.getChildren().clear();
        noOfTunedSongs = String.valueOf(currentUser.getTunedSongs().size());
        noOfTunedSongsLabel = new Label(noOfTunedSongs);
        noOfTunedSongsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        recentTunedSongsVBox.getChildren().addAll(noOfTunedSongsLabel, songYouDiscoveredByTuningLabel, seeRecentTunedSongsButton);
    }

    public void constructImageVBox() {
        lastTunedSongVBox.getChildren().clear();
        if (currentUser.getTunedSongs().size() > 0) {
            SongNode songNode = new SongNode(currentUser.getTunedSongs().getLast(), 150, 150, true, false); // song parameter will change
            lastTunedSongVBox.getChildren().addAll(lastTunedSongLabel, songNode);
        } else {
            lastTunedSongLabel.setText("You haven't tuned any song so far!");
            Label testLabel = new Label("");
            lastTunedSongVBox.getChildren().addAll(lastTunedSongLabel, testLabel);
        }
    }

    public void constructTuneWithFriendsVBox() {
        tuneWithFriendsVBox.getChildren().clear();
        noOfTuneWithFriends = String.valueOf(currentUser.getNumbOfTunedSongsWithFriends());
        noOfTuneWithFriendsLabel = new Label(noOfTuneWithFriends);
        noOfTuneWithFriendsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        tuneWithFriendsVBox.getChildren().addAll(noOfTuneWithFriendsLabel, tuneWithFriendTitleLabel, tuneWithFriendImageView2);
    }

    public void resetNavigateBar(TuneUser user) {
        currentUser = user;
        navigateBar.resetUsersTune(currentUser);
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

        Image tuneWithFriendImage2 = new Image(getClass().getResourceAsStream("/com/example/demo/tunewithafriend_ico.png"));
        tuneWithFriendImageView2 = new ImageView(tuneWithFriendImage2);
        tuneWithFriendImageView2.setPreserveRatio(true);
        tuneWithFriendImageView2.setFitWidth(50);
    }

    public void changeRecentTuneVBox(int number) {
        noOfTunedSongs = String.valueOf(number);
        noOfTunedSongsLabel.setText(noOfTunedSongs);
        recentTunedSongsVBox.getChildren().set(0, noOfTunedSongsLabel);
    }

    public void changeTuneWithFriendsVBox(int number){
        noOfTuneWithFriends = String.valueOf(number);
        noOfTuneWithFriendsLabel.setText(noOfTuneWithFriends);
        noOfTuneWithFriendsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        tuneWithFriendsVBox.getChildren().set(0, noOfTuneWithFriendsLabel);
    }

    public void setImageVBox(Song song) {
        SongNode songNode = new SongNode(song, 150, 150, true, false);
        lastTunedSongLabel = new Label("Last Tuned Song");
        lastTunedSongLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 21; -fx-font-family: Arial;");

        lastTunedSongVBox.getChildren().setAll(lastTunedSongLabel, songNode);
    }

    private void addHoverEffect(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.1), button);
        scaleUp.setToX(1.03);
        scaleUp.setToY(1.03);

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

    public TuneButton getDetailedTuneButton() {
        return detailedTuneButton;
    }

    public Button getSeeRecentTunedSongsButton() {
        return seeRecentTunedSongsButton;
    }

    public TuneButton getInstantTuneButton() {
        return instantTuneButton;
    }

    public TuneButton getTuneWithFriendButton() {
        return tuneWithFriendButton;
    }

    public VBox getLastTunedSongVBox() {
        return lastTunedSongVBox;
    }
}
