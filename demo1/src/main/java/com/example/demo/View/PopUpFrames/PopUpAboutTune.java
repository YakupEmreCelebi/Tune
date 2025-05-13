package com.example.demo.View.PopUpFrames;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopUpAboutTune extends PopUp{

    Label label;

    public PopUpAboutTune(){
        super(400,400);

        label = new Label("About Tune");

        VBox layout = (VBox) getRoot();
        layout.getChildren().add(label);
    }
}
