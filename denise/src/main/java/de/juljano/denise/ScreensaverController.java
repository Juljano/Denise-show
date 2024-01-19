package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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


    void setTimeandDate() throws IOException {

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd. MMM");

        timeLabel.setText(timeFormatter.format(localTime));
        dateLabel.setText(dateFormatter.format(localDate));

        setWheaterandMoonStatus();
    }

    void setWheaterandMoonStatus() throws IOException {
        
        
        temperatureLabel.setText("12.34°");
        Image weatherImage = new Image("sunny.png");
        Image moonImage = new Image("moonpartly.png");
        weatherStatus.setImage(weatherImage);

        moonStatus.setImage(moonImage);

        setQoute();
    }

    void setQoute() throws IOException {

        QuoteParser quoteParser = new QuoteParser();
        quoteParser.parsingQoute();

        QuoteModel quoteModel = QuoteModel.getInstance();

        if (quoteModel.getQoute() != null && quoteModel.getAuthor() != null) {

            qouteLabel.setText(quoteModel.getQoute());
            authorLabel.setText(quoteModel.getAuthor());

        }




    }


}