import java.util.ArrayList;
import java.util.List;

public class Object3D {
    private List<Point> listPoint = new ArrayList<>();

    public Object3D() {
    }

    public Object3D(List<Point> listPoint) {
        this.listPoint = listPoint;
    }

    public List<Point> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<Point> listPoint) {
        this.listPoint = listPoint;
    }


    public boolean IsInFloor(){
        Math3D tool = new Math3D();
        for(int i = 0 ;i<listPoint.size();i++){
            if(tool.PlaneZEqualZero().IsIncludePoint(listPoint.get(i)))
                return true;
        }
        return false;
    }

    public double height(){
        Math3D tool = new Math3D();
        double maxRange = listPoint.get(0).rangeTo(tool.PlaneZNotEqualZero(listPoint));;
        for(int i =0 ;i<listPoint.size();i++){
            if(listPoint.get(i).rangeTo(tool.PlaneZEqualZero())>= maxRange)
                maxRange = listPoint.get(i).rangeTo(tool.PlaneZNotEqualZero(listPoint));
        }
        return maxRange;
    }

    public PlaneEquation planeBottom(){
        double minZ = listPoint.get(0).getZ();
        for(int i = 0;i<listPoint.size();i++){
            if(listPoint.get(i).getZ()<=minZ)
                minZ = listPoint.get(i).getZ();
        }
        return new PlaneEquation(0,0,1,-minZ);
    }
    public PlaneEquation planeTop(){
        double maxZ = listPoint.get(0).getZ();
        for(int i = 0;i<listPoint.size();i++){
            if(listPoint.get(i).getZ()>=maxZ)
                maxZ = listPoint.get(i).getZ();
        }
        return new PlaneEquation(0,0,1,-maxZ);
    }

    public boolean IsInvalidIn(List<Object3D> list){
        Math3D tool = new Math3D();
        List<Object3D> listObject = new ArrayList<Object3D>();
        listObject.addAll(list);
        listObject.remove(this);
        int check1 =0;
        int check2 =0;
        if(listObject.size()!=0){
            if(this.IsInFloor()){
                for(int i = 0;i<listObject.size();i++){
                    for(int j = 0;j<listPoint.size();j++){
                        if(tool.IsPointInCuboidNotFace(listPoint.get(j),listObject.get(i).getListPoint()))
                            return false;
                    }
                }
                return true;
            }else{
                for(int i = 0;i<listObject.size();i++){
                    for(int j = 0;j<listPoint.size();j++){
                        if(tool.IsPointInCuboidNotFace(listPoint.get(j),listObject.get(i).getListPoint()))
                            return false;
                        if(Math.abs(listPoint.get(j).rangeTo(listObject.get(i).planeBottom())-listObject.get(i).height())<0.0001 && tool.IsPointInWall(listPoint.get(i),listObject.get(i).getListPoint()))
                            check1++;
                        if(Math.abs(listPoint.get(j).rangeTo(listObject.get(i).planeTop())-this.height())<0.0001)
                            check2++;
                    }

                }
                if(check1!=0 && check2!=0)
                    return true;
            }
        }else {
            if(this.IsInFloor())
                return true;
        }

        return false;
    }

}
