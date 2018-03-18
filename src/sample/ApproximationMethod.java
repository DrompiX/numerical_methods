package sample;

import javafx.scene.chart.LineChart;

public abstract class ApproximationMethod extends SeriesBuilder {

    ApproximationMethod(LineChart<Number, Number> chart) {
        super(chart);
    }

    @Override
    void display(double x0, double y0, double X, int N) {
        setFields(x0, y0, X, N);
        makeSeries();
        chart.getData().addAll(methodSeries);
    }

    @Override
    void hide() {
        chart.getData().removeAll(methodSeries);
    }

    double f(double x, double y) {
        return (Math.pow(y, 2) * Math.exp(x) + 2 * y); //y^2 * e^x + 2y
    }
}
