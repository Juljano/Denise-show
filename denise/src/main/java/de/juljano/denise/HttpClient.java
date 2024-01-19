package de.juljano.denise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpClient {

    public static void sendCommand(String url) {

        try {
            URL host = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) host.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputReader;
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputReader = bufferedReader.readLine()) != null) {

                stringBuilder.append(inputReader);

            }

            bufferedReader.close();

            //Convert ouput to JsonObject
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(stringBuilder.toString());

            getStateofPlug(jsonObject);


        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }


    private static void getStateofPlug(JSONObject jsonObject) {

        System.out.println(jsonObject);

        if (jsonObject.containsKey("ison")) {
            String status = jsonObject.get("ison").toString();
            setStateofPlug(status);
        }
    }

    private static void setStateofPlug(String stateofPlug) {

        if (!stateofPlug.contains("false") && !stateofPlug.isEmpty()) {
            System.out.println("Steckdose ist eingeschaltet");
        } else {
            System.out.println("Steckdose ist augeschaltet!");

        }
    }
}
