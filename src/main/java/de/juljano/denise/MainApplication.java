package de.juljano.denise;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainApplication extends Application {

    private AnimationTimer animationTimer;
    private final long timerInterval = 5 * 1_000_000_000L; // 5 Second
    private final long updateInterval = 10L * 60 * 1_000_000_000L; //10 Minutes
    private long lastTimerCall = 0;
    private boolean firstStart = true;

    private static ScreensaverController screensaverController;

    @Override
    public void start(Stage stage) {

        lastTimerCall = System.nanoTime();

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastTimerCall >= timerInterval) {
                    lastTimerCall = now;

                    if (!ConnectionTest.isConnected()) {
                        try {
                            FXMLLoader errorFrameLoader = new FXMLLoader(getClass().getResource("NetworkProblem.fxml"));
                            Parent errorRoot = errorFrameLoader.load();
                            NetworkProblemController networkProblemController = errorFrameLoader.getController();
                            networkProblemController.setRestartButton();

                            stage.getScene().setRoot(errorRoot);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            startingScreensaver(stage);

                            if (firstStart){
                                updateData();
                                firstStart = false;
                            }

                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            updateData();
                                        }
                                    });
                                }

                            }, updateInterval);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        animationTimer.start();


    }

    @Override
    public void stop() {
        animationTimer.stop();
    }

    private void startingHomeScreen(Stage stage) throws IOException {
        FXMLLoader mainFrameLoader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
        Parent secondRoot = mainFrameLoader.load();
        MainFrameController mainFrameController = mainFrameLoader.getController();
        mainFrameController.setSlideShowImageview();
        mainFrameController.listview();

        Scene homeScreen = stage.getScene();
        if (homeScreen == null) {
            homeScreen = new Scene(secondRoot, 1024, 600);
            stage.setScene(homeScreen);
        } else {
            homeScreen.setRoot(secondRoot);
        }

        stage.show();
    }

    private void startingScreensaver(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("screensaver.fxml"));
        Parent parent = fxmlLoader.load();
        screensaverController = fxmlLoader.getController(); // Aktualisiere die statische Controller-Referenz
        screensaverController.setTimeAndDate();
        Scene screenSaver = stage.getScene();

        if (screenSaver == null) {
            screenSaver = new Scene(parent, 1024, 600);
            stage.setScene(screenSaver);
        } else {
            screenSaver.setRoot(parent);
        }
        stage.show();
    }

    public static ScreensaverController getScreensaver() {
        return screensaverController;
    }

    private void updateData() {
        QuoteParser.parsingQoute();
        Sun.getSunInfo();
        FuelPriceChecker.getPetrolPrice();

    }

    public static void main(String[] args) {
        launch(args);


    }
}