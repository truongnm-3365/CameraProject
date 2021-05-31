public class LineEquation {
    private Point p;
    private Point q;
    private double a;
    private double b;
    private double c;

    public LineEquation() {
    }

    public LineEquation(Point p, double a, double b, double c) {
        this.p = p;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public LineEquation(Point p, Point q) {
        this.p = p;
        Vector3D v = new Vector3D(p,q);
        this.a = v.getX();
        this.b = v.getY();
        this.c = v.getZ();
    }

    public Point getQ() {
        return q;
    }

    public void setQ(Point q) {
        this.q = q;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public Vector3D VectorDirect(){
        return new Vector3D(this.a,this.b,this.c);
    }
}
