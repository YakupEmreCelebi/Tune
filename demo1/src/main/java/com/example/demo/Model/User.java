package com.example.demo.Model;

import java.util.ArrayList;

public class User {
    private String email;
    private String username;
    private String password;
    private ArrayList<User> friends;
    private ArrayList<Song> favouriteSongs;


    public User(String username, String password,ArrayList<User> friends, ArrayList<Song> favouriteSongs, String email) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
        this.email = email;
    }

    //Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public ArrayList<User> getFriends() {return friends;}
    public ArrayList<Song> getFavouriteSongs (){return favouriteSongs;}



}
