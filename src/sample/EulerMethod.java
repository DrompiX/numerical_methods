package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class EulerMethod extends ApproximationMethod {
//    private Series eulerSeries;

    EulerMethod(LineChart<Number, Number> chart) {
        super(chart);
//        eulerSeries = new Series();
    }

//    @Override
//    public void display(double x0, double y0, double X, int N) {
//        setFields(x0, y0, X, N);
//        make();
//        chart.getData().addAll(eulerSeries);
//    }

//    @Override
//    public void hide() {
////        chart.getData().removeAll(eulerSeries);
//    }

    @Override
    protected void makeSeries() {
//        double h = (X - x0) / N;
//        double x[] = new double[N];
//        x[0] = x0;
//        for (int i = 1; i < N; i++)
//            x[i] = x[i - 1] + h;
//        double y[] = new double[N];
//        y[0] = y0;
        initialize();

        for (int i = 0; i < N - 1; i++)
            y[i + 1] = y[i] + h * f(x[i], y[i]);//(Math.pow(y[i], 2) * Math.exp(x[i]) + 2 * y[i]);

//        eulerSeries = new Series();
//        eulerSeries.setName("Euler");
//        for (int i = 0; i < N; i++)
//            eulerSeries.getData().add(new Data(x[i], y[i]));
        methodSeries = new Series();
        methodSeries.setName("Euler");
        for (int i = 0; i < N; i++)
            methodSeries.getData().add(new Data(x[i], y[i]));
    }

}
