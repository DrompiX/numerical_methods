package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.CheckBox;


public class Controller implements Initializable {

    @FXML private LineChart<Number, Number> MyChart;
    @FXML private CheckBox eulerCheckBox;
    private EulerMethod euler;
    private int N = 25;
    private double /*x[],*/ x0 = 1.7, y0, X = 9;//, h;

    // Variant 23 - y^2*e^x - 2y, y(1.7) = -0.9025147, x in [1.7, 9]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        euler = new EulerMethod(MyChart);
//        y0 = exactSolution(x0);


//        x = new double[N];
//        x[0] = x0;
//
//        h = (X - x0) / N;
//        double y[] = new double[N];
//        double y2[] = new double[N];

//        y[0] = y0;
//        for (int i = 1; i < N; i++) {
//            x[i] = x[i - 1] + h;
//        }

//        for (int i = 0; i < N; i++) {
////            // Euler
////            y[i + 1] = y[i] + h * (Math.pow(y[i], 2) * Math.exp(x[i]) + 2 * y[i]);
////            // Real
//            y2[i] = initialFunction(x[i]);
//        }
////        y2[N - 1] = initialFunction(x[N - 1]);
//        y = approxWithEuler(x, y0, N, h);
//        Series eqSeries = new Series();
//        eqSeries.setName("Euler");
//        Series init = new Series();
//        init.setName("Initial");
//
//        for (int i=0; i<x.length; i++) {
//            System.out.println(x[i] + " " + y[i]);
//            eqSeries.getData().add(new Data(x[i], y[i]));
//            init.getData().add(new Data(x[i], y2[i]));
//        }
//
//        MyChart.getData().addAll(eqSeries, init);

    }

    private double exactSolution(double x) {
        final double C = 64.4198735;
        return 3 * Math.pow(Math.E, 2 * x) / (C - Math.pow(Math.E, 3 * x));
    }

    @FXML
    private void approxWithEuler() {
        if (eulerCheckBox.isSelected())
            euler.display(x0, exactSolution(x0), X, N);
        else
            euler.hide();
    }
}