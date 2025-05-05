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


    public Song(String trackID, String name, String artist, String language, int year, String genre, String mood, String imageUrl, int duration) {
        this.trackID = trackID;
        this.name = name;
        this.artist = artist;
        this.language = language;
        this.year = year;
        this.genre = genre;
        this.mood = mood;
        this.image = new Image(imageUrl);
        this.durationMS = duration;
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
