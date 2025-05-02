package com.example.demo.View.SpecialNodes;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class SongScroller extends ScrollPane {

    public SongScroller(ArrayList<SongVBox> nodes) {

        this.setPrefWidth(500);
        this.setPrefHeight(160);
        this.setMaxHeight(160);
        this.setMaxWidth(500);

        HBox layout = new HBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(nodes);


        this.setContent(layout);

        layout.setAlignment(Pos.CENTER);

        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


    }
}
