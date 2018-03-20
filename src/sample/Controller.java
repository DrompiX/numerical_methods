package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javax.activity.InvalidActivityException;


public class Controller implements Initializable {

    @FXML private LineChart<Number, Number> functionChart;
    @FXML private LineChart<Number, Number> errorChart;
    @FXML private CheckBox exactCheckBox;
    @FXML private CheckBox eulerCheckBox;
    @FXML private CheckBox imprEulerCheckBox;
    @FXML private CheckBox rKuttaCheckBox;

    @FXML private TextField NField;
    @FXML private TextField x0field;
    @FXML private TextField y0field;
    @FXML private TextField Xfield;
//    @FXML private Button updateChart;

//    @FXML private TextField exactN;
//    @FXML private TextField eulerN;
//    @FXML private TextField imprEulerN;
//    @FXML private TextField rKuttaN;

    private ExactSolution exactSolution;
    private EulerMethod euler;
    private ImprovedEulerMethod improvedEuler;
    private RungeKuttaMethod rungeKutta;
    private int N = 25; // N such that h <= 0.6
    private double x0 = 1.7, X = 9;

    // Variant 23 - y^2*e^x - 2y, y(1.7) = -0.9025147, x in [1.7, 9]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        euler = new EulerMethod(functionChart);
        improvedEuler = new ImprovedEulerMethod(functionChart);
        rungeKutta = new RungeKuttaMethod(functionChart);
        exactSolution = new ExactSolution(functionChart);
        NField.setText("25");
        x0field.setText("1.7");
        Xfield.setText("9");
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
//        Series errorSeries;
        if (eulerCheckBox.isSelected()) {
            euler.hide(); // TODO: test it
            euler.display(x0, exactSolution.y(x0), X, N);
            euler.displayError(exactSolution.getY());
//            errorSeries = exactSolution.calculateError(euler.methodSeries);
//            errorChart.getData().addAll(errorSeries);
        } else {
            euler.hide();
        }
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
        try {
            int N = Integer.valueOf(NField.getText());
            double x0 = Double.valueOf(x0field.getText());
            double X = Double.valueOf(Xfield.getText());
            // TODO: to manage incorrect N's
//            if ((X - x0) / N > 0.6 || X < x0 || x0 < 1.5)
//                throw new InvalidActivityException("Invalid range");
//            else {
                this.N = N;
                this.x0 = x0;
                this.X = X;
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        buildExact();
        approxWithEuler();
        approxWithImprovedEuler();
        approxWithRungeKutta();
    }

//    private boolean isGridNumValid(int n) {
//        return N >= (X - x0) / 0.6 ;
//    }

}