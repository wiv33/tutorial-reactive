package com.psawesome.stockclient;

import reactor.core.publisher.Flux;

/**
 * package: com.psawesome.stockclient
 * author: PS
 * DATE: 2020-01-19 일요일 19:54
 */
public interface StockClient {
    Flux<StockPrice> pricesFor(String symbol);
}
