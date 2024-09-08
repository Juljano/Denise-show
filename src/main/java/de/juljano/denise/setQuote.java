package de.juljano.denise;


public class setQuote {


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
