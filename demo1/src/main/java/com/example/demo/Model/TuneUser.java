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

    public TuneUser(String username, String password, String mail ,int id, ArrayList<TuneUser> friends, ArrayList<Song> favouriteSongs, int imageIndex) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
        this.tunedSongs = new ArrayList<Song>();
        this.tuneExistence = false;
        this.database = Controller.database;
        setImageWithIndex(imageIndex);


        // For testing
        //userTune = new Song("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "don't know", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);
        //tuneNote = "I feel tremendous.";
    }


    public void addSongToFavorites(String songName) {
        database.addSongToFavoritesInDatabase(username, songName);
        //TODO: add to favorites ArrayList
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

    public void addFriend(String friendUsername) {
        database.addFriendToDatabase(username, friendUsername);
        //friends.add(database.searchUserInDatabase(friendUsername));
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

        //TODO: change profileImage
    }

    public void setUserTune(boolean checkTuneExistence, Song tuneSong, String tuneNote) {
        this.tuneExistence = tuneExistence;
        this.userTune = tuneSong;
        this.tuneNote = tuneNote;
        //TODO: tune in database
    }

    public void addSongToLastTunedSongs(Song tunedSong) {
        tunedSongs.add(tunedSong);
        //TODO: add lastTunedSongs to database
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
