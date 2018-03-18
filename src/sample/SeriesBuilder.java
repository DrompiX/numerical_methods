package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

public abstract class SeriesBuilder {
    Series methodSeries;
    LineChart chart;
    double x[], y[];
    double x0, y0, X, h;
    int N;

    SeriesBuilder(LineChart<Number, Number> chart) {
        this.chart = chart;
        methodSeries = new Series();
    }

    protected void setFields(double x0, double y0, double X, int N) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;
    }

    void initialize() {
        h = (X - x0) / N;
        x = new double[N];
        x[0] = x0;
        for (int i = 1; i < N; i++)
            x[i] = x[i - 1] + h;
        y = new double[N];
        y[0] = y0;
    }

    abstract void display(double x0, double y0, double X, int N);

    abstract void hide();

    protected abstract void makeSeries();
}
