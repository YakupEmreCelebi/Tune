package com.example.demo.Model;

import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private ArrayList<User> friends;
    private ArrayList<Song> favouriteSongs;


    public User(String username, String password,int id,ArrayList<User> friends, ArrayList<Song> favouriteSongs) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.friends = friends;
        this.favouriteSongs = favouriteSongs;
    }

    //Getters
    public int getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public ArrayList<User> getFriends() {return friends;}
    public ArrayList<Song> getFavouriteSongs (){return favouriteSongs;}



}
