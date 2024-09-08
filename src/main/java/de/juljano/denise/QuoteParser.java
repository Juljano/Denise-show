package de.juljano.denise;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static de.juljano.denise.setQuote.updateQuote;

public class QuoteParser {

    public static void parsingQoute() {


        try {

            if (ConnectionTest.isConnected()) {

                Document document = Jsoup.connect("https://www.zitate-online.de/zufallszitat.txt.php").get();
                String getAuthorandQuote = document.select("#zufallszitat").first().text();

                if (!getAuthorandQuote.isEmpty()) {

                    String[] split = getAuthorandQuote.split(":", 2);

                    String getAuthor = split[0];
                    String getQuote = split[1];

                    int index = getQuote.indexOf("“");

                    if (index != -1) {
                        getQuote = getQuote.substring(0, index).trim();
                        System.out.println(getQuote);
                    }

                    //set the Author and Quote in @QuoteModel
                    QuoteModel quoteModel = QuoteModel.getInstance();
                    quoteModel.setQoute(getQuote);
                    quoteModel.setAuthor(getAuthor);

                    updateQuote();

                }
            }


        } catch (IOException e) {

            System.out.println(e);
        }


        }


        public static void updateQuote() {


            QuoteModel quoteModel = QuoteModel.getInstance();

            if (quoteModel.getQoute() != null && quoteModel.getAuthor() != null) {

                ScreensaverController screensaverController = MainApplication.getScreensaver();

                if (screensaverController != null) {

                    screensaverController.updateQuotes(quoteModel.getQoute(), quoteModel.getAuthor());

                } else {

                    System.out.println("Controller is empty");
                }

            }


        }

    }


