package com.psawesome.stockclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * package: com.psawesome.stockclient
 * author: PS
 * DATE: 2020-01-19 일요일 19:52
 */
@Slf4j
public class RSocketStockClient implements StockClient {

    private RSocketRequester rSocketRequester;

    public RSocketStockClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        return rSocketRequester.route("stockPrices")
                .data(symbol)
                .retrieveFlux(StockPrice.class)
                .doOnError(IOException.class, e -> log.info(e.getMessage()));
    }
}
