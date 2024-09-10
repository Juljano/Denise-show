package de.juljano.denise;

import javafx.application.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class QuoteParser {

    public static void parsingQoute() {


        try {

            if (ConnectionTest.isConnected()) {

                Document document = Jsoup.connect("https://zitate-aphorismen.de/zufallszitat.php").get();
                //   String getAuthorandQuote = document.select("#zufallszitat").first().text();

                String getQuote = document.select("div.tageszitat").text();
                String getAuthor = document.select("span.autor_in").text();
                //remove author from Quote
                getQuote = getQuote.substring(0, getQuote.indexOf(getAuthor)).trim();


                if (!getQuote.isEmpty() || !getAuthor.isEmpty()) {

                    System.out.println("Zitat " + getQuote);
                    System.out.println("Autor " + getAuthor);

                    // if Quote is too long, then reload parsingQoute again
                    if (!QuoteLength(getQuote)){
                        System.out.println("Quote too long");
                        parsingQoute();
                        return;
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
                Platform.runLater(() -> {
                    // screensaverController.updateQuotes(quoteModel.getQoute(), quoteModel.getAuthor());
                    screensaverController.quoteLabel.setText(quoteModel.getQoute());
                    screensaverController.authorLabel.setText(quoteModel.getQoute());
                });
            } else {
                System.out.println("Controller is empty");
            }
        } else {
            System.out.println("Quote or author is null");
        }
    }

    public static boolean QuoteLength(String lengthOfQuote) {

        return lengthOfQuote.length() <= 100;

    }
}