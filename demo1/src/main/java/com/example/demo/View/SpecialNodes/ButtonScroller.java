package com.example.demo.View.SpecialNodes;

import com.example.demo.Model.TuneUser;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ButtonScroller extends ScrollPane {

    public ButtonScroller(ArrayList<Button> buttons) {

        this.setPrefWidth(500);
        this.setPrefHeight(130);
        this.setMaxHeight(130);
        this.setMaxWidth(500);

        HBox layout = new HBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(buttons);
        layout.setAlignment(Pos.CENTER);

        this.setContent(layout);

        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}
