package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;

public abstract class SeriesBuilder {
    Series<Number, Number> methodSeries;
    LineChart<Number, Number> chart;
    double x[], y[];
    double x0, y0, X, h;
    int N;
    private boolean conditionsChanged = true;

    SeriesBuilder(LineChart<Number, Number> chart) {
        this.chart = chart;
        methodSeries = new Series<>();
    }

    void setFields(double x0, double y0, double X, int N) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;
        conditionsChanged = true;
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

    abstract void display();

    abstract void hide();

    protected abstract void makeSeries();

    void makeSeriesWithNewConditions() {
        if (conditionsChanged) {
            makeSeries();
            conditionsChanged = false;
        }
    }
}
