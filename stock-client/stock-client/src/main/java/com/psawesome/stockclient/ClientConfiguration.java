package com.psawesome.stockclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * package: com.psawesome.stockclient
 * author: PS
 * DATE: 2020-01-19 일요일 18:45
 */
@Configuration
public class ClientConfiguration {

    @Bean
    public WebClientStockClient webClientStockClient(WebClient webClient) {
        return new WebClientStockClient(webClient);
    }

    @Bean
    @ConditionalOnBean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
