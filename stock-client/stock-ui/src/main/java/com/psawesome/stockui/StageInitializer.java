package com.psawesome.stockui;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * package: com.psawesome.stockui
 * author: PS
 * DATE: 2020-01-19 일요일 18:25
 */
@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {
    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) {
        Stage stage = event.getStage();
    }
}
