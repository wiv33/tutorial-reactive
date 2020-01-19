package com.psawesome.stockui;

import com.psawesome.stockclient.StockPrice;
import com.psawesome.stockclient.WebClientStockClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * package: PACKAGE_NAME
 * author: PS
 * DATE: 2020-01-19 일요일 18:35
 */
@Component
public class ChartController {
    @FXML
    public LineChart<String, Double> chart;
    private WebClientStockClient webClientStockClient;

    public ChartController(WebClientStockClient webClientStockClient) {
        this.webClientStockClient = webClientStockClient;
    }

    @FXML
    public void initialize() {
        String symbol = "SYMBOL";
        final PriceSubscriber priceSubscriber1 = new PriceSubscriber(symbol);
        String symbol2 = "SYMBOL_2";
        final PriceSubscriber priceSubscriber2 = new PriceSubscriber(symbol2);
        String symbol3 = "SYMBOL_3";
        final PriceSubscriber priceSubscriber3 = new PriceSubscriber(symbol3);

        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        data.add(priceSubscriber1.getSeries());
        data.add(priceSubscriber2.getSeries());
        data.add(priceSubscriber3.getSeries());
        chart.setData(data);

        webClientStockClient.pricesFor(symbol).subscribe(priceSubscriber1);
        webClientStockClient.pricesFor(symbol2).subscribe(priceSubscriber2);
        webClientStockClient.pricesFor(symbol3).subscribe(priceSubscriber3);
    }

    private class PriceSubscriber implements Consumer<StockPrice> {
        private ObservableList<XYChart.Data<String, Double>> seriesData = FXCollections.observableArrayList();
        private final String symbol;
        private final XYChart.Series<String, Double> series;

        public PriceSubscriber(String symbol) {
            this.symbol = symbol;
            series = new XYChart.Series<>(seriesData);
        }


        @Override
        public void accept(StockPrice stockPrice) {
            Platform.runLater(() ->
                    seriesData.add(new XYChart.Data<>(String.valueOf(stockPrice.getTime().getSecond()), stockPrice.getPrice())
                    ));
        }

        public XYChart.Series<String, Double> getSeries() {
            return series;
        }
    }
}
