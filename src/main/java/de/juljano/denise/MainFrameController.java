package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainFrameController {

    @FXML
    private ImageView slideShowImageview;
    @FXML
    private Label timeLabel;
    @FXML
    private BorderPane borderpane;

    @FXML
    private ListView<String> listView;


    public void setSlideShowImageview(){

        Image image = new Image("julia.jpg");
        slideShowImageview.setImage(image);
        slideShowImageview.setFitHeight(slideShowImageview.getFitHeight());
        slideShowImageview.setFitWidth(slideShowImageview.getFitWidth());

    }


    public void listview() {
        listView = new ListView<>();
        listView.getItems().addAll("Hallo", "Bob");

        // Fügen Sie die ListView zur AnchorPane hinzu

        borderpane.setCenter(listView);
    }


}
