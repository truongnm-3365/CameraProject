public class Pyramid {
    private Point p;
    private double horizontalFieldOfView;
    private double verticalFieldOfView;
    private double height;

    public Pyramid() {
    }

    public Pyramid(Point p, double horizontalFieldOfView, double verticalFieldOfView) {
        this.p = p;
        this.horizontalFieldOfView = horizontalFieldOfView;
        this.verticalFieldOfView = verticalFieldOfView;
    }

    public Pyramid(Point p, double horizontalFieldOfView, double verticalFieldOfView, double height) {
        this.p = p;
        this.horizontalFieldOfView = horizontalFieldOfView;
        this.verticalFieldOfView = verticalFieldOfView;
        this.height = height;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public double getHorizontalFieldOfView() {
        return horizontalFieldOfView;
    }

    public void setHorizontalFieldOfView(double horizontalFieldOfView) {
        this.horizontalFieldOfView = horizontalFieldOfView;
    }

    public double getVerticalFieldOfView() {
        return verticalFieldOfView;
    }

    public void setVerticalFieldOfView(double verticalFieldOfView) {
        this.verticalFieldOfView = verticalFieldOfView;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
