package pl.com.great.nysa.it.webscrapper.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnaliticsController {


    private final WebScrapper webScrapper;
    @GetMapping("/analitics/{productName}")
    public String analizeAvgPrice(@PathVariable String productName) throws IOException {
        List<String> auctionsUrlList = webScrapper.getAuctionsUrlList(productName);
        return "250";

    }


}
