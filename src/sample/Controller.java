package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;

//public class Controller {
//    @FXML LineChart<Number, Number> lineChart;
//
//    public void f(ActionEvent event) {
//        XYChart.Series<Number, Number> series = new XYChart.Series<>();
//    }
//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

public class Controller implements Initializable {

    @FXML
    private LineChart<Number, Number> MyChart;

    // Variant 23 - y^2*e^x - 2y, y(1) = 0.5, x in [1, 7]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double x0 = 1.7, y0 = initialFunction(x0), X = 10;
        System.out.println(y0);
        int N = 50;
        double x[] = new double[N];
        double y[] = new double[N];
        double y2[] = new double[N];

        double h = (X - x0) / N;

        x[0] = x0;
        y[0] = y0;
        for (int i = 1; i < N; i++) {
            x[i] = x[i - 1] + h;
        }

//        for (int i = 0; i < N; i++) {
//            y[i] = 3 * Math.pow(Math.E, 2 * x[i]) / (C - Math.pow(Math.E, 3 * x[i]));
////            y[i] = 10.2871 * Math.pow(Math.E, -x[i]) / (10.2871 - Math.pow(Math.E, x[i]));
//        }
        for (int i = 0; i < N - 1; i++) {
            y[i + 1] = y[i] + h * (Math.pow(y[i], 2) * Math.exp(x[i]) + 2 * y[i]);
            y2[i] = initialFunction(x[i]);
        }
        y2[N - 1] = initialFunction(x[N - 1]);

        Series eqSeries = new Series();
        eqSeries.setName("Euler");
        Series init = new Series();
        init.setName("Initial");

        for (int i=0; i<x.length; i++) {
            System.out.println(x[i] + " " + y[i]);
            eqSeries.getData().add(new Data(x[i], y[i]));
            init.getData().add(new Data(x[i], y2[i]));
        }

        MyChart.getData().addAll(eqSeries, init);

    }

    private double initialFunction(double x) {
        final double C = 64.4199;
        return 3 * Math.pow(Math.E, 2 * x) / (C - Math.pow(Math.E, 3 * x));
    }
}