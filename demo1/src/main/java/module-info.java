module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires se.michaelthelin.spotify;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires com.google.gson;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Controller;
    opens com.example.demo.Controller to javafx.fxml;
    exports com.example.demo.View.SpecialNodes;
    opens com.example.demo.View.SpecialNodes to javafx.fxml;
    exports com.example.demo.View.Frames;
    opens com.example.demo.View.Frames to javafx.fxml;
    exports com.example.demo.Model;
    opens com.example.demo.Model to javafx.fxml;
}
