package com.example.demo.Frames;

import com.example.demo.SpecialNodes.NavigateBar;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsFrame extends Scene {

    NavigateBar navigateBar;
    VBox container;
    VBox accountVBox;
    VBox aboutVBox;
    SettingsButton emailButton;
    SettingsButton passwordButton;
    SettingsButton aboutUsButton;
    SettingsButton aboutTuneButton;
    ImageView pencilImageView;
    ImageView pencilImageView2;
    ImageView infoImageView;
    ImageView infoImageView2;
    Label accountLabel;
    Label aboutLabel;
    Label settingsLabel;

    public SettingsFrame() {
        super(new HBox(30), 800, 600);

        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        createImages();

        // Buttons
        emailButton = new SettingsButton("Email", pencilImageView);
        passwordButton = new SettingsButton("Password", pencilImageView2);
        aboutUsButton = new SettingsButton("About Us", infoImageView);
        aboutTuneButton = new SettingsButton("About Tune", infoImageView2);

        // Labels
        aboutLabel = new Label("About");
        accountLabel = new Label("Account");
        settingsLabel = new Label("Settings");

        settingsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-font-family: Arial");
        aboutLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-font-family: Arial");
        accountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-font-family: Arial");

        // VBoxes
        container = new VBox();
        container.setSpacing(40);
        container.setPadding(new Insets(40,0,0,0));

        accountVBox = new VBox();
        accountVBox.setSpacing(10);
        accountVBox.setStyle("-fx-border-color: #b3b1b1; -fx-border-width: 1px; -fx-border-radius: 5");
        accountVBox.setPrefWidth(450);
        accountVBox.setPadding(new Insets(10,0, 15, 20));

        aboutVBox = new VBox();
        aboutVBox.setSpacing(10);
        aboutVBox.setStyle("-fx-border-color: #b3b1b1; -fx-border-width: 1px; -fx-border-radius: 5");
        aboutVBox.setPrefWidth(450);
        aboutVBox.setPadding(new Insets(10,0,15,20));


        accountVBox.getChildren().addAll(accountLabel, emailButton, passwordButton);
        aboutVBox.getChildren().addAll(aboutLabel, aboutUsButton, aboutTuneButton);

        container.getChildren().addAll(settingsLabel, accountVBox, aboutVBox);

        navigateBar = new NavigateBar();
        HBox layout = (HBox) getRoot();
        layout.getChildren().addAll(navigateBar, container);
    }

    private void createImages() {
        Image pencilImage = new Image(getClass().getResourceAsStream("/com/example/demo/pencil_ico.png"));
        pencilImageView = new ImageView(pencilImage);
        pencilImageView.setPreserveRatio(true);
        pencilImageView.setFitWidth(18);

        Image infoImage = new Image(getClass().getResourceAsStream("/com/example/demo/info_ico.png"));
        infoImageView = new ImageView(infoImage);
        infoImageView.setPreserveRatio(true);
        infoImageView.setFitWidth(20);

        Image pencilImage2 = new Image(getClass().getResourceAsStream("/com/example/demo/pencil_ico.png"));
        pencilImageView2 = new ImageView(pencilImage2);
        pencilImageView2.setPreserveRatio(true);
        pencilImageView2.setFitWidth(18);

        Image infoImage2 = new Image(getClass().getResourceAsStream("/com/example/demo/info_ico.png"));
        infoImageView2 = new ImageView(infoImage2);
        infoImageView2.setPreserveRatio(true);
        infoImageView2.setFitWidth(20);


    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    // Inner class for Buttons
    public class SettingsButton extends Button {


        public SettingsButton(String name, ImageView imageView) {
            super(name, imageView);
            setStyle("-fx-font-weight: bold; -fx-alignment: CENTER_LEFT;");
            setGraphicTextGap(10);
            addHoverEffect(this);
            setPrefWidth(150);
        }

        private void addHoverEffect(Button button) {
            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    button.setStyle("-fx-background-color: #dadada; -fx-font-weight: bold; -fx-alignment: CENTER_LEFT;");
                }
            });

            button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    button. setStyle("-fx-font-weight: bold; -fx-alignment: CENTER_LEFT;");
                }
            });
        }
    }
}
