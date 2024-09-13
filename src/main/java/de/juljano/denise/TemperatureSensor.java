package de.juljano.denise;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class TemperatureSensor {
   private static String temperature;

   public static String getTemperature() {
      return temperature;
   }

   public static void setTemperature(String temperature) {
      TemperatureSensor.temperature = temperature;
   }

   public void startPythonScript(){
      String output;
      String errorOutput;

      try {
          // starting python-script to read DHT22 sensor
         Process process = Runtime.getRuntime().exec("python3 readDHT22.py");
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         BufferedReader bufferedReaderError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

         // get temperature values and split it
         while ((output = bufferedReader.readLine()) != null) {

            StringBuilder stringBuilder = new StringBuilder(output);
             int parts = stringBuilder.indexOf("°C");

            if (parts != -1){
               temperature = stringBuilder.substring(0, parts).trim();
               System.out.println("DHT-Sensor: " + temperature);


               updateTemperatureLabel();

            }

         }
         // get error output
         while ((errorOutput = bufferedReaderError.readLine()) != null) {
            System.out.println(errorOutput);
         }


      } catch (IOException ex) {

         throw new RuntimeException(ex);
      }

   }


   public static void updateTemperatureLabel(){

      ScreensaverController screensaverController = MainApplication.getScreensaver();

      if (screensaverController != null) {
         Platform.runLater(() -> {
            TemperatureSensor.setTemperature(temperature);
            //screensaverController.setTemperature(temperature);
         });
      } else {
         System.out.println("Controller is empty - ");
      }

   }


}
