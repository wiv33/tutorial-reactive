package com.psawesome.stockclient;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

/**
 * package: com.psawesome.stockclient
 * author: PS
 * DATE: 2020-01-19 일요일 18:06
 */
class WebClientStockClientIntegrationTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);
        Flux<StockPrice> prices = webClientStockClient.pricesFor("SYMBOL");

        assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        assertEquals(5, fivePrices.count().block());
        assertEquals("SYMBOL", fivePrices.blockFirst().getSymbol());
    }
}