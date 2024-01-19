package de.juljano.denise;

import java.io.File;

public class PhotoStorage {

    private final ConnectionTest connectionTest = new ConnectionTest();


    public void connectionToStorage(){

        if (connectionTest.isConnected()){

            File file = new File("/resources/julia.jpg");
            File[] imageFiles = file.listFiles();







        }

    }
}
