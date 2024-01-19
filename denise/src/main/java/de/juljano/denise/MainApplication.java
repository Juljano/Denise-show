package de.juljano.denise;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {


    //check the connection to the internet
    private final ConnectionTest connectionTest = new ConnectionTest();


    @Override
    public void start(Stage stage) throws IOException {

        if (!connectionTest.isConnected()) {
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
        mainStage.show();
*/


        HttpClient.sendCommand("http://192.168.178.43/relay/0?turn=on");


        startingScreensaver(stage);


    }


    private void startingScreensaver(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("screensaver.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 1024, 600);
        ScreensaverController screensaverController = fxmlLoader.getController();
        screensaverController.setTimeandDate();
        //stage.setFullScreen(true); // only on raspberry Pi
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();





    }


}