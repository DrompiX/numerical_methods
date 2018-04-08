package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class EulerMethod extends ApproximationMethod {

    EulerMethod() { super(); }

    EulerMethod(LineChart<Number, Number> funcChart,
                LineChart<Number, Number> errChart) {
        super(funcChart, errChart);
        methodSeries.setName("Euler");
        errorSeries.setName("Euler error");
    }

    @Override
    protected boolean makeSeries() {
        initialize();

        for (int i = 0; i < N - 1; i++) {
            y[i + 1] = y[i] + h * f(x[i], y[i]);
            if (y[i + 1] > failError) return false;
        }

        methodSeries.getData().clear();
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data<>(x[i], y[i]));
        return true;
    }

}
