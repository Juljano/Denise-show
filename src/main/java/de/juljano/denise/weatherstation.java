package de.juljano.denise;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weatherstation {


    public void getWeatherData() {
        try {

            String command = "https://wetterstation-9a964-default-rtdb.europe-west1.firebasedatabase.app/DS18b20.json";

            URL url = new URL(command);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                JSONObject jsonObject = getJsonObject(httpURLConnection);

                if (!jsonObject.isEmpty()) {

                    JSONObject resultsObject = (JSONObject) jsonObject.get("Temperature");

                    //System.out.println(resultsObject);
                }

            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

        private static JSONObject getJsonObject (HttpURLConnection httpURLConnection) throws IOException, ParseException
        {
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

