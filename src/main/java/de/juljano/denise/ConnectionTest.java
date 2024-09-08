package de.juljano.denise;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionTest {

   static boolean isConnected() {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL("https://ucupk9inmdhmiuxk.myfritz.net:44218");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            int httpCode = httpURLConnection.getResponseCode();
            httpURLConnection.setConnectTimeout(5000);
            return httpCode == 200;

        } catch (IOException e) {

            return false;

        } finally {
            if (httpURLConnection != null) {

                httpURLConnection.disconnect();
            }
        }
    }

}
