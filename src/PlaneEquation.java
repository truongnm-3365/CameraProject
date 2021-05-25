
public class PlaneEquation {
    private double a;
    private double b;
    private double c;
    private double d;
    private Point p1;
    private Point p2;
    private Point p3;
    public PlaneEquation(double a, double b, double c, double d) {    // ax + by + cz + d = 0
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public PlaneEquation(Point p1, Point p2, Point p3){
        Vector3D a1 = new Vector3D(p1,p2);
        Vector3D a2 = new Vector3D(p1,p3);
        Vector3D dirProVect = VectorDirectProduct(a1,a2);
        this.a = dirProVect.getX();
        this.b = dirProVect.getY();
        this.c = dirProVect.getZ();
        this.d = -this.a * p1.getX() - this.b * p1.getY() - this.c * p1.getZ();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
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

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public boolean IsIncludePoint(Point p){
        if(this.a*p.getX() + this.b* p.getY() + this.c * p.getZ() + d == 0)
            return true;
        return false;
    }

    public Vector3D VectorDirectProduct(Vector3D a1, Vector3D a2){
        double t1 = a1.getY() * a2.getY() - a2.getY() * a1.getZ();
        double t2 = a1.getZ() * a2.getX() - a1.getX() * a2.getZ();
        double t3 = a1.getX() * a2.getY() - a2.getX() * a1.getY();
        return new Vector3D(t1,t2,t3);
    }

    public boolean IsPerpendicular(Vector3D v){
        if(v.getX() * this.b == v.getY() * this.a && v.getY() * this.c == v.getZ() * this.b  && v.getX() * c == v.getZ() * a)
                return true;

        return false;
    }

    public boolean IsCoincidentPoint(Point p){
        if(this.p1.equals(p) && this.p2.equals(p) && this.p3.equals(p))
            return true;
        return false;
    }

}
