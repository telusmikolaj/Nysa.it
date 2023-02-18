package pl.com.great.nysa.it.api;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Component;
import pl.com.great.nysa.it.domain.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class WebScrapper {

    private static final String baseUrl = "https://www.olx.pl/d/nysa/?page=2";
    private static final String DESCRIPTION_XPATH = "//*[@class='css-bgzo2k er34gjf0']";
    private static final String PRICE_XPATH = "//*[@class='css-ddweki er34gjf0']";
    private static final String TITLE_XPATH = "//*[@class='css-1soizd2 er34gjf0']";
    private static final String CATEGORY_XPATH = "//*[@class='css-tyi2d1']";

    private static final String OLX_URL = "https://www.olx.pl";

    private static final String HREF_XPATH = "//a";
    private static final WebClient client = new WebClient();

    public void getProductsFromPage() throws IOException {
        List<String> auctionUrlList = this.getAuctionUrlList();
        List<Product> productList = auctionUrlList.stream().map(x -> {
            try {
                return this.extractProduct(x);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        System.out.println(productList);
    }

    public Product extractProduct(String url) throws IOException {


        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage(url);
        HtmlElement priceByXPath = page.getFirstByXPath(PRICE_XPATH) != null ? page.getFirstByXPath(PRICE_XPATH) : (HtmlElement) page.createElement("<h3>");
        HtmlElement descriptionByXPath = page.getFirstByXPath(DESCRIPTION_XPATH) != null ? page.getFirstByXPath(DESCRIPTION_XPATH) : (HtmlElement) page.createElement("<h3>");
        HtmlElement titleByXPath = page.getFirstByXPath(TITLE_XPATH) != null ? page.getFirstByXPath(TITLE_XPATH) : (HtmlElement) page.createElement("<h3>");
        HtmlElement categoryByXPath = page.getFirstByXPath(CATEGORY_XPATH) != null ? page.getFirstByXPath(CATEGORY_XPATH)  : (HtmlElement) page.createElement("<h3>");
        String priceWithCurrency = !priceByXPath.getTextContent().isEmpty() ? priceByXPath.getTextContent() : "0";
        String priceString = priceWithCurrency.replace(" zł", "");
        priceString = priceString.replace(",",".");
        priceString = priceString.replaceAll("\\s", "");
        BigDecimal price = new BigDecimal(priceString);

        return Product.builder().title(titleByXPath.getTextContent() != null ? titleByXPath.getTextContent() : " ")
                .description(descriptionByXPath.getTextContent() != null ? descriptionByXPath.getTextContent() : " ")
                .category(categoryByXPath.getTextContent() != null ? categoryByXPath.getTextContent() : " ")
                .price(price).build();
    }

    public List<String> getAuctionUrlList() throws IOException {
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage(baseUrl);
        List<HtmlAnchor> byXPath = page.getByXPath(HREF_XPATH);

        return byXPath.stream()
                .map(HtmlAnchor::getHrefAttribute)
                .filter(x -> x.contains("oferta"))
                .map(x -> {
                    if (!x.contains("www.")) {
                        return OLX_URL + x;
                    } else {
                        return x;
                    }
                })
                .collect(Collectors.toList());
    }


}
