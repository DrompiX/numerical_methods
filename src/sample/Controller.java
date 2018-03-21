package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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

    private ExactSolution exactSolution;
    private EulerMethod euler;
    private ImprovedEulerMethod improvedEuler;
    private RungeKuttaMethod rungeKutta;

    // Variant 23 - y^2*e^x - 2y, y(1.7) = -0.9025147, x in [1.7, 9]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initial values:
        double x0 = 1.7, y0 = -0.7, X = 9;
        int N = 25;

        euler = new EulerMethod(functionChart, errorChart);
        improvedEuler = new ImprovedEulerMethod(functionChart, errorChart);
        rungeKutta = new RungeKuttaMethod(functionChart, errorChart);
        exactSolution = new ExactSolution(functionChart);
        updateValues(x0, y0, X, N);
        NField.setText("25");
        x0field.setText("1.7");
        y0field.setText("-0.7");
        Xfield.setText("9");
        buildExact();
    }

    @FXML
    private void buildExact() {
        if (exactCheckBox.isSelected()) exactSolution.display();
        else exactSolution.hide();
    }

    @FXML
    private void approxWithEuler() {
        if (eulerCheckBox.isSelected()) {
            euler.display();
            euler.displayError(exactSolution.getY());
        } else
            euler.hide();
    }

    @FXML
    private void approxWithImprovedEuler() {
        if (imprEulerCheckBox.isSelected()) {
            improvedEuler.display();
            improvedEuler.displayError(exactSolution.getY());
        } else
            improvedEuler.hide();
    }

    @FXML
    private void approxWithRungeKutta() {
        if (rKuttaCheckBox.isSelected()) {
            rungeKutta.display();
            rungeKutta.displayError(exactSolution.getY());
        } else
            rungeKutta.hide();
    }

    @FXML
    private void update() {
        try {
            int N = Integer.valueOf(NField.getText());
            double x0 = Double.valueOf(x0field.getText());
            double y0 = Double.valueOf(y0field.getText());
            double X = Double.valueOf(Xfield.getText());
            // TODO: to manage incorrect N's - 1) X > x0; 2) x0 < 1.5
//            if ((X - x0) / N > 0.6 || X < x0 || x0 < 1.5)
//                throw new InvalidActivityException("Invalid range");
//            else
            updateValues(x0, y0, X, N);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        buildExact();
        approxWithEuler();
        approxWithImprovedEuler();
        approxWithRungeKutta();
    }

    private void updateValues(double x0, double y0, double X, int N) {
        exactSolution.setFields(x0, y0, X, N);
        euler.setFields(x0, y0, X, N);
        improvedEuler.setFields(x0, y0, X, N);
        rungeKutta.setFields(x0, y0, X, N);
    }

}