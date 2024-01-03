package de.juljano.denise;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class NetworkProblemController {

    @FXML
    private Button restartButton;


    public void setRestartButton() {
        System.out.println("Test");



        //The Device are rebooted to resolve the internet problem
        restartButton.setOnAction(actionEvent -> {

            try {
                System.out.println("Test");
                Process process = Runtime.getRuntime().exec("sudo reboot");
                int output = process.waitFor();
                System.out.println(output);


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
