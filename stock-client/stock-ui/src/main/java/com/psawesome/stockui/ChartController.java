package com.psawesome.stockui;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import org.springframework.stereotype.Component;

/**
 * package: PACKAGE_NAME
 * author: PS
 * DATE: 2020-01-19 일요일 18:35
 */
@Component
public class ChartController {
    @FXML
    public LineChart<String, Double> chart;
}
