package com.psawesome.stockclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * package: com.psawesome.stockclient
 * author: PS
 * DATE: 2020-01-19 일요일 18:06
 */
@Slf4j
@RequiredArgsConstructor
public class WebClientStockClient {
    private final WebClient webClient;

    public Flux<StockPrice> pricesFor(String symbol) {
        return webClient.get().uri("http://localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .doOnError(IOException.class, c -> log.info(c.getMessage()));
    }
}
