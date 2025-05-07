package com.example.demo.Controller;


import com.example.demo.Model.API.Api;
import com.example.demo.Model.Database;
import com.example.demo.Model.Song;
import com.example.demo.Model.TuneUser;
import com.example.demo.View.Frames.*;
import com.example.demo.View.PopUpFrames.*;
import com.example.demo.View.SpecialNodes.FriendNode;
import com.example.demo.View.SpecialNodes.SongNode;
import com.example.demo.View.Stage.PopUpStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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
    private PopUpQuestion popUpQuestion;
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

        Song testSong1 = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);
        Song testSong2 = new Song("3MTKD1H6Kt1ocmgnXBhGNl","Tövbe", "Duman", "TR", 2009, "Rock", "relax", "https://media-hosting.imagekit.io/d858cf1dac2c41d1/download.jpg?Expires=1841061704&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=FecJ0V-nz1JBydY~XJa1yHq4D36ozTsTxf5-QZZmO3Pbp2ncHBiV4HoB6wQ8ODSqlk5h5d9b8xlI2lQuGyewI5mlXqBOJxEjG9bgTEezjjf8~gQajGCR8M6BjtHK2AoS1PaWzmrU4lQQIni~mRZiZfj47PSnU1LBQ~eHR59rJmWH6NeYgYygZkORcwVjTy7SnCGXPmADdJX~bA2Ez0kkAXzaZfe-PhHRU5s0J3sMSlxkO~ytUKo42Yz2FfRwedy9ys7D6mYqR7FNKSlWPueBjPmFg2n70X6Hk0tvBWACPw8BDmKXhsF3ImAFg~nOPac7-yD6HLJ8ld55uQdSsjT~zw__", 5);
        Song testSong3 = new Song("6K4t31amVTZDgR3sKmwUJJ","The Less I Know The Better", "Tame Impala", "EN", 2015, "don't know", "relax", "https://media-hosting.imagekit.io/0a4c6d67be9e4ab2/download.jpg?Expires=1841061730&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=i5qYNIdRzYGeJeQrBQLYv-v2vrw-ZzSyBp8P5ggdprSk2Lcnf~7Dsajr7jHn8yrJm-03NMbRNTKbZgg2dLU9PKLzhYefsu-XGJOEk7fwlhH3ZylD7FfhT-K4h0wvQ5SfUZTrSPG2JM4XAQmDfw3BEkd5woguwV9Cl7ocFq-2KF8Z2C6e0nKfdFvYlEK1KIDBSsw~KQTLPDwPdSrpU7yXBthBmY7qBvOvXHywcy0AWfJPZvrPPUAA7TI~7UsUn7bBJvrtkj6sexeassE6cQaDFjW3uUZxzKofqxlLCQ0NriTDr-D8kWeCUTgGa6GxyRKZ18DtXL71mF6jJ2MJRlltbw__", 4);
        Song testSong4 = new Song("6AU0mbi9zlNn8mYkam3PRR","Fish Maan", "Hotel Ugly", "EN", 2023, "don't know", "relax", "https://media-hosting.imagekit.io/d83e3bc3fe9b478f/download.jpg?Expires=1841061858&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Fim2TOslQbw5Bg129HLDMNrnviYW-geOnH6PrN7wlzFO3HlLXP-M8NUnfI1F-QVVc1pcaHhzbN7rPskQe4~YA0sheXBb7MZchL6iQ6-QFx84-PRFAIcqHpSW3P0POTYw4FW8EzdANp4JnoS~N36Ga~L1JrVLTTW~9b7wMBAJ1Co-uIk~9mWhOzikyW4u6UudIlcAxvr1XfYQm6CPRncr1mT9R3Yk8qQHUmobbtQ~OPWPLz45C7QSDd-gD11I6kNSYJZ3P8wgGu~idOBMrolFmlMJ--Av4BeQdukxd1aKZRlmzDTgaq7mjAcZi1tLnmVZBBlR-gvp5s4q3iqZm0IUKg__", 4);
        Song testSong5 = new Song("7o7E1nrHWncYY7PY94gCiX","Video Killed The Radio Star", "The Buggles", "EN", 1980, "don't know", "good", "https://media-hosting.imagekit.io/bf502f9321f14090/download.jpg?Expires=1841062015&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=bEQ4F9vrw56SrAMqMv1YPv6pVCxf6ULbGIhLB~ZHJ8BEq48pt3tubOBmd7Ki8ZXL6afyrLsJ2DMmd7V5~D9KRE8y2Gw0lK7P7ExyQi2VH7GWia8N2R9VpmgKz-pbkcvO9hkY35eWQsRu5Nm6~0ZMl-4yAEll7B2g5fSmdIXp0FBf6nY6dnkWicJ9dHTGgw~JBqJYngu48cmZDUOMVGqmcbssGY3brQ7wbpMkkCpYgwvghgPJZeOZydW-67NSNx1dcUqSVGecID8rbr5dvmqfGEeclLX0wIVwNCcCTCbKY7dA5vVhOGRG-QF9ZdKQubuM76SeURK1FtyvKsPFhUIOig__", 4);
        Song testSong6 = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);
        Song testSong7 = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);
        Song testSong8 = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);

        randomSongs.add(testSong1);
        randomSongs.add(testSong2);
        randomSongs.add(testSong3);
        randomSongs.add(testSong4);
        randomSongs.add(testSong5);
        randomSongs.add(testSong6);
        randomSongs.add(testSong7);
        randomSongs.add(testSong8);

        currentUser = new TuneUser("Test", "Test123", "test@mail.com", 0, testFriends, randomSongs);
        for (Song aSong : randomSongs) currentUser.addSongToLastTunedSongs(aSong);
        currentSong = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);

        // Test end

    }

    public void initScenes() {


        welcomeFrame = new WelcomeFrame();
        loginFrame = new LoginFrame();
        signUpFrame = new SignUpFrame();
        homeFrame = new HomeFrame(currentUser, currentSong, randomSongs);
        profileFrame = new ProfileFrame(currentUser);
        tuneFrame = new TuneFrame(currentUser);
        settingsFrame = new SettingsFrame(currentUser);

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
        homeFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());



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

        tuneFrame.getDetailedTuneButton().setOnAction(actionEvent -> showPopUpQuestion());

        settingsFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
        settingsFrame.getNavigateBar().getHomeButton().setOnAction(new loginFrameController());
        settingsFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
        settingsFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
        settingsFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());

        settingsFrame.getEmailButton().setOnAction(new goToPopUpUpdate("Email" , "Email", "Email"));
        settingsFrame.getPasswordButton().setOnAction(new goToPopUpUpdate("Password" , "Password", "Password"));
        settingsFrame.getRemoveButton().setOnAction(new goToPopUpRemoveAccount());

        setOtherActions();
    }


    // Action event methods

    private void setOtherActions() {

        homeFrame.getSongPlayer().getPlayButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (homeFrame.getSongPlayer().getPLayingStatus()) pauseCurrentSong();
                else playCurrentSong();
            }
        });

        homeFrame.getSongPlayer().getNextButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                api.skipPlaybackToNextTrack();
            }
        });

        homeFrame.getSongPlayer().getPreviousButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                api.skipPlaybackToPreviousTrack();
            }
        });

        for (Node node : homeFrame.getSongNodeScroller().getNodes()) {
            SongNode songNode = (SongNode) node;
            songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                }
            });
        }

        for (int i = 0; i < profileFrame.getFriendScroller().getNodes().size(); i++) {
            Node node =  profileFrame.getFriendScroller().getNodes().get(i);
            if (i != profileFrame.getFriendScroller().getNodes().size() - 1) {
                FriendNode friendNode = (FriendNode) node;
                friendNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        System.out.println(friendNode.getFriend().getUsername());
                    }
                });
            } else {
                node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        PopUpUpdate addFriend = new PopUpUpdate("Add Friend", "Friend Name", "Add");
                        showPopUpUp(addFriend, "Add Friend");

                        addFriend.getUpdateButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                currentUser.addFriend(addFriend.getStringToUpdate());
                                System.out.println(addFriend.getStringToUpdate());
                            }
                        });
                    }
                });
            }
        }

        for (int i = 0; i < profileFrame.getFavSongScroller().getNodes().size(); i++) {
            Node node =  profileFrame.getFavSongScroller().getNodes().get(i);
            if (i != profileFrame.getFavSongScroller().getNodes().size() - 1) {
                SongNode songNode = (SongNode) node;
                songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                    }
                });
            } else {
                node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        PopUpUpdate addFavSongPopUp = new PopUpUpdate("Add to Favorite Songs", "Song Name", "Add");
                        showPopUpUp(addFavSongPopUp, "Add to Favorite Songs");

                        addFavSongPopUp.getUpdateButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                currentUser.addSongToFavorites(addFavSongPopUp.getStringToUpdate());
                                System.out.println(addFavSongPopUp.getStringToUpdate());
                            }
                        });
                    }
                });
            }
        }

        for (int i = 0; i < profileFrame.getRecentTunedScroller().getNodes().size(); i++) {
            Node node =  profileFrame.getRecentTunedScroller().getNodes().get(i);

            if (i != profileFrame.getRecentTunedScroller().getNodes().size() - 1) {
                SongNode songNode = (SongNode) node;
                songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        playNewSong(songNode.getTheSong(), currentUser.getTunedSongs());
                    }
                });
            } else {
                node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        showTuneFrame();
                    }
                });
            }
        }

        tuneFrame.getSeeRecentTunedSongsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showProfileFrame();
            }
        });

        tuneFrame.getLastTunedSongVBox().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playNewSong(currentUser.getTunedSongs().getLast(), currentUser.getTunedSongs());
            }
        });


    }

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
        homeFrame.getNavigateBar().setCurrentFrame("Home");
        homeFrame.getNavigateBar().getHomeButton().setStyle("-fx-background-color: #dadada; -fx-border-radius: 10; -fx-background-radius: 10");
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

    public void showPopUpUp(PopUpUpdate popUp, String title){
        popUpStage.setScene(popUp);
        popUpStage.setTitle(title);
        popUpStage.show();
        popUpUpdate.getUpdateButton().setOnAction(new closePopUp());
    }

    public void showPopUpShowFriendTune(TuneUser aFriend){
        if (aFriend.getTuneExistence()) {
            popUpShowFriendTune = new PopUpShowFriendTune(aFriend);
            popUpStage.setScene(popUpShowFriendTune);
            popUpStage.show();
        }
    }

    public void showPopUpQuestion(){
        popUpQuestion = new PopUpQuestion("1. Genre", "Rock", "Jazz", "Metal", "Pop");
        popUpStage.setScene(popUpQuestion);
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
            profileFrame.getNavigateBar().setCurrentFrame("Profile");
            profileFrame.getNavigateBar().getProfileButton().setStyle("-fx-background-color: #dadada; -fx-border-radius: 10; -fx-background-radius: 10");
        }
    }

    private class goToTuneFrame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            showTuneFrame();
            tuneFrame.getNavigateBar().setCurrentFrame("Tune");
            tuneFrame.getNavigateBar().getTuneButton().setStyle("-fx-background-color: #dadada; -fx-border-radius: 10; -fx-background-radius: 10");
        }
    }

    private class goToSettingsFrame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            showSettingsFrame();
            settingsFrame.getNavigateBar().setCurrentFrame("Settings");
            settingsFrame.getNavigateBar().getSettingsButton().setStyle("-fx-background-color: #dadada; -fx-border-radius: 10; -fx-background-radius: 10");
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

    private void playNewSong(Song aSong, ArrayList<Song> aSongList) {
        currentSong = aSong;
        currentSongList = aSongList;
        api.startResumePlayback(aSong, aSongList);
        homeFrame.getSongPlayer().setPlayingStatus(true);
        homeFrame.getSongPlayer().reset();
    }

    private void playCurrentSong() {
        api.startResumePlayback();
        homeFrame.getSongPlayer().setPlayingStatus(true);
        homeFrame.getSongPlayer().reset();
    }

    private void pauseCurrentSong() {
        api.pausePlayback();
        homeFrame.getSongPlayer().setPlayingStatus(false);
        homeFrame.getSongPlayer().reset();
    }
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
