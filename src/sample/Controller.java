package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

    // Tab 1 labels
    @FXML private Label euLabel;
    @FXML private Label imLabel;
    @FXML private Label rkLabel;

    // Tab 2 checkboxes
    @FXML private CheckBox eulerErrorCheckBox;
    @FXML private CheckBox imprEulerErrorCheckBox;
    @FXML private CheckBox rKuttaErrorCheckBox;

    // Tab 2 text fields
    @FXML private TextField N0Field;
    @FXML private TextField N1Field;

    private SeriesModel model;

    // Variant 23 - y^2*e^x - 2y, x in [1.7, 9]
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new SeriesModel(functionChart, errorChart, errorDepChart);
        // Set initial values
        setFirstTabValues(1.7, -0.7, 9, 25);
        setSecondTabValues(25, 100);
        buildExact();
    }

    @FXML
    private void buildExact() {
        model.displayExact(exactCheckBox);
    }

    @FXML
    private void approxWithEuler() {
        model.displayApproximation(euLabel, model.getEuler(), eulerCheckBox);
    }

    @FXML
    private void approxWithImprovedEuler() {
        model.displayApproximation(imLabel, model.getImprovedEuler(), imprEulerCheckBox);
    }

    @FXML
    private void approxWithRungeKutta() {
        model.displayApproximation(rkLabel, model.getRungeKutta(), rKuttaCheckBox);
    }

    @FXML
    private void buildEulerError() {
        if (eulerErrorCheckBox.isSelected())
            model.displayErrorDep(new EulerMethod(), model.eulerDep, getRange());
        else
            model.hideErrorDep(model.eulerDep);
    }

    @FXML
    private void buildImpEulerError() {
        if (imprEulerErrorCheckBox.isSelected())
            model.displayErrorDep(new ImprovedEulerMethod(), model.imEulerDep, getRange());
        else
            model.hideErrorDep(model.imEulerDep);
    }

    @FXML
    private void buildRKuttaError() {
        if (rKuttaErrorCheckBox.isSelected())
            model.displayErrorDep(new RungeKuttaMethod(), model.rKuttaDep, getRange());
        else
            model.hideErrorDep(model.rKuttaDep);
    }

    /**
     * This method parses the range [N0; N1] from second tab's fields
     * @return range of values
     */
    private Pair<Integer, Integer> getRange() {
        int N0, N1;
        try {
            N0 = Integer.valueOf(N0Field.getText());
            N1 = Integer.valueOf(N1Field.getText());
            if (N0 > N1) throw new IllegalArgumentException("N0 > N1");
            if (model.getMinimalN() > N0) throw new IllegalArgumentException("N0 is invalid");
            model.setN0(N0); model.setN1(N1);
            return new Pair<>(N0, N1);
        } catch (Exception e) {
            model.showError(e.getMessage());
            setSecondTabValues(model.getN0(), model.getN1());
            return null;
        }
    }

    /**
     * Updates all charts after some changes in initial conditions
     */
    @FXML
    private void update() {
        boolean errorOccurred = false;
        try {
            int N = Integer.valueOf(NField.getText());
            double x0 = Double.valueOf(x0field.getText());
            double y0 = Double.valueOf(y0field.getText());
            double X = Double.valueOf(Xfield.getText());

            // Check validity
            if (X < x0 || x0 < 1.5 || y0 >= 0) {
                model.showError("Invalid range [x0, X] or y0 >= 0!");
                errorOccurred = true;
                throw new IllegalArgumentException("Invalid range [x0, X] or y0 >= 0");
            }
            model.updateValues(x0, y0, X, N);
        } catch (Exception e) {
            setFirstTabValues(model.getX0(), model.getY0(), model.getX(), model.getN());
            if (errorOccurred) return;
            model.showError("Incorrect type of input data!");
            return;
        }

        buildExact();
        approxWithEuler();
        approxWithImprovedEuler();
        approxWithRungeKutta();
    }

    /**
     * Updates all error dependency charts in tab 2
     */
    @FXML
    private void updateError() {
        buildEulerError();
        buildImpEulerError();
        buildRKuttaError();
    }

    private void setFirstTabValues(double x0, double y0, double X, int N) {
        x0field.setText(Double.toString(x0));
        y0field.setText(Double.toString(y0));
        Xfield.setText(Double.toString(X));
        NField.setText(Integer.toString(N));
    }

    private void setSecondTabValues(int n0, int n1) {
        N0Field.setText(Integer.toString(n0));
        N1Field.setText(Integer.toString(n1));
    }

}