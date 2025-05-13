package com.example.demo.View.PopUpFrames;

import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.FriendNode;
import com.example.demo.View.SpecialNodes.NodeScroller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PopUpTuneWithFriend extends PopUp{

    VBox mainVBox;
    TuneUser currentUser;
    ArrayList<Node> friendTuneNodes;
    NodeScroller friendTunesNodeScroller;

    public PopUpTuneWithFriend(TuneUser tuneUser) {
        super(500, 260);

        this.currentUser = tuneUser;
        friendTuneNodes = new ArrayList<Node>();

        constructFriendsButtons();



        mainVBox = new VBox(30);
        mainVBox.setPadding(new Insets(3,0,30,0));
        mainVBox.setStyle("-fx-background-color: black;");

        mainVBox.getChildren().add(friendTunesNodeScroller);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: black; -fx-border-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setPrefHeight(260);
        scrollPane.setMinHeight(260);
        scrollPane.setMaxHeight(260);






        ((VBox) getRoot()).getChildren().add(scrollPane);
    }

    public void constructFriendsButtons(){

        for (TuneUser friend : currentUser.getFriends()) {
            FriendNode friendBox = new FriendNode(friend, true, false);
            friendTuneNodes.add(friendBox);
        }

        friendTunesNodeScroller = new NodeScroller("Choose friend to Tune", friendTuneNodes, 600);
    }
}
