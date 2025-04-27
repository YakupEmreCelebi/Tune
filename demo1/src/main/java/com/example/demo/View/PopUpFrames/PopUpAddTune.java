package com.example.demo.View.PopUpFrames;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopUpAddTune extends PopUp{

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private Label addYourTuneLabel;
    private Label tuneFeelingLabel;
    private TextField tuneNoteTextField;
    private Button addYourTuneButton;

    public PopUpAddTune(){
        super(400, 180);

        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();

        addYourTuneLabel = new Label("Add your tune");
        tuneFeelingLabel = new Label("Tune Feeling: ");

        tuneNoteTextField = new TextField();
        addYourTuneButton = new Button("Add your tune");

        hBox1.getChildren().add(addYourTuneLabel);
        hBox2.getChildren().addAll(tuneFeelingLabel, tuneNoteTextField);
        hBox3.getChildren().add(addYourTuneButton);

        VBox layout = (VBox) getRoot();

        layout.getChildren().addAll(hBox1, hBox2, hBox3);
    }
}
