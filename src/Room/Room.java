package Room;

import java.util.ArrayList;
import java.util.List;

import Camera.Camera;
import Object3D.Object3D;
import Tool.Cuboid;
import Tool.Math3D;
import Tool.Point;

public class Room extends Cuboid {
	private List<Object3D> object3DList = new ArrayList<Object3D>();
	private List<Camera> cameraList = new ArrayList<Camera>();

	public Room(List<Point> pointList) {
		super(pointList);
	}

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public List<Object3D> getObject3DList() {
		return object3DList;
	}

	public void setObject3DList(List<Object3D> object3DList) {
		this.object3DList = object3DList;
	}

	public List<Camera> getCameraList() {
		return cameraList;
	}

	public void setCameraList(List<Camera> cameraList) {
		this.cameraList = cameraList;
	}

	


	//
	public boolean includePoint(Point point) {
		Math3D tool = new Math3D();
		return IsPointInCuboid(point);
	}
	
	public boolean includeObject3D(Object3D object3d) {
		Math3D tool = new Math3D();
		return IsIncludeSmallCuboid(object3d);
	}
	
	public boolean includeCamera(Camera camera) {
		Math3D tool = new Math3D();
		return IsPointInFaceNotBottom(camera.getPeakPoint());
	}
	
	
}
