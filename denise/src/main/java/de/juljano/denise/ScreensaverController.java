package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScreensaverController {
    @FXML
    public Label temperatureLabel;
    @FXML
    public Label timeLabel;
    @FXML
    public Label dateLabel;
    @FXML
    public ImageView weatherStatus;
    @FXML

    public ImageView moonStatus;
    @FXML

    public Label qouteLabel;

    @FXML
    private Label authorLabel;


    public void setQuoteandAuthor() {

    }

    public void updateGui() {

        QuoteModel quoteModel = new QuoteModel();



        temperatureLabel.setText("16.45°");
        timeLabel.setText("22:48");
        dateLabel.setText("Freitag, 04. Februar");

        Image weatherImage = new Image("sunny.png");
        Image moonImage = new Image("moonpartly.png");
        weatherStatus.setImage(weatherImage);

        moonStatus.setImage(moonImage);

        qouteLabel.setText(quoteModel.getQoute());
        authorLabel.setText(quoteModel.getAuthor());
    }


}