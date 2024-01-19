package de.juljano.denise;


import javafx.scene.input.GestureEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;

public class QuoteParser {


    public void parsingQoute() throws IOException {
        ConnectionTest connectionTest = new ConnectionTest();

        if (connectionTest.isConnected()) {

            Document document = Jsoup.connect("https://www.zitate-online.de/zufallszitat.txt.php").get();
            String getAuthorandQuote = document.select("#zufallszitat").first().text();

            if (!getAuthorandQuote.isEmpty()) {

                String[] split = getAuthorandQuote.split(":", 2);

                String getAuthor = split[0];
                String getQuote = split[1];

               int index = getQuote.indexOf("“");

                if (index != -1){
                   getQuote = getQuote.substring(0,index).trim();
                   System.out.println(getQuote);
                }

                //set the Author and Quote in @QuoteModel
                QuoteModel quoteModel = QuoteModel.getInstance();
                quoteModel.setQoute(getQuote);
                quoteModel.setAuthor(getAuthor);

            }
        }


    }


}
