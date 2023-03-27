package pl.com.great.nysa.it.webscrapper.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalyzeService {

    public List<BigDecimal> filterOutliers(List<BigDecimal> prices) {
        Collections.sort(prices);

        int size = prices.size();
        BigDecimal q1 = getQuartile(prices, 0.25);
        BigDecimal q3 = getQuartile(prices, 0.75);
        BigDecimal iqr = q3.subtract(q1);
        BigDecimal lowerBound = q1.subtract(iqr.multiply(new BigDecimal(1.5)));
        BigDecimal upperBound = q3.add(iqr.multiply(new BigDecimal(1.5)));

        List<BigDecimal> filteredPrices = new ArrayList<>();
        for (BigDecimal price : prices) {
            if (price.compareTo(lowerBound) >= 0 && price.compareTo(upperBound) <= 0) {
                filteredPrices.add(price);
            }
        }
        return filteredPrices;
    }

    public static BigDecimal getQuartile(List<BigDecimal> sortedPrices, double quartile) {
        int n = (int) (sortedPrices.size() * quartile + 0.5);
        return sortedPrices.get(n);
    }
}
