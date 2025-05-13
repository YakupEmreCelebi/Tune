package com.example.demo.View.PopUpFrames;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopUpAboutUs extends PopUp{

    Label label;

    public PopUpAboutUs(){
        super(400,400);

        label = new Label("About Us");

        VBox layout = (VBox) getRoot();
        layout.getChildren().add(label);
    }
}
