package com.example.demo.Model;

import javafx.scene.image.Image;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class Song {

    private int trackID;
    private String name;
    private String artist;
    private String language;
    private int year;
    private String genre;
    private String mood;
    private int duration;
    private int currentPosition;
    private Image image;




    public Song(int trackID, String name, String artist, String language, int year, String genre, String mood, String imageUrl, int duration) {
        this.trackID = trackID;
        this.name = name;
        this.artist = artist;
        this.language = language;
        this.year = year;
        this.genre = genre;
        this.mood = mood;
        this.image = new Image(imageUrl);
        this.duration = duration;
        this.currentPosition = 0;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    // Getters
    public int getTrackID() {
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

    public int getDuration() {
        return duration;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
