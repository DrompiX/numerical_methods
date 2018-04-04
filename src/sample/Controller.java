package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class Controller implements Initializable {

    @FXML private LineChart<Number, Number> functionChart;
    @FXML private LineChart<Number, Number> errorChart;
    @FXML private LineChart<Number, Number> errorDepChart;

    // Tab 1 checkboxes
    @FXML private CheckBox exactCheckBox;
    @FXML private CheckBox eulerCheckBox;
    @FXML private CheckBox imprEulerCheckBox;
    @FXML private CheckBox rKuttaCheckBox;

    // Tab 1 text fields
    @FXML private TextField NField;
    @FXML private TextField x0field;
    @FXML private TextField y0field;
    @FXML private TextField Xfield;

    // Tab 2 checkboxes
    @FXML private CheckBox eulerErrorCheckBox;
    @FXML private CheckBox imprEulerErrorCheckBox;
    @FXML private CheckBox rKuttaErrorCheckBox;

    // Tab 2 text fields
    @FXML private TextField N0Field;
    @FXML private TextField N1Field;

    private ExactSolution exactSolution;
    private EulerMethod euler;
    private ImprovedEulerMethod improvedEuler;
    private RungeKuttaMethod rungeKutta;

    private Series<Number, Number> eulerDep = new Series<>();
    private Series<Number, Number> imEulerDep = new Series<>();
    private Series<Number, Number> rKuttaDep = new Series<>();

    private double x0 = 1.7, y0 = -0.7, X = 9;
    private int N = 25;

    // Variant 23 - y^2*e^x - 2y, x in [1.7, 9]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exactSolution = new ExactSolution(functionChart);
        euler = new EulerMethod(functionChart, errorChart);
        improvedEuler = new ImprovedEulerMethod(functionChart, errorChart);
        rungeKutta = new RungeKuttaMethod(functionChart, errorChart);

        updateValues(x0, y0, X, N);
//        NField.setText("25");
//        x0field.setText("1.7");
//        y0field.setText("-0.7");
//        Xfield.setText("9");
        setFirstTabValues(1.7, -0.7, 9, 25);
        N0Field.setText("25");
        N1Field.setText("100");
        buildExact();

        eulerDep.setName("Euler error");
        imEulerDep.setName("Im.Euler error");
        rKuttaDep.setName("R-Kutta error");
    }

    @FXML
    private void buildExact() {
        if (exactCheckBox.isSelected()) exactSolution.display();
        else exactSolution.hide();
    }

    @FXML
    private void approxWithEuler() {
        if (eulerCheckBox.isSelected()) euler.display();
        else euler.hide();
    }

    @FXML
    private void approxWithImprovedEuler() {
        if (imprEulerCheckBox.isSelected()) improvedEuler.display();
        else improvedEuler.hide();
    }

    @FXML
    private void approxWithRungeKutta() {
        if (rKuttaCheckBox.isSelected()) rungeKutta.display();
        else rungeKutta.hide();
    }

    @FXML
    private void buildEulerError() {
        if (eulerErrorCheckBox.isSelected())
            displayErrorDep(new EulerMethod(), eulerDep);
        else
            hideErrorDep(eulerDep);
    }

    @FXML
    private void buildImpEulerError() {
        if (imprEulerErrorCheckBox.isSelected())
            displayErrorDep(new ImprovedEulerMethod(), imEulerDep);
        else
            hideErrorDep(imEulerDep);
    }

    @FXML
    private void buildRKuttaError() {
        if (rKuttaErrorCheckBox.isSelected())
            displayErrorDep(new RungeKuttaMethod(), rKuttaDep);
        else
            hideErrorDep(rKuttaDep);
    }

    private void displayErrorDep(ApproximationMethod am, Series<Number, Number> errorDep) {
        ExactSolution exact = new ExactSolution();
        Pair<Integer, Integer> range = getRange();

        hideErrorDep(errorDep);
        if (range != null) {
            errorDep.getData().clear();
            for (int i = range.getKey(); i <= range.getValue(); i++) {
                exact.setFields(x0, y0, X, i);
                am.setFields(x0, y0, X, i, exact.getY());
                double iError = am.getMaxError();
                errorDep.getData().add(new Data<>(i, iError));
            }
            errorDepChart.getData().add(errorDep);
        }
    }

    private void hideErrorDep(Series<Number, Number> errorDep) {
        errorDepChart.setAnimated(false);
        errorDepChart.getData().remove(errorDep);
        errorDepChart.setAnimated(true);
    }

    private Pair<Integer, Integer> getRange() {
        int N0, N1;
        try {
            N0 = Integer.valueOf(N0Field.getText());
            N1 = Integer.valueOf(N1Field.getText());
            if (N0 > N1) throw new IllegalArgumentException("N0 > N1");
            return new Pair<>(N0, N1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @FXML
    private void update() {
        try {
            int N = Integer.valueOf(NField.getText());
            double x0 = Double.valueOf(x0field.getText());
            double y0 = Double.valueOf(y0field.getText());
            double X = Double.valueOf(Xfield.getText());


            // Check validity
            if (X < x0 || x0 < 1.5 || y0 >= 0) {
                showError("Invalid range [x0, X] or y0 >= 0");
                throw new IllegalArgumentException("Invalid range [x0, X] or y0 >= 0");
            }
            // TODO: to manage incorrect N's
            if ((X - x0) / N > 0.53) {
                showError("Invalid range, some chart may go to infinity");
                throw new IllegalArgumentException("Invalid range, chart goes to infinity");
            }
            updateValues(x0, y0, X, N);
        } catch (Exception e) {
            setFirstTabValues(x0, y0, X, N);
            System.out.println(e.getMessage());
            return;
        }

        buildExact();
        approxWithEuler();
        approxWithImprovedEuler();
        approxWithRungeKutta();
    }

    @FXML
    private void updateError() {
        buildEulerError();
        buildImpEulerError();
        buildRKuttaError();
    }

    private void showError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error here");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void updateValues(double x0, double y0, double X, int N) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;

        exactSolution.setFields(x0, y0, X, N);
        double[] exact = exactSolution.getY();

        euler.setFields(x0, y0, X, N, exact);
        improvedEuler.setFields(x0, y0, X, N, exact);
        rungeKutta.setFields(x0, y0, X, N, exact);
    }

    private void setFirstTabValues(double x0, double y0, double X, int N) {
        x0field.setText(Double.toString(x0));
        y0field.setText(Double.toString(y0));
        Xfield.setText(Double.toString(X));
        NField.setText(Integer.toString(N));
    }

    private void setSecondTabValues(int n0, int n1) {

    }

}