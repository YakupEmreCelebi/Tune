package com.example.demo.Controller;

//a
//b
import com.example.demo.View.Frames.*;
import com.example.demo.View.PopUpFrames.PopUpUpdate;
import com.example.demo.View.Stage.PopUpStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controller {

    // Stage and Frames
    private Stage mainStage;
    private WelcomeFrame welcomeFrame;
    private LoginFrame loginFrame;
    private SignUpFrame signUpFrame;
    private HomeFrame homeFrame;
    private ProfileFrame profileFrame;
    private TuneFrame tuneFrame;
    private SettingsFrame settingsFrame;

    private PopUpStage popUpStage;

    public Controller(Stage stage) {
        this.mainStage = stage;
    }

    public void initScenes() {

        welcomeFrame = new WelcomeFrame();
        loginFrame = new LoginFrame();
        signUpFrame = new SignUpFrame();
        homeFrame = new HomeFrame();
        profileFrame = new ProfileFrame();
        tuneFrame = new TuneFrame();
        settingsFrame = new SettingsFrame();

        popUpStage = new PopUpStage();

        // Adding actions to the Buttons
        welcomeFrame.getLoginButton().setOnAction(new goToLoginFrame());
        welcomeFrame.getSignUpButton().setOnAction(new goToSignupFrame());

        loginFrame.getLoginButton().setOnAction(new goToHomeFrame());

        signUpFrame.getSignupButton().setOnAction(new goToLoginFrame());

        homeFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        homeFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
        homeFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        homeFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());

        profileFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        profileFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
        profileFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        profileFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());

        tuneFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        tuneFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
        tuneFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        tuneFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());

        settingsFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        settingsFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
        settingsFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        settingsFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());

        settingsFrame.getEmailButton().setOnAction(e -> showPopUpUpdate("Email", "Email", "Email"));
        settingsFrame.getPasswordButton().setOnAction(e -> showPopUpUpdate("Password", "Password", "Password"));


    }



    // Action event methods
    public void showWelcomeFrame() {
        mainStage.setScene(welcomeFrame);
        mainStage.setTitle("TUNE");
        mainStage.show();
    }

    public void showLoginFrame() {
        mainStage.setScene(loginFrame);
        mainStage.setTitle("LOGIN");
        mainStage.show();
    }

    public void showSignupFrame() {
        mainStage.setScene(signUpFrame);
        mainStage.setTitle("SIGNUP");
        mainStage.show();
    }
    public void showHomeFrame() {
        mainStage.setScene(homeFrame);
        mainStage.setTitle("HOME");
        mainStage.show();
    }
    public void showSettingsFrame() {
        mainStage.setScene(settingsFrame);
        mainStage.setTitle("SETTINGS");
        mainStage.show();
    }
    public void showTuneFrame() {
        mainStage.setScene(tuneFrame);
        mainStage.setTitle("TUNE");
        mainStage.show();
    }
    public void showProfileFrame() {
        mainStage.setScene(profileFrame);
        mainStage.setTitle("PROFILE");
        mainStage.show();
    }

    public void showPopUpUpdate(String title, String textAreaPrompt, String buttonText){
        PopUpUpdate popUpUpdate = new PopUpUpdate(title, textAreaPrompt, buttonText);
        popUpStage.setScene(popUpUpdate);
        popUpStage.setTitle(title);
        popUpStage.show();

    }

    // Inner Classes for Events

    private class goToWelcomeFrame implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            showWelcomeFrame();
        }
    }

    private class goToLoginFrame implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            showLoginFrame();
        }
    }

    private class goToSignupFrame implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            showSignupFrame();
        }
    }

    private class goToHomeFrame implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            showHomeFrame();
        }
    }

    private class goToProfileFrame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showProfileFrame();
        }
    }

    private class goToTuneFrame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showTuneFrame();
        }
    }

    private class goToSettingsFrame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showSettingsFrame();
        }
    }

}
