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
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    private static final Api api = new Api();

    private TuneUser currentUser;
    private Song currentSong;

    private ArrayList<Song> currentSongList;
    private boolean checkSongPlaying;

    static String connectionString = "mongodb+srv://admin:admin@project102.8x4g8.mongodb.net/?retryWrites=true&w=majority&appName=Project102";
    static String dbName = "Project-102";

    // Initialize the database handler
    public static Database database = new Database(connectionString, dbName);


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
    private PopUpQuestion popUpQuestion1;
    private PopUpQuestion popUpQuestion2;
    private PopUpQuestion popUpQuestion3;
    private PopUpQuestion popUpQuestion4;
    private PopUpInstantTune popUpInstantTune;
    private ArrayList<Song> randomSongs;

    private String detailedTuneChoices;

    private PopUpStage popUpStage;

    public Controller(Stage stage) {
        this.mainStage = stage;



        // For testing
        randomSongs = new ArrayList<Song>();

        Song testSong1 = database.searchSongInDatabase("Video Killed The Radio Star");
        Song testSong2 = database.searchSongInDatabase("Until I Found You");
        Song testSong3 = database.searchSongInDatabase("Bi Güzellik Yap");
        Song testSong4 = database.searchSongInDatabase("Seattle");
        Song testSong5 = database.searchSongInDatabase("The Less I Know The Better");
        Song testSong6 = database.searchSongInDatabase("Fish Maan");
        Song testSong7 = database.searchSongInDatabase("Stan");
        Song testSong8 = database.searchSongInDatabase("Until I Found You");


        randomSongs.add(testSong1);
        randomSongs.add(testSong2);
        randomSongs.add(testSong3);
        randomSongs.add(testSong4);
        randomSongs.add(testSong5);
        randomSongs.add(testSong6);
        randomSongs.add(testSong7);
        randomSongs.add(testSong8);

        currentSongList = randomSongs;
//
        //currentUser = new TuneUser("Test", "Test123", "test@mail.com", 0, new ArrayList<TuneUser>(), randomSongs, 1); // waiting to be changed
        //for (Song aSong : randomSongs) currentUser.addSongToLastTunedSongs(aSong);
        //currentSong = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);

        // Test end

    }

    public void initScenes() {


        welcomeFrame = new WelcomeFrame();
        loginFrame = new LoginFrame();
        signUpFrame = new SignUpFrame();

        popUpQuestion1 = new PopUpQuestion("1. Mood", "Happy", "Sad", "Romantic", "Dance");
        popUpQuestion2 = new PopUpQuestion("2. Genre", "Rock", "Pop", "Rap", "Indie");
        popUpQuestion3 = new PopUpQuestion("3. Language", "TR", "EN", "ESP", "");
        popUpQuestion4 = new PopUpQuestion("4. Year" , "1960-1980", "1980-2000", "2000-2025", "");


        popUpStage = new PopUpStage();

        // Adding actions to the Buttons
        welcomeFrame.getLoginButton().setOnAction(new goToLoginFrame());
        welcomeFrame.getSignUpButton().setOnAction(new goToSignupFrame());

        loginFrame.getLoginButton().setOnAction(new loginFrameController());
        loginFrame.getBackButton().setOnAction(new goToWelcomeFrame());

        signUpFrame.getSignupButton().setOnAction(new signUpFrameController());
        signUpFrame.getBackButton().setOnAction(new goToWelcomeFrame());


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

        for (Node node : homeFrame.getFriendTunesNodeScroller().getNodes()) {
            FriendNode friendNode = (FriendNode) node;
            friendNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    TuneUser friend = friendNode.getFriend();
                    showPopUpShowFriendTune(friend);
                    playFriendTuneSong(friend);
                }
            });
        }

        for (int i = 0; i < profileFrame.getFriendScroller().getNodes().size(); i++) {
            Node node =  profileFrame.getFriendScroller().getNodes().get(i);
            if (i != profileFrame.getFriendScroller().getNodes().size() - 1) {
                FriendNode friendNode = (FriendNode) node;
                friendNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        TuneUser friend = database.searchTuneUserInDatabase(friendNode.getFriend().getUsername());
                        FriendProfileFrame friendProfileFrame = new FriendProfileFrame(friend, currentUser);
                        mainStage.setScene(friendProfileFrame);
                        mainStage.setTitle(friend.getUsername());
                        mainStage.show();

                        friendProfileFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
                        friendProfileFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
                        friendProfileFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
                        friendProfileFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());

                        for (int i = 0; i < friendProfileFrame.getFavSongScroller().getNodes().size(); i++) {
                            Node node = friendProfileFrame.getFavSongScroller().getNodes().get(i);
                            SongNode songNode = (SongNode) node;
                            songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                public void handle(MouseEvent event) {
                                    playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                                }
                            });
                        }

                        for (int i = 0; i < friendProfileFrame.getRecentTunedScroller().getNodes().size(); i++) {
                            Node node = friendProfileFrame.getFavSongScroller().getNodes().get(i);
                            SongNode songNode = (SongNode) node;
                            songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                public void handle(MouseEvent event) {
                                    playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                                }
                            });
                        }

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
                                String addedFriendName = addFriend.getStringToUpdate();
                                if (currentUser.addFriend(addedFriendName)) {
                                    profileFrame.resetUserFriends(currentUser);
                                    FriendNode friendNode = (FriendNode) profileFrame.getFriendScroller().getNodes().get(
                                            profileFrame.getFriendScroller().getNodes().size() - 2);
                                    friendNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        public void handle(MouseEvent event) {
                                            TuneUser friend = database.searchTuneUserInDatabase(friendNode.getFriend().getUsername());
                                            FriendProfileFrame friendProfileFrame = new FriendProfileFrame(friend, currentUser);
                                            mainStage.setScene(friendProfileFrame);
                                            mainStage.setTitle(friend.getUsername());
                                            mainStage.show();

                                            friendProfileFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
                                            friendProfileFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
                                            friendProfileFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
                                            friendProfileFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());

                                            for (int i = 0; i < friendProfileFrame.getFavSongScroller().getNodes().size(); i++) {
                                                Node node = friendProfileFrame.getFavSongScroller().getNodes().get(i);
                                                SongNode songNode = (SongNode) node;
                                                songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    public void handle(MouseEvent event) {
                                                        playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                                                    }
                                                });
                                            }

                                            for (int i = 0; i < friendProfileFrame.getRecentTunedScroller().getNodes().size(); i++) {
                                                Node node = friendProfileFrame.getFavSongScroller().getNodes().get(i);
                                                SongNode songNode = (SongNode) node;
                                                songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    public void handle(MouseEvent event) {
                                                        playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                                                    }
                                                });
                                            }

                                        }
                                    });
                                    closePopUpStage();
                                } else {
                                    System.out.println("no user");
                                }
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
                                String addedSongName = addFavSongPopUp.getStringToUpdate();
                                if (currentUser.addSongToFavorites(addedSongName)) {
                                    profileFrame.resetUserFavSongs(currentUser);
                                    closePopUpStage();

                                    SongNode songNode = (SongNode) profileFrame.getFavSongScroller().getNodes().get(profileFrame.getFavSongScroller().getNodes().size() - 2);
                                    songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        public void handle(MouseEvent event) {
                                            playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                                        }
                                    });

                                } else {
                                    System.out.println("no song");
                                }
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
            TuneUser newUser  = new TuneUser(signUpFrame.getUsernameTextFieldText(), signUpFrame.getPasswordTextFieldText() , signUpFrame.getEmailTextFieldText(),1, new ArrayList<TuneUser>(), new ArrayList<Song>(), new ArrayList<Song>(), 0);
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
            System.out.println("successfully logged in");
            currentUser = database.searchTuneUserInDatabase(loginFrame.getUsernameTextFieldText());
            currentSong = database.searchSongInDatabase("Until I Found You");


            homeFrame = new HomeFrame(currentUser, currentSong, randomSongs, database);
            profileFrame = new ProfileFrame(currentUser);
            tuneFrame = new TuneFrame(currentUser);
            settingsFrame = new SettingsFrame(currentUser);

            homeFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
            homeFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
            homeFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
            homeFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
            homeFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());
            homeFrame.getSearchButton().setOnAction(event -> {showSearchBarSongs();});

            profileFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
            profileFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
            profileFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
            profileFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
            profileFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());
            profileFrame.getEditProfileButton().setOnAction(new goToPopProfileImageSelection());

            tuneFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
            tuneFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
            tuneFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
            tuneFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
            tuneFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());

            tuneFrame.getDetailedTuneButton().setOnAction(actionEvent -> showPopUpQuestion1());
            tuneFrame.getInstantTuneButton().setOnAction(actionEvent -> {showPopUpInstantTune(database.suggestInstantTuneFromDatabase(currentUser.getUsername()));});

            settingsFrame.getNavigateBar().getProfileButton().setOnAction(new goToProfileFrame());
            settingsFrame.getNavigateBar().getHomeButton().setOnAction(new goToHomeFrame());
            settingsFrame.getNavigateBar().getTuneButton().setOnAction(new goToTuneFrame());
            settingsFrame.getNavigateBar().getSettingsButton().setOnAction(new goToSettingsFrame());
            settingsFrame.getNavigateBar().getAddTuneButton().setOnAction(new goToPopUpAddTune());

            settingsFrame.getEmailButton().setOnAction(new goToPopUpUpdate("Email" , "Email", "Email"));
            settingsFrame.getPasswordButton().setOnAction(new goToPopUpUpdate("Password" , "Password", "Password"));
            settingsFrame.getRemoveButton().setOnAction(new goToPopUpRemoveAccount());

            setOtherActions();

            showHomeFrame();
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

        popUpStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                return;
            }
        });

        popUpUpdate.getUpdateButton().setOnAction(new closePopUp());
    }

    public void showPopUpUp(PopUpUpdate popUp, String title){
        popUpStage.setScene(popUp);
        popUpStage.setTitle(title);
        popUpStage.show();

        popUpStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                return;
            }
        });

    }

    public void showPopUpShowFriendTune(TuneUser aFriend){
        System.out.println(aFriend.getTuneExistence());
        if (aFriend.getTuneExistence()) {
            popUpShowFriendTune = new PopUpShowFriendTune(aFriend);
            popUpStage.setScene(popUpShowFriendTune);
            popUpStage.show();


            popUpStage.setOnCloseRequest(new EventHandler<>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    playNewSong(currentSong, currentSongList);
                }
            });
        }
    }

    public void showPopUpQuestion1(){
        detailedTuneChoices = "";
        popUpStage.setScene(popUpQuestion1);
        popUpStage.show();
        popUpQuestion1.getNextButton().setOnAction(actionEvent -> {showPopUpQuestion2();
        detailedTuneChoices += popUpQuestion1.getChoice() + " ";});

    }
    public void showPopUpQuestion2(){
        popUpStage.setScene(popUpQuestion2);
        popUpStage.show();
        popUpQuestion2.getNextButton().setOnAction(actionEvent -> {showPopUpQuestion3();
        detailedTuneChoices += popUpQuestion2.getChoice() + " ";});

    }
    public void showPopUpQuestion3(){
        popUpStage.setScene(popUpQuestion3);
        popUpStage.show();
        popUpQuestion3.getNextButton().setOnAction(actionEvent -> {showPopUpQuestion4();
        detailedTuneChoices += popUpQuestion3.getChoice() + " ";});

    }

    public void showPopUpQuestion4(){
        popUpStage.setScene(popUpQuestion4);
        popUpStage.show();

        popUpQuestion4.getNextButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                closePopUpStage();
                int year = Integer.parseInt(popUpQuestion4.getChoice().split("-")[1]);
                detailedTuneChoices += year;
                showPopUpDetailedTune();
            }
        });



        popUpStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                return;
            }
        });

    }

    int count = 0;
    public void showPopUpDetailedTune(){
        ArrayList<Song> suggestedSongs = database.suggestDetailedTuneFromDatabase(detailedTuneChoices);
        count = 0;

        popUpInstantTune = new PopUpInstantTune(suggestedSongs.get(count++));
        popUpStage.setScene(popUpInstantTune);
        popUpStage.show();

        popUpInstantTune.getAnotherButton().setOnAction(actionEvent -> {
            if (count < suggestedSongs.size()) popUpInstantTune.changeSong(suggestedSongs.get(count++));
            else System.out.println("no other suggestion");
        });

        popUpInstantTune.getAddToFavoritesButton().setOnAction(actionEvent -> {
            addFavorites(popUpInstantTune.getSong());
            closePopUpStage();
        });
    }

    public void showPopUpInstantTune(Song song){
        popUpInstantTune = new PopUpInstantTune(song);
        popUpStage.setScene(popUpInstantTune);
        popUpStage.show();
        Song nextSong;
        popUpInstantTune.getAnotherButton().setOnAction(actionEvent -> {
            popUpInstantTune.changeSong(database.suggestInstantTuneFromDatabase(currentUser.getUsername()));});

        popUpInstantTune.getAddToFavoritesButton().setOnAction(actionEvent -> {
            addFavorites(popUpInstantTune.getSong());
            closePopUpStage();
        });
    }

    public void showPopUpRemoveAccount(){
        popUpRemoveAccount = new PopUpRemoveAccount();
        popUpStage.setScene(popUpRemoveAccount);
        popUpStage.show();

        popUpStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                return;
            }
        });
        popUpRemoveAccount.getYesButton().setOnAction(actionEvent -> removeAccount());
        popUpRemoveAccount.getNoButton().setOnAction(actionEvent -> closePopUpStage());

    }

    public void showPopUpProfileImageSelection(){
        popUpProfileImageSelection = new PopUpProfileImageSelection();
        popUpStage.setScene(popUpProfileImageSelection);
        popUpStage.show();

        popUpStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                return;
            }
        });

        for(int i=0; i<popUpProfileImageSelection.getButtons().size(); i++){
            int finalI = i;
            popUpProfileImageSelection.getButtons().get(i).setOnAction(actionEvent -> {
                currentUser.updateProfileImg(finalI);
                currentUser.setImageWithIndex(finalI);
                profileFrame.constructImageContainer();
                closePopUpStage();
            });
        }

    }

    public void showPopUpAddTune(){
        popUpAddTune = new PopUpAddTune(currentSong);
        popUpAddTune.getAddYourTuneButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentUser.updateUserTune(currentSong, popUpAddTune.getTuneNoteTextAreaText());
                homeFrame.resetNavigateBar(currentUser);
                profileFrame.resetNavigateBar(currentUser);
                settingsFrame.resetNavigateBar(currentUser);
                tuneFrame.resetNavigateBar(currentUser);

                closePopUpStage();
            }
        });
        popUpStage.setScene(popUpAddTune);
        popUpStage.show();

        popUpStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                return;
            }
        });
    }

    public void removeAccount(){
        database.removeUserFromDatabase(currentUser.getUsername());
        showWelcomeFrame();
        closePopUpStage();
    }

    public void addFavorites(Song song){
        if (currentUser.addSongToFavorites(song.getName())) {
            profileFrame.resetUserFavSongs(currentUser);

            SongNode songNode = (SongNode) profileFrame.getFavSongScroller().getNodes().get(profileFrame.getFavSongScroller().getNodes().size() - 2);
            songNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    playNewSong(songNode.getTheSong(), currentUser.getFavouriteSongs());
                }
            });
        }
    }

    public void showSearchBarSongs(){
        homeFrame.getSearchSongsVBox().setSongs(database.suggestSearchBarTunesFromDatabase(homeFrame.getSearchBarText()));
        homeFrame.getSearchSongsVBox().setVisible(true);
        for(int i=0; i<homeFrame.getSearchSongsVBox().getButtons().size(); i++){
            int finalI = i;
            homeFrame.getSearchSongsVBox().getButtons().get(i).setOnAction(actionEvent -> {homeFrame.getSearchSongsVBox().setVisible(false);
                System.out.println(homeFrame.getSearchSongsVBox().getButtons().get(finalI).getText());
                homeFrame.getSearchBar().clear();

                // List can be fixed (2. argument)
                playNewSong(database.searchSongInDatabase(homeFrame.getSearchSongsVBox().getButtons().get(finalI).getText()), homeFrame.getSearchSongsVBox().getSongs());
            });
        }
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


    private class goToHomeFrame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            showHomeFrame();
            homeFrame.getNavigateBar().setCurrentFrame("Home");
            homeFrame.getNavigateBar().getHomeButton().setStyle("-fx-background-color: #dadada; -fx-border-radius: 10; -fx-background-radius: 10");
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
        homeFrame.getSongPlayer().resetCurrentSong(currentSong);

        currentSongList = aSongList;
        api.startResumePlayback(currentSong, currentSongList);
        homeFrame.getSongPlayer().setPlayingStatus(true);
        homeFrame.getSongPlayer().resetPlayButton();
    }

    private void playCurrentSong() {
        api.startResumePlayback();
        homeFrame.getSongPlayer().setPlayingStatus(true);
        homeFrame.getSongPlayer().resetPlayButton();
    }

    private void pauseCurrentSong() {
        api.pausePlayback();
        homeFrame.getSongPlayer().setPlayingStatus(false);
        homeFrame.getSongPlayer().resetPlayButton();
    }

    private void playFriendTuneSong(TuneUser aFriend) {
        api.startTrackFromRandomPos(aFriend.getTuneSong());
    }

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
