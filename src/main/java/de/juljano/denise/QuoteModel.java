package de.juljano.denise;

public class QuoteModel {
    private String qoute;
    private String author;
    private static QuoteModel quoteModel;


    private QuoteModel() {

    }


    public void setAuthor(String author) {
        this.author = author;

    }

    public String getAuthor() {
        return author;
    }

    public void setQoute(String qoute) {
        this.qoute = qoute;
    }

    public String getQoute() {
        return qoute;
    }

    public static synchronized QuoteModel getInstance() {
        if (quoteModel == null) {
            quoteModel = new QuoteModel();
        }
        return quoteModel;

    }

}