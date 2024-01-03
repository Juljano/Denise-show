package de.juljano.denise;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class QuoteParser {


    public void parsingQoute() throws IOException {

        Document document = Jsoup.connect("https://www.zitate-online.de/zufallszitat.txt.php").get();
        String getAuthorandQuote = document.select("#zufallszitat").first().text();

        if (!getAuthorandQuote.isEmpty()){
            String[] split = getAuthorandQuote.split(":", 2);

            String getAuthor = split[0];
            String getQuote = split[1];

            //set the Author and Quote in @QouteModel
            QuoteModel quoteModel = new QuoteModel();
            quoteModel.setQoute(getQuote);
            quoteModel.setAuthor(getAuthor);


        }



    }
    
    
    
    
}
