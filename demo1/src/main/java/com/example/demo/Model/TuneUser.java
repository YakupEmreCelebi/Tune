package com.example.demo.Model;

import java.util.ArrayList;

public class TuneUser {
    private int id;
    private String username;
    private String password;
    private ArrayList<TuneUser> friends;
    private ArrayList<Song> favouriteSongs;
    private boolean checkTuneExistence;
    private Song tuneSong;
    private String tuneNote;
    private ArrayList<Song> tunedSongs;
    private int profileImg;
    private int numbOfTunedSongsWithFriends;


    public TuneUser(String username, String password, int id, ArrayList<TuneUser> friends, ArrayList<Song> favouriteSongs) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
    }


    public void addSongToFavorites(String username, String songName) {

    }

    public void removeSongFromFavorites(String username, String songName) {

    }

    public void removeFriend(String username, String friendUsername) {

    }

    public void addFriend(String username, String friendUsername) {

    }

    public void updateMail(String newUsername) {

    }

    public void updatePassword(String newPassword) {

    }

    public void updateUsername(String newUsername) {

    }

    public void setUserTune(boolean checkTuneExistence, Song tuneSong, String tuneNote) {
        this.checkTuneExistence = checkTuneExistence;
        this.tuneSong = tuneSong;
        this.tuneNote = tuneNote;
    }

    public void addSongToLastTunedSongs(Song tunedSong) {
        tunedSongs.add(tunedSong);
    }

    public void updateProfileImg(int selectedProfileImg) {
        profileImg = selectedProfileImg;
    }

    public void updateNumbOfTunedSongsWithFriends(int numbOfTunedSongsWithFriends) {
        this.numbOfTunedSongsWithFriends = numbOfTunedSongsWithFriends;
    }



    //Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public ArrayList<TuneUser> getFriends() {return friends;}
    public ArrayList<Song> getFavouriteSongs (){return favouriteSongs;}




}
