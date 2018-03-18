package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;


public class Controller implements Initializable {

    @FXML private LineChart<Number, Number> MyChart;
    @FXML private CheckBox exactCheckBox;
    @FXML private CheckBox eulerCheckBox;
    @FXML private CheckBox imprEulerCheckBox;
    @FXML private CheckBox rKuttaCheckBox;

    private ExactSolution exactSolution;
    private EulerMethod euler;
    private ImprovedEulerMethod improvedEuler;
    private RungeKuttaMethod rungeKutta;
    private int N = 25;
    private double /*x[],*/ x0 = 1.7, y0, X = 9;//, h;

    // Variant 23 - y^2*e^x - 2y, y(1.7) = -0.9025147, x in [1.7, 9]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        euler = new EulerMethod(MyChart);
        improvedEuler = new ImprovedEulerMethod(MyChart);
        rungeKutta = new RungeKuttaMethod(MyChart);
        exactSolution = new ExactSolution(MyChart);
//        if (euler.methodSeries == improvedEuler.methodSeries)
//            System.out.println("KEK");
    }

//    private double exactSolution(double x) {
//        final double C = 64.4198735;
//        return 3 * Math.pow(Math.E, 2 * x) / (C - Math.pow(Math.E, 3 * x));
//    }

    @FXML
    private void buildExact() {
        if (exactCheckBox.isSelected()) {

        } else {

        }
    }

    @FXML
    private void approxWithEuler() {
        if (eulerCheckBox.isSelected())
            euler.display(x0, exactSolution.y(x0), X, N);
        else
            euler.hide();
    }

    @FXML
    private void approxWithImprovedEuler() {
        if (imprEulerCheckBox.isSelected())
            improvedEuler.display(x0, exactSolution.y(x0), X, N);
        else
            improvedEuler.hide();
    }

    @FXML
    private void approxWithRungeKutta() {
        if (rKuttaCheckBox.isSelected())
            rungeKutta.display(x0, exactSolution.y(x0), X, N);
        else
            rungeKutta.hide();
    }
}