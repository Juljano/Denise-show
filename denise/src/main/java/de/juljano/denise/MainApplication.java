package de.juljano.denise;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MainApplication extends Application {


    private boolean isConnected() {
        try {
            URL url = new URL("https://ucupk9inmdhmiuxk.myfritz.net:44218");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            return true;

        } catch (IOException e) {

            return false;

        }
    }


    @Override
    public void start(Stage stage) throws IOException {

        if (!isConnected()) {
            FXMLLoader errorFrame = new FXMLLoader(getClass().getResource("NetworkProblem.fxml"));
            Parent parent = errorFrame.load();
            Scene errorScene = new Scene(parent, 1024, 600);
            NetworkProblemController networkProblemController = errorFrame.getController();
            networkProblemController.setRestartButton();
            stage.setScene(errorScene);
            stage.show();

        }

      /*  //MainFrame
        FXMLLoader mainFrame = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
        Parent secondRoot = mainFrame.load();
        MainFrameController mainFrameController = mainFrame.getController();
        mainFrameController.setSlideShowImageview();
        Scene secondScene = new Scene(secondRoot, 1024, 600);
        Stage mainStage = new Stage();
        mainStage.setFullScreen(true);
        mainStage.setScene(secondScene);
        mainStage.show();*/


        startingScreensaver(stage);


    }

    public static void main(String[] args) {
        launch();

    }

    private void startingScreensaver(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("screensaver.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 1024, 600);
        ScreensaverController screensaverController = fxmlLoader.getController();
        screensaverController.updateGui();
        screensaverController.setQuoteandAuthor();
        stage.setFullScreen(true); // only on raspberry Pi
        stage.setScene(scene);
        stage.show();

    }


}