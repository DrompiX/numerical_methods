package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;

public class ExactSolution extends SeriesBuilder {
//    private LineChart chart;

    ExactSolution(LineChart<Number, Number> chart) {
        super(chart);
//        this.chart = chart;
//        methodSeries = new Series();
    }

    double y(double x) {
        final double C = 64.4198735;
        return 3 * Math.pow(Math.E, 2 * x) / (C - Math.pow(Math.E, 3 * x));
    }

    void display(double x0, double y0, double X, int N) {
        setFields(x0, y0, X, N);
        makeSeries();
        chart.getData().addAll(methodSeries);
    }

    void hide() {
        chart.getData().removeAll(methodSeries);
    }

    @Override
    protected void makeSeries() {
        initialize();
    }
}
