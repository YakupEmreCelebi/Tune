package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.Database;
import com.example.demo.Model.Song;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchSongsVBox extends VBox {

    Database database;
    String text;
    ArrayList<Song> songs;

    public SearchSongsVBox(String text, Database database) {
        super(0);
        setMaxWidth(400);
        setMinWidth(400);
        setPadding(new Insets(10,0,0,0));

        setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10");

        this.text = text;
        this.database = database;

        songs = database.suggestSearchBarTunesFromDatabase(text);

        setMaxHeight(songs.size() * 100 + 20);
        setMinHeight(songs.size() * 100 + 20);

        for(int i=0; i<songs.size(); i++) {
            ImageView img = new ImageView(songs.get(i).getImage());
            img.setFitHeight(80);
            img.setPreserveRatio(true);
            Button b = new Button(songs.get(i).getName(), img);
            b.setAlignment(Pos.CENTER_LEFT);
            b.setMaxHeight(100);
            b.setMinHeight(100);
            b.setMaxWidth(400);
            setMinWidth(400);
            addHoverEffect(b);

            b.setStyle("-fx-background-color: transparent;");
            this.getChildren().add(b);

        }

        //this.getChildren().add(new Label(songs.get(0).getName()));
        System.out.println(songs.size());
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                button.setStyle("-fx-background-color: #e6e6e6;");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                button.setStyle("-fx-background-color: transparent;");
            }
        });
    }
}
