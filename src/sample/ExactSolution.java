package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;

public class ExactSolution extends SeriesBuilder {
    private double C;

    ExactSolution() { super(); }

    ExactSolution(LineChart<Number, Number> chart) {
        super(chart);
        methodSeries.setName("Exact");
    }

    private double y(double x) {
        return 3 * Math.exp(2 * x) / (C - Math.exp(3 * x));
    }

    private void calculateConstant() {
        C = (3 * Math.exp(2 * x0) + y0 * Math.exp(3 * x0)) / y0;
    }

    void display() {
        hide();
        chart.getData().add(methodSeries);
    }

    void hide() {
        chart.setAnimated(false);
        chart.getData().remove(methodSeries);
        chart.setAnimated(true);
    }

    @Override
    protected boolean makeSeries() {
        initialize();

        for (int i = 0; i < N; i++)
            y[i] = y(x[i]);

        methodSeries.getData().clear();
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data<>(x[i], y[i]));
        return true;
    }

    double[] getY() {
        return y;
    }

    void setFields(double x0, double y0, double X, int N) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;
        calculateConstant();
        makeSeries();
    }

}
