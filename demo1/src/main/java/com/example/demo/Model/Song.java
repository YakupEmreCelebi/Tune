package com.example.demo.Model;

import javafx.scene.image.Image;

public class Song {

    private String trackID;
    private String name;
    private String artist;
    private String language;
    private int year;
    private String genre;
    private String mood;
    private int durationMS;
    private int currentPositionMS;
    private Image image;



    public Song(String trackID, String name, String artist, String language, int year, String genre, String mood, String imageUrl, int durationMS) {
        this.trackID = trackID;
        this.name = name;
        this.artist = artist;
        this.language = language;
        this.year = year;
        this.genre = genre;
        this.mood = mood;
        if (imageUrl == null || imageUrl.isBlank() || !imageUrl.startsWith("http") || imageUrl.substring(0,13).equals("https://media") ) {
            imageUrl = "https://imgur.com/czfSZQH.jpeg";
        }

        try {
            this.image = new Image(imageUrl, true); // true: background loading
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid image URL: " + imageUrl);
            this.image = new Image("https://imgur.com/czfSZQH.jpeg");
        }


        this.durationMS = durationMS;
        this.currentPositionMS = 0;
    }

    public void setCurrentPositionMS(int currentPosition) {
        this.currentPositionMS = currentPosition;
    }

    // Getters
    public String getTrackID() {
        return trackID;
    }

    public Image getImage() {
        return image;
    }

    public int getYear() {
        return year;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getMood() {
        return mood;
    }

    public String getName() {
        return name;
    }

    public int getDurationMS() {
        return durationMS;
    }

    public int getCurrentPositionMS() {
        return currentPositionMS;
    }

}
