package com.example.demo.Model;

import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;

public class Song {

    private String name;
    private String artist;
    private String language;
    private int year;
    private String genre;
    private String mood;
    private ImageView imageView;
    private int duration;
    private int currentPosition;
    private Image image;


    public Song(String name, String artist, String language, int year, String genre, String mood, ImageView imageView) {
        this.name = name;
        this.artist = artist;
        this.language = language;
        this.year = year;
        this.genre = genre;
        this.mood = mood;
        this.imageView = imageView;
    }


    // Getters
    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
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
}
