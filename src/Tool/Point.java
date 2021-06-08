package Tool;

import Camera.Camera;
import Object3D.Object3D;
import Print.Show;

import java.util.List;

public class Point implements Show {
    private double x;
    private double y;
    private double z;

    public Point() {
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Math.abs(point.getX()-this.x) < 0.000001 && Math.abs(point.getY() - this.y) < 0.000001 && Math.abs(point.getZ()- this.z) < 0.000001;
    }


    public double rangeTo (PlaneEquation planeEquation){
        double d = Math.sqrt(planeEquation.getA()* planeEquation.getA()+planeEquation.getB()* planeEquation.getB()+planeEquation.getC()* planeEquation.getC());
        return Math.abs(this.x*planeEquation.getA()+ this.y* planeEquation.getB()+ this.z* planeEquation.getC()+ planeEquation.getD())/d;
    }

    public boolean IsSeenByCamera(List<Camera> cameraList, Cuboid cuboid, List<Object3D> object3DList){
        int check = 0;
        for(int i = 0;i<cameraList.size();i++){
            for (int j = 0;j<object3DList.size();j++){
                if(object3DList.get(j).IsThrouthLineSegment(cameraList.get(i),this,cuboid)){
                    check ++;
                    break;
                }
            }
        }
        if(check == cameraList.size())
            return false;

        return true;
    }

    @Override
    public void show() {
        System.out.print("("+x+", "+y+", "+z+") ");
    }
}