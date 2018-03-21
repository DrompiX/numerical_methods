package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

public abstract class ApproximationMethod extends SeriesBuilder {
    Series<Number, Number> errorSeries;
    private LineChart<Number, Number> errChart;

    ApproximationMethod(LineChart<Number, Number> funcChart,
                        LineChart<Number, Number> errChart) {
        super(funcChart);
        this.errChart = errChart;
    }

    @Override
    void display() {
        hide();
        makeSeriesWithNewConditions();
        chart.getData().add(methodSeries);
    }

    @Override
    void hide() {
        chart.setAnimated(false);
        chart.getData().remove(methodSeries);
        hideError();
        chart.setAnimated(true);
    }

    double f(double x, double y) {
        return (Math.pow(y, 2) * Math.exp(x) + 2 * y); //y^2 * e^x + 2y
    }

    void displayError(double[] exactY) {
        calculateError(exactY);
        errChart.getData().add(errorSeries);
    }

    private void hideError() {
        errChart.setAnimated(false);
        errChart.getData().remove(errorSeries);
        errChart.setAnimated(true);
    }

    protected void calculateError(double[] exactY) {
        errorSeries = new Series<>();

        for (int i = 0; i < N; i++)
            errorSeries.getData().add(new Data<>(x[i], Math.abs(exactY[i] - y[i])));
    }

}
