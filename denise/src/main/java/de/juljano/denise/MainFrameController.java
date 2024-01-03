package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainFrameController {

    @FXML
    private ImageView slideShowImageview;
    @FXML
    private Label timeLabel;


    public void setSlideShowImageview(){

        Image image = new Image("julia.jpg");
        slideShowImageview.setImage(image);
        slideShowImageview.setFitHeight(slideShowImageview.getFitHeight());
        slideShowImageview.setFitWidth(slideShowImageview.getFitWidth());

    }


}
