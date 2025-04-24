package com.example.demo.Model;

import java.util.ArrayList;

public class TuneUser {
    private int id;
    private String username;
    private String password;
    private ArrayList<TuneUser> friends;
    private ArrayList<Song> favouriteSongs;


    public TuneUser(String username, String password, int id, ArrayList<TuneUser> friends, ArrayList<Song> favouriteSongs) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
    }

    //Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public ArrayList<TuneUser> getFriends() {return friends;}
    public ArrayList<Song> getFavouriteSongs (){return favouriteSongs;}



}
