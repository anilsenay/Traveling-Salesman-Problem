import java.io.*;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Main {
    static ArrayList<City> cityList = new ArrayList<City>();
    static ArrayList<Line> pathList = new ArrayList<Line>();
    static int cityCounter = 0;

    static void readFromFile(String fileName) throws IOException {
		try {
			File file=new File(fileName);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            int oldID = -1;
            Point2D oldCity = null;
            while((line=br.readLine())!=null){

                String[] values = line.replaceAll("[ ]+", " ").trim().split(" ");
                // int id = Integer.parseInt(values[0]);
                // // double x = Double.parseDouble(values[1]);
                // // double y = Double.parseDouble(values[2]);

                // cityList.add(new City(id, new Point2D.Double(Double.parseDouble(values[1]), Double.parseDouble(values[2]))));

                if(oldID == -1){
                    oldID = Integer.parseInt(values[0]);
                    oldCity = new Point2D.Double(Double.parseDouble(values[1]), Double.parseDouble(values[2]));
                } 
                else {
                    double x = Double.parseDouble(values[1]);
                    double y =  Double.parseDouble(values[2]);
                    pathList.add(new Line(new Line2D.Double(oldCity.getX(), oldCity.getY(), x, y), oldID));
                    oldID = Integer.parseInt(values[0]);
                    oldCity = new Point2D.Double(x, y);
                }

                cityCounter++;
            }
            Point2D startCity = new Point2D.Double(pathList.get(0).line.getX1(), pathList.get(0).line.getY1());
            pathList.add(new Line(new Line2D.Double(oldCity.getX(), oldCity.getY(), startCity.getX(), startCity.getY()), oldID));

            fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
    public static void main(String args[]) throws IOException {
        readFromFile("example-input-2.txt");

        for(int i = 0; i < cityCounter; i++){
            System.out.println(pathList.get(i).getLineID() + " " 
            + pathList.get(i).getLine().getP1().getX() + " " + pathList.get(i).getLine().getP1().getY() + " "
            + pathList.get(i).getLine().getP2().getX() + " " + pathList.get(i).getLine().getP2().getY());
        }

        Point2D test = new Point2D.Float(222,2);
        Point2D test2 = new Point2D.Float(2,2);
        Line2D line = new Line2D.Float(0, 0, 2, 0);
        Line2D line2 = new Line2D.Float(0, 1, 2, 1);
        System.out.println(line.intersectsLine(line2));
        System.out.println(test.distance(test2));
        line2.setLine(test, test2);
        System.out.println(line2.getX1());

        System.out.println("is intersect: " + (pathList.get(1).getLine().intersectsLine(pathList.get(2).getLine())));
    }
}