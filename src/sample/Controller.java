package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML LineChart<Number, Number> lineChart;

    public void f(ActionEvent event) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
    }
}
