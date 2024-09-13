package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Label quoteLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public ImageView imageViewBackground;

    public ScreensaverController() {
    }


    void setTimeAndDate() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd. MMM");

        timeLabel.setText(timeFormatter.format(localTime));
        dateLabel.setText(dateFormatter.format(localDate));

        setWeatherAndMoonStatus();
    }

    public void setTemperature(){

        if (TemperatureSensor.getTemperature() != null){
            temperatureLabel.setText(TemperatureSensor.getTemperature()+"°C");
        }else{
            temperatureLabel.setText("No temp");
        }
    }

    void setWeatherAndMoonStatus() {
        Image weatherImage = new Image("sunny.png");
        Image moonImage = new Image("moonpartly.png");
        weatherStatus.setImage(weatherImage);
        imageViewBackground.setImage(new Image("night.png"));
        moonStatus.setImage(moonImage);
    }

    public void updateQuotes() {
        QuoteModel quoteModel = QuoteModel.getInstance();

        if (quoteModel != null){
            quoteLabel.setText(quoteModel.getQoute());
            authorLabel.setText(quoteModel.getAuthor());
        }

}
}
