package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class Controller implements Initializable {

    @FXML private LineChart<Number, Number> MyChart;
    @FXML private CheckBox exactCheckBox;
    @FXML private CheckBox eulerCheckBox;
    @FXML private CheckBox imprEulerCheckBox;
    @FXML private CheckBox rKuttaCheckBox;

    @FXML private TextField x0field;
    @FXML private TextField Xfield;
    @FXML private Button updateChart;

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
    }

    @FXML
    private void buildExact() {
        if (exactCheckBox.isSelected()) {
            exactSolution.hide();
            exactSolution.display(x0, exactSolution.y(x0), X, N);
        } else {
            exactSolution.hide();
        }
    }

    @FXML
    private void approxWithEuler() {
        if (eulerCheckBox.isSelected()) {
            euler.hide(); // TODO: test it
            euler.display(x0, exactSolution.y(x0), X, N);
        } else
            euler.hide();
    }

    @FXML
    private void approxWithImprovedEuler() {
        if (imprEulerCheckBox.isSelected()) {
            improvedEuler.hide();
            improvedEuler.display(x0, exactSolution.y(x0), X, N);
        } else
            improvedEuler.hide();
    }

    @FXML
    private void approxWithRungeKutta() {
        if (rKuttaCheckBox.isSelected()) {
            rungeKutta.hide();
            rungeKutta.display(x0, exactSolution.y(x0), X, N);
        } else
            rungeKutta.hide();
    }

    @FXML
    private void update() {
        x0 = Double.valueOf(x0field.getText());
        X = Double.valueOf(Xfield.getText());
        buildExact();
        approxWithEuler();
        approxWithImprovedEuler();
        approxWithRungeKutta();
    }
}