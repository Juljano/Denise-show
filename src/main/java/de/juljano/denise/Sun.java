package de.juljano.denise;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.desktop.SystemSleepEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


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

                    Convert12hTo24h(sunrise, sunset);
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

    private static void Convert12hTo24h(String sunrise, String sunset) {


        // Definiere die Formatierung des 12-Stunden-Formats
        DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("h:mm:ss a");

        DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            LocalTime TimeSunrise = LocalTime.parse(sunrise, formatter12Hour);
            LocalTime TimeSunset = LocalTime.parse(sunset, formatter12Hour);

            String sunSet24h = TimeSunset.format(formatter24Hour);
            String sunrise24h = TimeSunrise.format(formatter24Hour);

            System.out.println("Sunrise " + sunrise24h);
            System.out.println("Sunset " + sunSet24h);

        } catch (DateTimeParseException e) {

            System.err.println("Ungültiges Zeitformat: " + e.getMessage());
        }

    }

}
