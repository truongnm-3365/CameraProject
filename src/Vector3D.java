public class Vector3D {

    private double x;
    private double y;
    private double z;
    private Point a;
    private Point b;

    public Vector3D() {
    }

    public Vector3D(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.x = b.getX() - a.getX();
        this.y = b.getY() - a.getY();
        this.z = b.getZ() - a.getZ();
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Double length(){
        double l = Math.sqrt(this.length2());
        return (double) Math.round(l * 1000) / 1000;
    }

    public Double length2(){
        double l2 = Math.pow(this.a.getX() - this.b.getX(),2) + Math.pow(this.a.getY() - this.b.getY(),2) + Math.pow(this.a.getZ() - this.b.getZ(),2);
        return (double) Math.round(l2 * 1000) / 1000;
    }




}
