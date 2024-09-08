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
    public Label qouteLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public ImageView imageViewBackground;

    public ScreensaverController() {
    }

    @FXML
    private void initialize() {
        setTimeAndDate();
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

    void setWeatherAndMoonStatus() {
        temperatureLabel.setText("12.34°");
        Image weatherImage = new Image("sunny.png");
        Image moonImage = new Image("moonpartly.png");
        weatherStatus.setImage(weatherImage);
        imageViewBackground.setImage(new Image("night.png"));
        moonStatus.setImage(moonImage);
    }

    public void updateQuotes(String quote, String author) {
        qouteLabel.setText(quote);
        authorLabel.setText(author);

    }
}
