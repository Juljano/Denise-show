package de.juljano.denise;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Sun {

    static void getSunInfo() {
        try {

            URL url = new URL("https://api.sunrisesunset.io/json?lat=52.2633856&lng=9.1408978");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                JSONObject jsonObject = getJsonObject(httpURLConnection);

                if (!jsonObject.isEmpty()) {

                    JSONObject resultsObject = (JSONObject) jsonObject.get("results");
                    String sunrise = resultsObject.get("sunrise").toString();
                    String sunset = resultsObject.get("sunset").toString();

                    String replacePM = sunset.replace("PM", "");
                    String replaceAM = sunrise.replace("AM", "");

                   // System.out.println("Sunrise and Sunset: " + replaceAM + replacePM);
                }

            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject getJsonObject(HttpURLConnection httpURLConnection) throws IOException, ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputReader;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputReader = bufferedReader.readLine()) != null) {

            stringBuilder.append(inputReader);


        }

        bufferedReader.close();

        //Convert output to JsonObject
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(stringBuilder.toString());
    }

}
