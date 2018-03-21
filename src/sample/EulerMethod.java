package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class EulerMethod extends ApproximationMethod {

    EulerMethod(LineChart<Number, Number> funcChart,
                LineChart<Number, Number> errChart) {
        super(funcChart, errChart);
    }

    @Override
    protected void makeSeries() {
        initialize();

        for (int i = 0; i < N - 1; i++)
            y[i + 1] = y[i] + h * f(x[i], y[i]);//(Math.pow(y[i], 2) * Math.exp(x[i]) + 2 * y[i]);

        methodSeries = new Series<>();
        methodSeries.setName("Euler");
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data<>(x[i], y[i]));
    }

    @Override
    protected void calculateError(double[] exactY) {
        super.calculateError(exactY);
        errorSeries.setName("Euler error");
    }

}
