package Camera;

import Print.Show;
import Room.Room;
import Tool.*;

public class Camera implements Show{

	private Point peakPoint;
	private double horizontalFieldOfView;
	private double verticalFieldOfView;
	private double height;

	public Camera() {
	}

	public Camera(Point p, double horizontalFieldOfView, double verticalFieldOfView) {
		this.peakPoint = p;
		this.horizontalFieldOfView = horizontalFieldOfView;
		this.verticalFieldOfView = verticalFieldOfView;
	}

	public Camera(Point p, double horizontalFieldOfView, double verticalFieldOfView, double height) {
		this.peakPoint = p;
		this.horizontalFieldOfView = horizontalFieldOfView;
		this.verticalFieldOfView = verticalFieldOfView;
		this.height = height;
	}

	public Point getPeakPoint() {
		return peakPoint;
	}

	public void setP(Point p) {
		this.peakPoint = p;
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

	public PlaneEquation PlaneIncludePeakIn(Cuboid cuboid){
		if(cuboid.PlaneXNotEqualZero().IsIncludePoint(peakPoint))
			return cuboid.PlaneXNotEqualZero();
		else if(cuboid.PlaneYNotEqualZero().IsIncludePoint(peakPoint))
			return cuboid.PlaneYNotEqualZero();
		else if(cuboid.PlaneZNotEqualZero().IsIncludePoint(peakPoint))
			return cuboid.PlaneZNotEqualZero();
		else if(Math3D.PlaneZEqualZero().IsIncludePoint(peakPoint))
			return Math3D.PlaneXEqualZero();
		else if(Math3D.PlaneYEqualZero().IsIncludePoint(peakPoint))
			return Math3D.PlaneYEqualZero();
		else
			return Math3D.PlaneZEqualZero();

	}


	@Override
	public void show() {
		this.getPeakPoint().show();
		System.out.println(" " + this.getHorizontalFieldOfView() + " " + this.getVerticalFieldOfView() );
		
	}
}
