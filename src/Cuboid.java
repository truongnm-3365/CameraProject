import java.util.ArrayList;
import java.util.List;

public class Cuboid {
    private List<Point> pointList = new ArrayList<>();

    public Cuboid() {
    }

    public Cuboid(List<Point> pointList) {
        this.pointList = pointList;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }


    public double xMax (){
        double max = pointList.get(0).getX();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getX() >= max)
                max = pointList.get(i).getX();
        }
        return max;
    }

    public double yMax (){
        double max = pointList.get(0).getY();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getY() >= max)
                max = pointList.get(i).getY();
        }
        return max;
    }

    public double zMax (){
        double max = pointList.get(0).getZ();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getZ() >= max)
                max = pointList.get(i).getZ();
        }
        return max;
    }

    public double xMin (){
        double min = pointList.get(0).getX();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getX() <= min)
                min = pointList.get(i).getX();
        }
        return min;
    }

    public double yMin (){
        double min = pointList.get(0).getY();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getY() <= min)
                min = pointList.get(i).getY();
        }
        return min;
    }

    public double zMin (){
        double min = pointList.get(0).getZ();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getZ() <= min)
                min = pointList.get(i).getZ();
        }
        return min;
    }

    public boolean IsPointInCuboid(Point p){
        if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        return false;
    }

    public boolean IsPointInCuboidNotFace(Point p){
        if(p.getX()>xMin() && p.getX()<xMax() && p.getY()>yMin() && p.getY()<yMax() && p.getZ()>zMin() && p.getZ()<zMax())
            return true;
        return false;
    }

    public boolean IsPointInWall(Point p){
        if(p.getX()==xMin() || p.getX()==xMax() && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()==yMin() || p.getY()==yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()==zMax())
            return true;
        return false;
    }
    public boolean IsPointInFace(Point p){
        if((p.getX()==xMin() || p.getX()==xMax()) && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        if(p.getX()>=xMin() && p.getX()<=xMax() && (p.getY()==yMin() || p.getY()==yMax()) && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()>=yMin() && p.getY()<=yMax() && (p.getZ()==zMax() ||p.getZ()==zMin()) )
            return true;
        return false;
    }

    public boolean IsIncludeSmallCuboid(Cuboid smallCuboid){
        int count = 0;

        for(int i = 0; i<smallCuboid.getPointList().size(); i++){
            if(!IsPointInCuboid(smallCuboid.getPointList().get(i)))
                return false;
            if(smallCuboid.getPointList().get(0).getZ() == smallCuboid.getPointList().get(i).getZ())
                count++;
        }

        if(count != 4)
            return false;
        return true;
    }

    public PlaneEquation PlaneXNotEqualZero(){
        for( int i = 0; i<pointList.size(); i++){
            if(pointList.get(i).getX()!=0)
                return new PlaneEquation(1,0,0,-pointList.get(i).getX());

        }
        return null;
    }

    public PlaneEquation PlaneYNotEqualZero(){
        for( int i = 0; i<pointList.size(); i++){
            if(pointList.get(i).getY()!=0)
                return new PlaneEquation(0,1,0,-pointList.get(i).getY());

        }
        return null;
    }

    public PlaneEquation PlaneZNotEqualZero(){
        for( int i = 0; i<pointList.size(); i++){
            if(pointList.get(i).getZ()!=0)
                return new PlaneEquation(0,0,1,-pointList.get(i).getZ());

        }
        return null;
    }

    public boolean IsStadardAxisCuboid(){
        int count =0;
        for(int i = 0; i< getPointList().size();i++){
            if(getPointList().get(i).getX()==0 && getPointList().get(i).getY()==0 && getPointList().get(i).getZ() == 0)
                count++;
            if(getPointList().get(i).getX()>0 && getPointList().get(i).getY()==0 && getPointList().get(i).getZ() == 0)
                count++;
            if(getPointList().get(i).getX()==0 && getPointList().get(i).getY()>0 && getPointList().get(i).getZ() == 0)
                count++;
            if(getPointList().get(i).getX()==0 && getPointList().get(i).getY()==0 && getPointList().get(i).getZ() > 0)
                count++;
        }
        if(count ==4)
            return true;

        return false;
    }
}
