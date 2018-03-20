package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class ImprovedEulerMethod extends ApproximationMethod {

    ImprovedEulerMethod(LineChart<Number, Number> chart) {
        super(chart);
    }

    @Override
    protected void makeSeries() {
        initialize();

        for (int i = 0; i < N - 1; i++)
            y[i + 1] = y[i] + h/2 * (f(x[i], y[i]) + f(x[i + 1], y[i] + h * f(x[i], y[i])));

        methodSeries = new Series();
        methodSeries.setName("Impr. Euler");
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data(x[i], y[i]));
    }
}
