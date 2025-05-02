package com.example.demo.View.Frames;

import com.example.demo.Model.TuneUser;
import com.example.demo.View.SpecialNodes.NavigateBar;
import com.example.demo.View.SpecialNodes.SongScroller;
import com.example.demo.View.SpecialNodes.SongVBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static javafx.stage.Screen.getPrimary;

public class ProfileFrame extends Scene {

    private NavigateBar navigateBar;
    private ArrayList<Node> scrollerNodes;
    private SongScroller scroller;
    private Button editProfileButton;
    private VBox containerVBox;
    private VBox profileVBox;
    private VBox nodeScrollersVBox;
    private ImageView profileImageView;
    private TuneUser currentUser;
    private Image profileImage;
    private ArrayList<SongVBox> songVBoxList;

    public ProfileFrame(TuneUser tuneUser) {
        super(new HBox(40), getScreenWidth() , getScreenHeight());
        this.getStylesheets().add(getClass().getResource("navBar.css").toExternalForm());

        currentUser = tuneUser;
        songVBoxList = new ArrayList<>();
        constructFavSongs();


        navigateBar = new NavigateBar();
        createImage();
        scrollerNodes = new ArrayList<>();

        scroller = new SongScroller(songVBoxList);

        //Button
        editProfileButton = new Button("Edit Profile");
        editProfileButton.setStyle("-fx-font-size: 18; -fx-background-color: black; -fx-text-fill: white");
        editProfileButton.setTranslateX(20);

        //VBoxes
        profileVBox = new VBox();
        profileVBox.setSpacing(0);
        profileVBox.setPadding(new Insets(100,0,0,30));
        profileVBox.getChildren().addAll(profileImageView, editProfileButton);


        // Layout
        HBox layout = (HBox) getRoot();
        layout.getChildren().addAll(navigateBar, profileVBox, scroller);

        // Alignments
        StackPane.setAlignment(navigateBar, Pos.TOP_LEFT);
    }

    private void createImage(){
        profileImage = new Image(getClass().getResourceAsStream("/com/example/demo/megadeth_ico.jpg"));
        profileImageView = new ImageView(profileImage);
        profileImageView.setPreserveRatio(true);
        profileImageView.setFitWidth(180);
        profileImageView.setClip(new Circle(80, 80, 80));
    }

    private void constructFavSongs(){
        if(currentUser.getFavouriteSongs() != null)
        {
            for(int i=0; i<currentUser.getFavouriteSongs().size(); i++){
                SongVBox songVBox = new SongVBox(currentUser.getFavouriteSongs().get(i));
                songVBoxList.add(songVBox);
            }
        }


    }

    private static double getScreenWidth() {
        return getPrimary().getVisualBounds().getWidth();
    }

    private static double getScreenHeight() {
        return getPrimary().getVisualBounds().getHeight();
    }

    public NavigateBar getNavigateBar() {
        return navigateBar;
    }

    public SongScroller getScroller() {
        return scroller;
    }

    public Button getEditProfileButton() {
        return editProfileButton;
    }

    public ArrayList<SongVBox> getSongVBoxList() {
        return songVBoxList;
    }
}
