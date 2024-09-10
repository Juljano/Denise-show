package de.juljano.denise;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FuelPriceChecker {
    private static final String websiteOfStation = "https://www.clever-tanken.de/tankstelle_liste?lat=52.2611036829205&lon=9.04889591933145&ort=31675+B%C3%BCckeburg&spritsorte=7&r=6";
    private static final String[] IdsOfStation = {"30890", "7171", "45912", "47478", "45865", "623"};


    public static void getPetrolPrice() {
        try {
            Document document = Jsoup.connect(websiteOfStation).get();
            Elements gasStationLinks= document.select("a[href]");

            Map<String, Double> petrolPrices = new HashMap<>();

            for (Element link : gasStationLinks) {
                // Extrahiere die ID aus dem href-Attribut
                String href = link.attr("href");
                String id = href.replaceAll("/tankstelle_details/", "").trim();

                for (String stationId : IdsOfStation) {
                    if (id.equals(stationId)) {
                        // Wenn die ID übereinstimmt, extrahiere die Tankstelleninformationen
                        Element petrolPriceElement = link.selectFirst("div.price-text.price.text-color-ct-blue");
                        if (petrolPriceElement == null) {
                            continue; // Überspringe, wenn kein Preiselement gefunden wurde
                        }

                        String priceText = petrolPriceElement.text();
                        double price;
                        try {
                            price = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
                        } catch (NumberFormatException e) {
                            System.err.println("Fehler beim Parsen des Preises: " + priceText);
                            continue;
                        }

                        Element stationNameElement = link.selectFirst("span.fuel-station-location-name");
                        if (stationNameElement == null) {
                            continue; // Überspringe, wenn kein Name gefunden wurde
                        }
                        String name = stationNameElement.text();

                        petrolPrices.put(name, price);

                    }
                }
            }

            // Bestimme die günstigste Tankstelle nach dem Schleifendurchlauf
            String cheapestStation = getCheapestPetrol(petrolPrices);
            System.out.printf("Die günstigste Tankstelle ist '%s' mit einem Preis von %.2f%n€",
                    cheapestStation, petrolPrices.get(cheapestStation));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String getCheapestPetrol(Map<String, Double> petrolPrices) {
        return petrolPrices.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Keine Tankstellen gefunden");
    }

}