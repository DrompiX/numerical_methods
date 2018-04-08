package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class ImprovedEulerMethod extends ApproximationMethod {

    ImprovedEulerMethod() { super(); }

    ImprovedEulerMethod(LineChart<Number, Number> funcChart,
                        LineChart<Number, Number> errChart) {
        super(funcChart, errChart);
        methodSeries.setName("Im.Euler");
        errorSeries.setName("Im.Euler error");
    }

    @Override
    protected void makeSeries() {
        initialize();

        for (int i = 0; i < N - 1; i++) {
            double m1 = f(x[i], y[i]);
            double m2 = f(x[i + 1], y[i] + h * m1);
            y[i + 1] = y[i] + h / 2 * (m1 + m2);
        }

        methodSeries.getData().clear();
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data<>(x[i], y[i]));
    }

}
