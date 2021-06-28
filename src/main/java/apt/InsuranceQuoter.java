package apt;

import java.util.HashMap;
import java.util.Map;

public class InsuranceQuoter {
    public static double getQuote(int marketPrice, String typeofProperty, int numberOfBedrooms, String yearOfConstruction, String constructionMaterial) {
        // basic quote is .2% of market value
        double basicQuote = marketPrice * .002;

        basicQuote *= getQuoteMultiplierForDwellingMap().get(typeofProperty);
        basicQuote = addOnePercentForEachBedroom(numberOfBedrooms, basicQuote);

        basicQuote = addLoadingForYearOfConstruction(yearOfConstruction, basicQuote);
        // a bit more for a timber house
        if (constructionMaterial.equals("timber")) {
            basicQuote += basicQuote * 0.1;
        }
        return basicQuote;
    }

    /**
     * Different multipliers based on the year of construction
     * @param yearOfConstruction
     * @param basicQuote
     * @return
     */
    private static double addLoadingForYearOfConstruction(String yearOfConstruction, double basicQuote) {
        if (Integer.parseInt(yearOfConstruction) <= 1900) {
            basicQuote += basicQuote * .04;
        } else if (Integer.parseInt(yearOfConstruction) <= 1921) {
            basicQuote += basicQuote * .03;
        } else if (Integer.parseInt(yearOfConstruction) <= 1951) {
            basicQuote += basicQuote * .02;
        } else if (Integer.parseInt(yearOfConstruction) <= 1971) {
            basicQuote += basicQuote * .01;
        }
        return basicQuote;
    }

    /**
     * Add on 1% for each bedroom
     * @param numberOfBedrooms
     * @param basicQuote
     * @return
     */
    private static double addOnePercentForEachBedroom(int numberOfBedrooms, double basicQuote) {
        return basicQuote + (numberOfBedrooms * (basicQuote / 100));
    }

    /**
     * Returns a map of multiplier for the type of the property.
     * @return The quote multiplier map.
     */
    private static Map<String, Double> getQuoteMultiplierForDwellingMap() {
        Map<String, Double> map = new HashMap<>();
        map.put("apartment", .95);
        map.put("semi", 1.05);
        map.put("terraced", 1.0);
        map.put("detached", 1.1);
        return map;
    }
}

