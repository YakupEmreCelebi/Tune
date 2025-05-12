package com.example.demo.Model;

import com.example.demo.Controller.Controller;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class TuneUser {
    private int id;
    private String username;
    private String password;
    private String mail;
    private ArrayList<TuneUser> friends;
    private ArrayList<Song> favouriteSongs;
    private boolean tuneExistence;
    private Song userTune;
    private String tuneNote;
    private ArrayList<Song> tunedSongs;
    private int profileImgIndex;
    private int numbOfTunedSongsWithFriends;
    private Image profileImage;
    Database database;

    public TuneUser(String username, String password, String mail ,int id, ArrayList<TuneUser> friends, ArrayList<Song> favouriteSongs, ArrayList<Song> tunedSongs,  int imageIndex) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
        this.database = Controller.database;
        this.tuneExistence = (!database.getUserTuneFromDatabase(username).equals(""));
        this.tunedSongs = tunedSongs;
        setImageWithIndex(imageIndex);

    }

    public void setTune() {
        tuneNote = database.getTuneNoteFromDatabase(username);
        userTune = database.searchSongInDatabase(database.getUserTuneFromDatabase(username));
    }

    public boolean addSongToFavorites(String songName) {
        Song song = database.searchSongInDatabase(songName);
        if (song != null) {
            database.addSongToFavoritesInDatabase(username, songName);
            favouriteSongs.add(song);
            return true;
        }
        return false;
    }

    public void removeSongFromFavorites(String songName) {
        database.removeSongFromFavoritesFromDatabase(username, songName);
        for (Song aSong : favouriteSongs)
            if (aSong.getName().equals(songName)) favouriteSongs.remove(aSong);
    }

    public void removeFriend(String friendUsername) {
        database.removeFriendFromDatabase(username, friendUsername);
        for (TuneUser aFriend : friends)
            if (aFriend.getUsername().equals(friendUsername)) friends.remove(aFriend);
    }

    public boolean addFriend(String friendUsername) {
        TuneUser friend = database.searchTuneUserInDatabase(friendUsername);
        if (friend != null) {
            database.addFriendToDatabase(username, friend.getUsername());
            database.addFriendToDatabase(friend.getUsername(), username);
            friends.add(friend);
            return true;
        }
        return false;
    }

    public void updateMail(String newMail) {
        database.updateEmailInDatabase(username, newMail);
        mail = newMail;
    }

    public void updatePassword(String newPassword) {
        database.updatePasswordInDatabase(username, newPassword);
        password = newPassword;
    }

    public void updateUsername(String newUsername) {
        database.updateUsernameInDatabase(username, newUsername);
        username = newUsername;
    }

    public void updateProfileImg(int selectedProfileImg) {
        profileImgIndex = selectedProfileImg;
        database.updateProfileImagoNoInDatabase(username, selectedProfileImg + "");
    }

    public void updateUserTune(Song tuneSong, String tuneNote) {
        this.tuneExistence = true;
        this.userTune = tuneSong;
        this.tuneNote = tuneNote;
        database.setUserTuneInDatabase(username, tuneSong, tuneNote);
    }

    public void addSongToLastTunedSongs(String songName) {
        database.addSongToTunedSongsInDatabase(username, songName);
        tunedSongs.add(database.searchSongInDatabase(songName));
    }



    public void updateNumbOfTunedSongsWithFriends(int numbOfTunedSongsWithFriends) {
        this.numbOfTunedSongsWithFriends = numbOfTunedSongsWithFriends;
        //TODO: add numberofTunedSongsWithFriend to database
    }



    //Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getTuneNote() {return tuneNote;}

    public Image getProfileImage() {
        return profileImage;
    }

    public Song getTuneSong() {
        return userTune;
    }

    public int getProfileImgIndex() {
        return profileImgIndex;
    }

    public ArrayList<Song> getTunedSongs() {
        return tunedSongs;
    }

    public ArrayList<TuneUser> getFriends() {return friends;}
    public ArrayList<Song> getFavouriteSongs (){return favouriteSongs;}

    public int getNumbOfTunedSongsWithFriends() {
        return numbOfTunedSongsWithFriends;
    }

    public boolean getTuneExistence() {
        return tuneExistence;
    }

    public void setImageWithIndex(int index) {
        profileImgIndex = index;
        if(profileImgIndex == 0)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/duman_ico.jpg"));
        }
        else if(profileImgIndex == 1)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/duman1_ico.jpg"));
        }
        else if(profileImgIndex == 2)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/duman2_ico.jpg"));
        }
        else if(profileImgIndex == 3)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/senikendimesakladım_ico.jpg"));
        }
        else if(profileImgIndex == 4)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica_ico.jpg"));
        }
        else if(profileImgIndex == 5)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica2_ico.jpg"));
        }
        else if(profileImgIndex == 6)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica3_ico.jpg"));
        }
        else if(profileImgIndex == 7)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica4_ico.jpg"));
        }
        else if(profileImgIndex == 8)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc_ico.jpg"));
        }
        else if(profileImgIndex == 9)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc2_ico.jpg"));
        }
        else if(profileImgIndex == 10)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc3_ico.jpg"));
        }
        else if(profileImgIndex == 11)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc4_ico.jpg"));
        }
    }
}
