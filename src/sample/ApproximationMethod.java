package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

public abstract class ApproximationMethod extends SeriesBuilder {
    Series<Number, Number> errorSeries;
    private LineChart<Number, Number> errChart;
    private double maxError;
    final double failError = 100.0;
    private boolean isFailed = false;

    ApproximationMethod() {
        super();
        errorSeries = new Series<>();
    }

    ApproximationMethod(LineChart<Number, Number> funcChart,
                        LineChart<Number, Number> errChart) {
        super(funcChart);
        this.errChart = errChart;
        errorSeries = new Series<>();
    }

    /**
     * Add series to charts
     */
    @Override
    void display() {
        hide();
        chart.getData().add(methodSeries);
        errChart.getData().add(errorSeries);
    }

    /**
     * Remove series from charts
     */
    @Override
    void hide() {
        chart.setAnimated(false);
        errChart.setAnimated(false);
        chart.getData().remove(methodSeries);
        errChart.getData().remove(errorSeries);
        errChart.setAnimated(true);
        chart.setAnimated(true);
    }

    /**
     * Given initial function
     * @return f(x, y)
     */
    double f(double x, double y) {
        return (Math.pow(y, 2) * Math.exp(x) + 2 * y);
    }

    /**
     * Computes error of approximation method
     * @param exactY - exact solution array
     */
    private void calculateError(double[] exactY) {
        maxError = 0;
        errorSeries.getData().clear();

        for (int i = 0; i < N; i++) {
            double error = Math.abs(exactY[i] - y[i]);
            errorSeries.getData().add(new Data<>(x[i], error));
            if (error > maxError) maxError = error;
        }
    }

    double getMaxError() {
        return maxError;
    }

    /**
     * Set new values and recompute series for approximation method
     */
    void setFields(double x0, double y0, double X, int N, double[] exactY) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;
        isFailed = !makeSeries();
        calculateError(exactY);
    }

    /**
     * Can method be displayed?
     * @return true if yes, false - otherwise
     */
    public boolean isFailed() {
        return isFailed;
    }
}
