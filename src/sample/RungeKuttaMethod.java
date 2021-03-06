package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class RungeKuttaMethod extends ApproximationMethod {

    RungeKuttaMethod() { super(); }

    RungeKuttaMethod(LineChart<Number, Number> funcChart,
                     LineChart<Number, Number> errChart) {
        super(funcChart, errChart);
        methodSeries.setName("R-Kutta");
        errorSeries.setName("R-Kutta error");
    }

    @Override
    protected boolean makeSeries() {
        initialize();

        for (int i = 0; i < N - 1; i++) {
            double k1 = f(x[i], y[i]);
            double k2 = f(x[i] + h/2, y[i] + h * k1/2);
            double k3 = f(x[i] + h/2, y[i] + h * k2/2);
            double k4 = f(x[i] + h, y[i] + h * k3);

            y[i + 1] = y[i] + h/6 * (k1 + 2 * k2 + 2 * k3 + k4);
            if (y[i + 1] > failError) return false;
        }

        methodSeries.getData().clear();
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data<>(x[i], y[i]));
        return true;
    }

}
