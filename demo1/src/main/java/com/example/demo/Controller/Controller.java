package com.example.demo.Controller;


import com.example.demo.Model.API.Api;
import com.example.demo.Model.Database;
import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.Frames.*;
import com.example.demo.View.PopUpFrames.*;
import com.example.demo.View.Stage.PopUpStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    private static final Api api = new Api();

    private TuneUser currentUser;
    private Song currentSong;

    private ArrayList<Song> currentSongList;
    private boolean checkSongPlaying;

    String connectionString = "mongodb+srv://admin:admin@project102.8x4g8.mongodb.net/?retryWrites=true&w=majority&appName=Project102";
    String dbName = "Project-102";

    // Initialize the database handler
    Database database = new Database(connectionString, dbName);


    // Stage and Frames
    private Stage mainStage;
    private WelcomeFrame welcomeFrame;
    private LoginFrame loginFrame;
    private SignUpFrame signUpFrame;
    private HomeFrame homeFrame;
    private ProfileFrame profileFrame;
    private TuneFrame tuneFrame;
    private SettingsFrame settingsFrame;
    private PopUpUpdate popUpUpdate;
    private PopUpShowFriendTune popUpShowFriendTune;
    private PopUpRemoveAccount popUpRemoveAccount;
    private PopUpProfileImageSelection popUpProfileImageSelection;
    private PopUpAddTune popUpAddTune;
    private ArrayList<Song> randomSongs;

    private PopUpStage popUpStage;

    public Controller(Stage stage) {
        this.mainStage = stage;

        // For testing
        TuneUser friend1 = new TuneUser("friend1", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend2 = new TuneUser("friend2", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend3 = new TuneUser("friend1", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend4 = new TuneUser("friend2", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend5 = new TuneUser("friend1", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend6 = new TuneUser("friend2", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend7 = new TuneUser("friend1", "Test123", "test@mail.com", 0, null, null);
        TuneUser friend8 = new TuneUser("friend2", "Test123", "test@mail.com", 0, null, null);


        ArrayList<TuneUser> testFriends = new ArrayList<TuneUser>();
        testFriends.add(friend1);
        testFriends.add(friend2);
        testFriends.add(friend3);
        testFriends.add(friend4);
        testFriends.add(friend5);
        testFriends.add(friend6);
        testFriends.add(friend7);
        testFriends.add(friend8);


        randomSongs = new ArrayList<Song>();

        Song testSong1 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong2 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong3 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong4 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong5 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong6 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong7 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        Song testSong8 = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);

        randomSongs.add(testSong1);
        randomSongs.add(testSong2);
        randomSongs.add(testSong3);
        randomSongs.add(testSong4);
        randomSongs.add(testSong5);
        randomSongs.add(testSong6);
        randomSongs.add(testSong7);
        randomSongs.add(testSong8);

        currentUser = new TuneUser("Test", "Test123", "test@mail.com", 0, testFriends, randomSongs);
        currentSong = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);

        // Test end

    }

    public void initScenes() {


        welcomeFrame = new WelcomeFrame();
        loginFrame = new LoginFrame();
        signUpFrame = new SignUpFrame();
        homeFrame = new HomeFrame(currentUser, currentSong, randomSongs);
        profileFrame = new ProfileFrame(currentUser);
        tuneFrame = new TuneFrame();
        settingsFrame = new SettingsFrame();

        popUpStage = new PopUpStage();

        // Adding actions to the Buttons
        welcomeFrame.getLoginButton().setOnAction(new goToLoginFrame());
        welcomeFrame.getSignUpButton().setOnAction(new goToSignupFrame());

        loginFrame.getLoginButton().setOnAction(new loginFrameController());

        signUpFrame.getSignupButton().setOnAction(new signUpFrameController());

        homeFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        homeFrame.getNavigateBar().getHomeButton().setOnAction(new loginFrameController());
        homeFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        homeFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
        homeFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpShowFriendTune());


        profileFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        profileFrame.getNavigateBar().getHomeButton().setOnAction(new loginFrameController());
        profileFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        profileFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
        profileFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());
        profileFrame.getEditProfileButton().setOnAction(new goToPopProfileImageSelection());

        tuneFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        tuneFrame.getNavigateBar().getHomeButton().setOnAction(new loginFrameController());
        tuneFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        tuneFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
        tuneFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());

        settingsFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        settingsFrame.getNavigateBar().getHomeButton().setOnAction(new loginFrameController());
        settingsFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        settingsFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
        settingsFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());

        settingsFrame.getEmailButton().setOnAction(new goToPopUpUpdate("Email" , "Email", "Email"));
        settingsFrame.getPasswordButton().setOnAction(new goToPopUpUpdate("Password" , "Password", "Password"));
        settingsFrame.getRemoveButton().setOnAction(new goToPopUpRemoveAccount());

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

    public void signUpFrameOperations(){

        if(database.checkIfUserUnique(signUpFrame.getUsernameTextFieldText(), signUpFrame.getEmailTextFieldText()))
        {
            database.addUserToDatabase(signUpFrame.getUsernameTextFieldText(), signUpFrame.getEmailTextFieldText(), signUpFrame.getPasswordTextFieldText());
            TuneUser newUser  = new TuneUser(signUpFrame.getUsernameTextFieldText(), signUpFrame.getPasswordTextFieldText() , signUpFrame.getEmailTextFieldText(),1, new ArrayList<>(), new ArrayList<>() );
            currentUser = newUser;
            showLoginFrame();
        }
        else
        {
            System.out.println("User is already logged in");
            signUpFrame.getWarningLabel().setVisible(true);

            // Timer (disappear after 2 sec)
            Timer timer = new Timer(2000, e -> {
                signUpFrame.getWarningLabel().setVisible(false);
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public  void loginFrameOperations(){

        if(database.checkUserExistInDatabase(loginFrame.getUsernameTextFieldText(), loginFrame.getPasswordTextFieldText()))
        {
            showHomeFrame();
            System.out.println("successfully logged in");
        }
        else
        {
            System.out.println("Incorrect username or password");
            loginFrame.getWarningLabel().setVisible(true);

            // Timer (disappear after 2 sec)
            Timer timer = new Timer(2000, e -> {
                loginFrame.getWarningLabel().setVisible(false);
            });
            timer.setRepeats(false);
            timer.start();
        }
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

    public void closePopUpStage() {
        popUpStage.close();
    }

    public void showPopUpUpdate(String title, String textAreaPrompt, String buttonText){
        popUpUpdate = new PopUpUpdate(title, textAreaPrompt, buttonText);
        popUpStage.setScene(popUpUpdate);
        popUpStage.setTitle(title);
        popUpStage.show();
        popUpUpdate.getUpdateButton().setOnAction(new closePopUp());
    }

    public void showPopUpShowFriendTune(TuneUser aFriend){
        popUpShowFriendTune = new PopUpShowFriendTune(aFriend);
        popUpStage.setScene(popUpShowFriendTune);
        popUpStage.show();

    }

    public void showPopUpRemoveAccount(){
        popUpRemoveAccount = new PopUpRemoveAccount();
        popUpStage.setScene(popUpRemoveAccount);
        popUpStage.show();
        popUpRemoveAccount.getYesButton().setOnAction(actionEvent -> removeAccount());
        popUpRemoveAccount.getNoButton().setOnAction(actionEvent -> closePopUpStage());

    }

    public void showPopUpProfileImageSelection(){
        popUpProfileImageSelection = new PopUpProfileImageSelection();
        popUpStage.setScene(popUpProfileImageSelection);
        popUpStage.show();
        for(int i=0; i<popUpProfileImageSelection.getButtons().size(); i++){
            int finalI = i;
            popUpProfileImageSelection.getButtons().get(i).setOnAction(actionEvent -> {currentUser.setImageWithIndex(finalI); profileFrame.constructImageContainer(); closePopUpStage();});
        }

    }

    public void showPopUpAddTune(){
        popUpAddTune = new PopUpAddTune();
        popUpStage.setScene(popUpAddTune);
        popUpStage.show();
    }

    public void removeAccount(){
        database.removeUserFromDatabase(currentUser.getUsername());
        showWelcomeFrame();
        closePopUpStage();
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

    private class signUpFrameController implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            signUpFrameOperations();
        }
    }

    private class goToSignupFrame implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            showSignupFrame();
        }
    }

    private class loginFrameController implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            loginFrameOperations();
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

    private class goToPopUpUpdate implements EventHandler<ActionEvent> {

        String title;
        String textAreaPrompt;
        String buttonText;

        public goToPopUpUpdate(String title, String textAreaPrompt, String buttonText) {
            this.title = title;
            this.textAreaPrompt = textAreaPrompt;
            this.buttonText = buttonText;
        }
        @Override
        public void handle(ActionEvent actionEvent) {
            showPopUpUpdate(this.title, this.textAreaPrompt, this.buttonText);
        }
    }

    private class closePopUp implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            closePopUpStage();
        }
    }

    private class goToPopUpAddTune implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showPopUpAddTune();
        }
    }

    private class goToPopUpShowFriendTune implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showPopUpShowFriendTune(currentUser);
        }
    }

    private class goToPopProfileImageSelection implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showPopUpProfileImageSelection();
        }
    }

    private class goToPopUpRemoveAccount implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            showPopUpRemoveAccount();
        }
    }

//    private void playNewSong(Song aSong, ArrayList<Song> aSongList) {
//        currentSong = aSong;
//        currentSongList = aSongList;
//        api.startResumePlayback(aSong, aSongList);
//    }
//
//    private void playCurrentSong() {
//        api.startResumePlayback();
//    }
//
//    private void pauseCurrentSong() {
//        api.pausePlayback();
//    }
//
//    private void playFriendTuneSong(TuneUser aFriend) {
//        api.startTrackFromRandomPos(aFriend.getTuneSong());
//    }
//
//    private Song searchSong(String songName) {
//        return new Song();
//    }
//
//    private Song suggestInstantTune() {
//        return theDatabase.suggestInstantTuneFromDatabase(currentUser.getUsername());
//    }
//
//
//    private Song suggestTuneWithFriend() {
//        return theDatabase.suggestTuneWithFriendFromDatabase(currentUser.getUsername());
//    }
//
//    private Song suggestDetailedSong(ArrayList<String> answers) {
//        return theDatabase.suggestDetailedTuneFromDatabase(answers);
//    }
//
//    private void playTunedSong(Song tunedSong) {
//        api.startTrackFromRandomPos(tunedSong);
//    }


}
