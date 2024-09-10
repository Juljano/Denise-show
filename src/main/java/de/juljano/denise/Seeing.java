package de.juljano.denise;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Seeing {

    public void getSeeing() {
        try {
            Document document = Jsoup.connect("https://www.meteoblue.com/de/wetter/outdoorsports/seeing/obernkirchen_deutschland_2859532").get();

            Elements nightRows = document.select("tr.hour-row.night[data-day=0]");

            for (Element row : nightRows) {

                Elements tdElements = row.select("td");

                if (!tdElements.isEmpty()) {

                      String hour = row.attr("data-hour");

                    // Nehme das fünfte <td>-Element (Index 4, da Index 0-basierend ist)
                    if (tdElements.size() > 4) {
                        Element valueElement = tdElements.get(4);
                        String value = valueElement.text().trim();
                        String moonphase = document.select("div.illumination").first().text();

                        // Ausgabe der Uhrzeit und des Werts
                        System.out.println("Uhrzeit: " + hour + ":00");
                        System.out.println("Nachtwert: " + value);
                        System.out.println("Mondphase " +  moonphase);
                    } else {
                        System.out.println("Nicht genug <td>-Elemente in der Zeile gefunden.");
                    }
                } else {
                    System.out.println("Keine <td>-Elemente gefunden.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}