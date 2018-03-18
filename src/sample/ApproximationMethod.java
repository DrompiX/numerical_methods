package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;

public abstract class ApproximationMethod extends SeriesBuilder {
//    Series methodSeries;
//    private LineChart chart;
//    double x[], y[];
//    double x0, y0, X, h;
//    int N;

    ApproximationMethod(LineChart<Number, Number> chart) {
        super(chart);
//        this.chart = chart;
//        methodSeries = new Series();
    }

//    private void setFields(double x0, double y0, double X, int N) {
//        this.x0 = x0;
//        this.y0 = y0;
//        this.X = X;
//        this.N = N;
//    }

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

//    void initialize() {
//        h = (X - x0) / N;
//        x = new double[N];
//        x[0] = x0;
//        for (int i = 1; i < N; i++)
//            x[i] = x[i - 1] + h;
//        y = new double[N];
//        y[0] = y0;
//    }

    double f(double x, double y) {
        return (Math.pow(y, 2) * Math.exp(x) + 2 * y); //y^2 * e^x + 2y
    }
}
