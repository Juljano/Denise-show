package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class NetworkProblemController {

    @FXML
    private Button restartButton;


    public void setRestartButton() {

        //The Device are rebooted to resolve the internet problem
        restartButton.setOnAction(actionEvent -> {

            try {
                Process process = Runtime.getRuntime().exec("sudo reboot");
                int output = process.waitFor();
                System.out.println(output);


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
