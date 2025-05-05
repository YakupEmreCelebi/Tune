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
    private Image profileImage;


    public TuneUser(String username, String password, String mail ,int id, ArrayList<TuneUser> friends, ArrayList<Song> favouriteSongs) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
        this.tunedSongs = new ArrayList<Song>();

        // For testing
        tuneSong = new Song(1,"Yanıbaşımdan", "Duman", "TR", 2010, "Rock", "happy", "https://imgur.com/Vbcu0c9.jpg", 4);
        tuneNote = "I feel tremendous.";
        profileImage = new Image("https://imgur.com/Vbcu0c9.jpg");
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

    public Image getProfileImage() {
        return profileImage;
    }

    public Song getTuneSong() {
        return tuneSong;
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

    public void setProfileImgIndex(int profileImgIndex) {
        this.profileImgIndex = profileImgIndex;
    }

    public void setImageWithIndex(int imageIndex) {
        if(imageIndex == 0)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/duman_ico.jpg"));
        }
        else if(imageIndex == 1)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/duman1_ico.jpg"));
        }
        else if(imageIndex == 2)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/duman2_ico.jpg"));
        }
        else if(imageIndex == 3)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/senikendimesakladım_ico.jpg"));
        }
        else if(imageIndex == 4)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica_ico.jpg"));
        }
        else if(imageIndex == 5)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica2_ico.jpg"));
        }
        else if(imageIndex == 6)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica3_ico.jpg"));
        }
        else if(imageIndex == 7)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/metallica4_ico.jpg"));
        }
        else if(imageIndex == 8)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc_ico.jpg"));
        }
        else if(imageIndex == 9)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc2_ico.jpg"));
        }
        else if(imageIndex == 10)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc3_ico.jpg"));
        }
        else if(imageIndex == 11)
        {
            profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/acdc4_ico.jpg"));
        }
    }
}
