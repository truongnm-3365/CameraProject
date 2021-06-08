package Controller;

import java.io.BufferedReader;  
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Camera.Camera;
import Object3D.Object3D;
import Room.Room;
import Tool.Math3D;
import Tool.Point;

public class Main {

	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		boolean done1 = false;
		Math3D tool = new Math3D();
		
		String url ="";
		List<String> listLine;
		Room room = new Room();
		List<Object3D> listObject3ds =new ArrayList<Object3D>() ;
		List<Camera> listCameras = new ArrayList<Camera>();
		
        //String url = "C:\\Users\\ADMIN\\eclipse-workspace\\CameraPro\\src\\Tool\\input.txt";
		//C:\\Users\\ADMIN\\OneDrive\\Desktop\\input.txt
        
        while(true) {
          showMenu();
          System.out.println("Your choice: ");
          int key = sc.nextInt();	
          
          switch (key) {
		case 0: {
			sc.close();
			System.exit(0);
			break;
		}
		case 1: {
			boolean check = true;
	        
	        do {
	        	System.out.println("Nhap url:");
				url =  sc.next();
		        try {
		        	listLine = readInput(url);
			        
			        String line1 = listLine.get(0);
			        room = makeRoom(line1);
			        
			        int numObject = Integer.parseInt(listLine.get(1));
			        
			        listObject3ds = makeListObject3Ds(listLine);
			        room.setObject3DList(listObject3ds);

			        listCameras = makeListCameras(listLine, numObject);
			        room.setCameraList(listCameras);

			        if (tool.IsCuboid(room.getPointList()) && (room.IsStadardAxisCuboid())){
			        	System.out.println("Check Room: true");
					}else {
						System.out.println("Check Room: false");
						check = false;
					}
			        
			        for(int i = 0; i< numObject ; i++) {
			        	if(room.includeObject3D(listObject3ds.get(i)) && listObject3ds.get(i).IsInvalidIn(listObject3ds) ) {
			        		System.out.println("Object " + (i + 1) + ": valid");

			        	}else {
							System.out.println("Object " + (i + 1) + ": invalid");
							check= false;
						}
			        }
			        
			        for(int i = 0; i< listCameras.size() ; i++) {
			        	if(room.includeCamera(listCameras.get(i))) {
			        		System.out.println("Camera " + (i + 1) + " valid");
			        	}else {
			        		System.out.println("Camera " + (i + 1) + " invalid");
			        		check = false;
						}
			        }
			        
			        done1 = true;
				} catch (Exception e) {
					System.err.println("File not found!");
				}
	        }while(!check);
			
	        break;
		}
		case 2:{
			if(!done1) {
				System.out.println("No input file. Choose again!!");
				break;
			}
			
			System.out.println("Room axis: ");
			for(Point point : room.getPointList()) {
				point.show();
			}
			System.out.println("\nObjects axis:");
			for(int i = 0;i < listObject3ds.size() ; i++ ) {
				System.out.println("Object " + ( i + 1 ) + " : " );
				for(Point point: listObject3ds.get(i).getPointList()) {
					point.show();
				}
			}
			
			System.out.println("\nCamera axis: ");
			for(int i = 0; i < listCameras.size(); i++) {
				System.out.println("Camera " + ( i + 1) + " : ");
				listCameras.get(i).show();
			}
			
			break;
		}
		case 3:{
			if(!done1) {
				System.out.println("No input file. Choose again!!");
				break;
			}
			System.out.println("Enter point: ");
			System.out.println("Enter the X-axis");
			Double x = sc.nextDouble();
			System.out.println("Enter the Y-axis");
			Double y = sc.nextDouble();
			System.out.println("Enter the Z-axis");
			Double z = sc.nextDouble();
			Point point = new Point(x, y, z);
			
	    	int checkObj = 0;
	    	if(room.includePoint(point)) {
	    		for(Object3D tmpO : listObject3ds) {
	    			if(tmpO.IsPointInCuboid(point))
	    				checkObj=1;
	    		}
	    		
	    		if (checkObj == 1)   {
	    			System.out.println("thuoc Vat");
	    		}else if(point.IsSeenByCamera(listCameras,room,listObject3ds)) {
	    			System.out.println("thuoc Cam va khong thuoc vat");
	    		}else {
	    			System.out.println("chi thuoc phong");
	    		}
	    	}else {
				System.out.println("nam ngoai phong");
			}
	    	
	    	break;
		}
		case 4:
			if(!done1) {
				System.out.println("No input file. Choose again!!");
				break;
			}
			double totalPoint = 0;
			double seenPoint = 0;

			for(double x = room.xMin();x<=room.xMax();x=x+0.005)
			{
				for(double y = room.yMin();y<=room.yMax();y=y+0.005)
				{
					for(double z = room.zMin();z<=room.zMax();z=z+0.005){
						totalPoint++;
						Point point = new Point(x,y,z);
						if(point.IsSeenByCamera(listCameras,room,listObject3ds))
							seenPoint++;
					}
				}

			}

			System.out.println("Result: "+(seenPoint/totalPoint)*100+ "%");
		default:
			break;
		}
        }
       
        
    }
	
	
	
	public static void showMenu() {
		System.out.println("-----------------------------------------------------");
		System.out.println("1.Read file ");
		System.out.println("2.Details ");
		System.out.println("3.Check Point ");
		System.out.println("4.Calculate visible space ");
		System.out.println("0.Exit ");
		System.out.println("-----------------------------------------------------");
	}
	
	public static void showMenu1() {
		System.out.println("-----------------------------------------------------");
		System.out.println("Enter the X-axis");
		System.out.println("Enter the Y-axis");
		System.out.println("Enter the Z-axis");
		System.out.println("-----------------------------------------------------");
	}
	
	
	public static List <String> readInput(String url){
		FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        List <String> listLine = new ArrayList<String>();
        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                listLine.add(line);
                line = bufferedReader.readLine();
            }
        }catch (Exception ex){
            System.err.print("Error:");
        }
        
        return listLine;
	}
	
	public static Room makeRoom(String input) {
		String str = input.replaceFirst("\\(","");
        String str1 = str.substring(0, str.length()-1);
        String [] points = str1.split("\\) \\(");
        List<Point> listPointRoom = new ArrayList<Point>(8);
        for (int i = 0; i< points.length; i++){
            String [] xyz = points[i].split(", ");
            listPointRoom.add(new Point(Double.parseDouble(xyz[0]),Double.parseDouble(xyz[1]),Double.parseDouble(xyz[2])));
        }
		return new Room(listPointRoom);
	}
	
	public static List<Object3D> makeListObject3Ds(List<String> input){
		List<Object3D> object3ds = new ArrayList<Object3D>();
		
        for(int i=2;i<2+Integer.parseInt(input.get(1));i++){
            String str2 = input.get(i).replaceFirst("\\(","");
            String str3 = str2.substring(0, str2.length()-1);
            String [] pointS = str3.split("\\) \\(");
            List<Point> listPointObject = new ArrayList<Point>(8);
            for (int j = 0; j< pointS.length; j++){
                String [] xyz = pointS[j].split(", ");
                listPointObject.add(new Point(Double.parseDouble(xyz[0]),Double.parseDouble(xyz[1]),Double.parseDouble(xyz[2])));
            }
            object3ds.add(new Object3D(listPointObject));
        }
		return object3ds;
	}
	
	public static List<Camera> makeListCameras(List<String> input, int soVat){
		List<Camera> cameras = new ArrayList<Camera>();
		
		int k = (soVat+2)+1;
  
        for(int i = k;i<k+Integer.parseInt(input.get(k-1));i++){
            String [] cameraSpec = input.get(i).split("\\) ");
            String str2 = cameraSpec[0].replaceFirst("\\(","");
            String [] xyz = str2.split(", ");
            String [] view = cameraSpec[1].split(" ");
            Point cameraPoint = new Point(Double.parseDouble(xyz[0]),Double.parseDouble(xyz[1]),Double.parseDouble(xyz[2]));
           
           
            cameras.add(new Camera(cameraPoint,Double.parseDouble(view[0]),Double.parseDouble(view[1])));
        }
		
		return cameras;
	}
}