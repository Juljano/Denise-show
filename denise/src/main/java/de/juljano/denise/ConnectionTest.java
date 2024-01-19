package de.juljano.denise;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionTest {

    boolean isConnected() {
        try {
            URL url = new URL("https://ucupk9inmdhmiuxk.myfritz.net:44218");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            urlConnection.setConnectTimeout(5000);

            return true;

        } catch (IOException e) {

            return false;

        }
    }

}
