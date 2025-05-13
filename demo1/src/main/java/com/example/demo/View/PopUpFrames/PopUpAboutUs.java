package com.example.demo.View.PopUpFrames;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopUpAboutUs extends PopUp{

    Label label;
    Label title;

    public PopUpAboutUs(){
        super(1140,350);

        title = new Label("About Us – Pirates of Caribbean");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

        label = new Label(
                "We are a team of first-year Computer Engineering students at Bilkent University, and we’ve developed Tune, a music application as part of our CS-102 course project. Our team name is Pirates of Caribbean.\n" +
                "\n" +
                "Team Members & Roles:\n" +
                "\n" +
                "Kaan Yıldırım (Captain) – Database Lead: Responsible for designing and managing the structure and operations of our music database.\n" +
                "\n" +
                "Yakup Emre Çelebi – UI Lead: In charge of crafting an intuitive and user-friendly interface for the application.\n" +
                "\n" +
                "Burak Yılmaz Zor – API Lead, UI Contributor: Developed the APIs for the app and contributed to the UI design.\n" +
                "\n" +
                "Enes Akbulut – Data Control: In charge of sourcing songs and adding them to the database.\n" +
                "\n" +
                "Yusuf Mert Balcı – Data Control: Also responsible for sourcing and adding songs to the database.\n" +
                "\n" +
                "Our passion for music and technology united us to create something meaningful and fun. Tune is more than just a project — it represents our teamwork, creativity, and what we can achieve together.");

        VBox layout = (VBox) getRoot();
        layout.setSpacing(15);
        layout.getChildren().addAll(title, label);
        layout.setPadding(new Insets(10,10,10,10));
    }
}
