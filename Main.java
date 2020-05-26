import java.io.*;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Main {
    static ArrayList<City> cityList = new ArrayList<City>();
    static int cityCounter = 0;

    static void readFromFile(String fileName) throws IOException {
		try {
			File file=new File(fileName);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null){

                String[] values = line.split(" ");
                int id = Integer.parseInt(values[0]);
                // double x = Double.parseDouble(values[1]);
                // double y = Double.parseDouble(values[2]);

                cityList.add(new City(id, new Point2D.Double(Double.parseDouble(values[1]), Double.parseDouble(values[2]))));

                cityCounter++;
            }
            fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
    public static void main(String args[]) throws IOException {
        readFromFile("example-input-1.txt");

        for(int i = 0; i < cityCounter; i++){
            System.out.println(cityList.get(i).id + " " + cityList.get(i).point.getX() + " " + cityList.get(i).point.getY());
        }

        Point2D test = new Point2D.Float(222,2);
        Point2D test2 = new Point2D.Float(2,2);
        Line2D line = new Line2D.Float(0, 0, 2, 0);
        Line2D line2 = new Line2D.Float(0, 1, 2, 1);
        System.out.println(line.intersectsLine(line2));
        System.out.println(test.distance(test2));
        line2.setLine(test, test2);
        System.out.println(line2.getX1());;
    }
}