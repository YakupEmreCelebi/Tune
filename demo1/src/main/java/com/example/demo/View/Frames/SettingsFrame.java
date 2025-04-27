package com.example.demo.View.Frames;

import com.example.demo.View.SpecialNodes.NavigateBar;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SettingsFrame extends Scene {

    NavigateBar navigateBar;
    VBox container;
    VBox accountVBox;
    VBox aboutVBox;
    HBox removeButtonHBox;
    SettingsButton emailButton;
    SettingsButton passwordButton;
    SettingsButton aboutUsButton;
    SettingsButton aboutTuneButton;
    Button removeButton;
    ImageView pencilImageView;
    ImageView pencilImageView2;
    ImageView infoImageView;
    ImageView infoImageView2;
    ImageView deleteImageView;
    Label accountLabel;
    Label aboutLabel;
    Label settingsLabel;

    public SettingsFrame() {
        super(new HBox(60), getScreenWidth(), getScreenHeight());

        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        createImages();

        // Buttons
        emailButton = new SettingsButton("Email", pencilImageView);
        passwordButton = new SettingsButton("Password", pencilImageView2);
        aboutUsButton = new SettingsButton("About Us", infoImageView);
        aboutTuneButton = new SettingsButton("About Tune", infoImageView2);
        removeButton = new Button("Remove Account", deleteImageView);
        removeButton.setStyle("-fx-border-radius: 10;-fx-border-width: 2.5; -fx-background-radius: 10 ;-fx-font-size: 18; -fx-background-color: #e8e8e8; -fx-border-color: black");
        addHoverEffect(removeButton);

        // Labels
        aboutLabel = new Label("About");
        accountLabel = new Label("Account");
        settingsLabel = new Label("Settings");

        settingsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 35px; -fx-font-family: Arial");
        aboutLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25px; -fx-font-family: Arial");
        accountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25px; -fx-font-family: Arial");

        // VBoxes
        container = new VBox();
        container.setSpacing(60);
        container.setPadding(new Insets(80,0,0,0));

        accountVBox = new VBox();
        accountVBox.setSpacing(20);
        accountVBox.setStyle("-fx-border-color: #b3b1b1; -fx-border-width: 1px; -fx-border-radius: 5");
        accountVBox.setPrefWidth(getScreenWidth() - getScreenWidth() / 5.5 - 120);
        accountVBox.setPadding(new Insets(20,0, 25, 35));

        aboutVBox = new VBox();
        aboutVBox.setSpacing(20);
        aboutVBox.setStyle("-fx-border-color: #b3b1b1; -fx-border-width: 1px; -fx-border-radius: 5");
        aboutVBox.setPrefWidth(getScreenWidth() - getScreenWidth() / 5.5 - 120);
        aboutVBox.setPadding(new Insets(20,0, 25, 35));

        //HBox for RemoveButton
        removeButtonHBox = new HBox();
        removeButtonHBox.setAlignment(Pos.CENTER);

        //Navigate Bar
        navigateBar = new NavigateBar();

        // Add elements to VBoxes & HBox
        removeButtonHBox.getChildren().addAll(removeButton);
        accountVBox.getChildren().addAll(accountLabel, emailButton, passwordButton);
        aboutVBox.getChildren().addAll(aboutLabel, aboutUsButton, aboutTuneButton);
        container.getChildren().addAll(settingsLabel, accountVBox, aboutVBox, removeButtonHBox);

        // Layout
        HBox layout = (HBox) getRoot();
        layout.getChildren().addAll(navigateBar, container);
    }

    private void createImages() {
        Image pencilImage = new Image(getClass().getResourceAsStream("/com/example/demo/pencil_ico.png"));
        pencilImageView = new ImageView(pencilImage);
        pencilImageView.setPreserveRatio(true);
        pencilImageView.setFitWidth(32);

        Image infoImage = new Image(getClass().getResourceAsStream("/com/example/demo/info_ico.png"));
        infoImageView = new ImageView(infoImage);
        infoImageView.setPreserveRatio(true);
        infoImageView.setFitWidth(35);

        Image pencilImage2 = new Image(getClass().getResourceAsStream("/com/example/demo/pencil_ico.png"));
        pencilImageView2 = new ImageView(pencilImage2);
        pencilImageView2.setPreserveRatio(true);
        pencilImageView2.setFitWidth(32);

        Image infoImage2 = new Image(getClass().getResourceAsStream("/com/example/demo/info_ico.png"));
        infoImageView2 = new ImageView(infoImage2);
        infoImageView2.setPreserveRatio(true);
        infoImageView2.setFitWidth(35);

        Image deleteImage = new Image(getClass().getResourceAsStream("/com/example/demo/delete_ico.png"));
        deleteImageView = new ImageView(deleteImage);
        deleteImageView.setPreserveRatio(true);
        deleteImageView.setFitWidth(35);


    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    // Inner class for Buttons
    public class SettingsButton extends Button {


        public SettingsButton(String name, ImageView imageView) {
            super(name, imageView);
            setStyle("-fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-font-size: 17");
            setGraphicTextGap(10);
            addHoverEffect(this);
            setPrefWidth(230);
        }

        private void addHoverEffect(Button button) {
            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    button.setStyle("-fx-background-color: #dadada; -fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-font-size: 17");
                }
            });

            button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    button. setStyle("-fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-font-size: 17");
                }
            });
        }
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

    private static double getScreenWidth() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    }


    public SettingsButton getEmailButton() {
        return emailButton;
    }

    public SettingsButton getPasswordButton() {
        return passwordButton;
    }
}
