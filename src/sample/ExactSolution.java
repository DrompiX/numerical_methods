package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class ExactSolution extends SeriesBuilder {
    private double C;

    ExactSolution(LineChart<Number, Number> chart) {
        super(chart);
    }

    double y(double x) {
        return 3 * Math.pow(Math.E, 2 * x) / (C - Math.pow(Math.E, 3 * x));
    }

    private void calculateConstant() {
        C = (3 * Math.exp(2 * x0) + y0 * Math.exp(3 * x0)) / y0;
    }

    void display() {
        hide();
        calculateConstant();
        makeSeriesWithNewConditions();
        chart.getData().add(methodSeries);
    }

    void hide() {
        chart.setAnimated(false);
        chart.getData().remove(methodSeries);
        chart.setAnimated(true);
    }

    @Override
    protected void makeSeries() {
        initialize();

        for (int i = 0; i < N; i++)
            y[i] = y(x[i]);

        methodSeries = new Series<>();
        methodSeries.setName("Exact");
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data<>(x[i], y[i]));
    }

    double[] getY() {
        makeSeriesWithNewConditions();
        return y;
    }

}
