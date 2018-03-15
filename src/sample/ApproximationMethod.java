package sample;

public abstract class ApproximationMethod {
    double x0, y0, X;
    int N;

    void setFields(double x0, double y0, double X, int N) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;
    }

    public abstract void display(double x0, double y0, double X, int N);

    public abstract void hide();

    protected abstract void make();
}
