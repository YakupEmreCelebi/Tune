package com.example.demo.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class TuneUser {
    private int id;
    private String username;
    private String password;
    private String mail;
    private ArrayList<TuneUser> friends;
    private ArrayList<Song> favouriteSongs;
    private boolean checkTuneExistence;
    private Song tuneSong;
    private String tuneNote;
    private ArrayList<Song> tunedSongs;
    private int profileImgIndex;
    private int numbOfTunedSongsWithFriends;


    public TuneUser(String username, String password, String mail ,int id, ArrayList<TuneUser> friends, ArrayList<Song> favouriteSongs) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;

        // For testing
        tuneSong = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        tuneNote = "I feel tremendous.";
        friends.add(new TuneUser("Test", "Test123", "test@mail.com", 0, null, null));
    }


    public void addSongToFavorites(String songName) {

    }

    public void removeSongFromFavorites(String songName) {

    }

    public void removeFriend(String friendUsername) {

    }

    public void addFriend(String friendUsername) {

    }

    public void updateMail(String newMail) {

    }

    public void updatePassword(String newPassword) {

    }

    public void updateUsername(String newUsername) {

    }

    public void updateProfileImg(int selectedProfileImg) {
        profileImgIndex = selectedProfileImg;
    }

    public void setUserTune(boolean checkTuneExistence, Song tuneSong, String tuneNote) {
        this.checkTuneExistence = checkTuneExistence;
        this.tuneSong = tuneSong;
        this.tuneNote = tuneNote;
    }

    public void addSongToLastTunedSongs(Song tunedSong) {
        tunedSongs.add(tunedSong);
    }



    public void updateNumbOfTunedSongsWithFriends(int numbOfTunedSongsWithFriends) {
        this.numbOfTunedSongsWithFriends = numbOfTunedSongsWithFriends;
    }



    //Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getTuneNote() {return tuneNote;}

    public Song getTuneSong() {
        return tuneSong;
    }

    public int getProfileImgIndex() {
        return profileImgIndex;
    }


    public ArrayList<TuneUser> getFriends() {return friends;}
    public ArrayList<Song> getFavouriteSongs (){return favouriteSongs;}


    public void setProfileImgIndex(int profileImgIndex) {
        this.profileImgIndex = profileImgIndex;
    }
}
