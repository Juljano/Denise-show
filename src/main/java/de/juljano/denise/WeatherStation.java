package de.juljano.denise;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class WeatherStation {
    private static final String[] weatherLinks = {"https://wetterstation-9a964-default-rtdb.europe-west1.firebasedatabase.app/DS18b20/Temperature.json","https://wetterstation-9a964-default-rtdb.europe-west1.firebasedatabase.app/MLX90614/Cloudtemperature.json",
            "https://wetterstation-9a964-default-rtdb.europe-west1.firebasedatabase.app/Windspeed.json","https://wetterstation-9a964-default-rtdb.europe-west1.firebasedatabase.app/DHT22/Humidity.json","https://wetterstation-9a964-default-rtdb.europe-west1.firebasedatabase.app/station/LastMeasurements.json"};
   private static String lastValue = "?orderBy=\"$key\"&limitToLast=1";


    public static void getWeatherData() {

        int pos = 0;

        HashMap<String, String> weatherData = new HashMap<>();

        for (String weatherLink : weatherLinks) {

            try {
                String command = weatherLink + lastValue;


                URL url = new URL(command);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                int responseCode = httpURLConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    JSONObject jsonObject = getJsonObject(httpURLConnection);

                    if (!jsonObject.isEmpty()) {

                        switch (pos){
                            case 0:
                                weatherData.put("Temperature", jsonObject.toJSONString());
                                break;
                            case 1:
                                weatherData.put("CloudTemp", jsonObject.toJSONString());
                                break;
                            case 2:
                                weatherData.put("Windspeed", jsonObject.toJSONString());
                                break;
                            case 3:
                                weatherData.put("Humidity", jsonObject.toJSONString());
                                break;
                            case 4:
                                weatherData.put("LastMeasurements", jsonObject.toJSONString());
                                break;
                            default:
                                System.out.println("error");
                        }

                        System.out.println(weatherData.entrySet());

                    }

                }
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
            pos++;
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

