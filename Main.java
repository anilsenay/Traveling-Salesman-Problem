import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

            while((line=br.readLine())!=null){

                String[] values = line.replaceAll("[ ]+", " ").trim().split(" ");
                Point2D point = new Point2D.Double(Double.parseDouble(values[1]),Double.parseDouble(values[2]));
                cityList.add(new City(Integer.parseInt(values[0]), point));
                
                cityCounter++;
            }

            fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    }

    // we are not using this function anymore, we use randomizedNearestNeighbor now
    public static void nearestNeighbor(){
        int cityNumber = cityList.size();
        for(int i = 0; i < cityNumber - 1; i++){
            double minDistance = Integer.MAX_VALUE;
            int minCityIndex = i + 1;
            for(int j = i + 1; j < cityNumber; j++){
                if(cityList.get(i).getPoint().distance(cityList.get(j).getPoint()) < minDistance){
                    minDistance = cityList.get(i).getPoint().distance(cityList.get(j).getPoint());
                    minCityIndex = j;
                }
            }
            Collections.swap(cityList, i + 1, minCityIndex);
        }
    }

    // get nearest 3 cities and choose one of them randomly. So on every run, it will give different results. 
    public static void randomizedNearestNeighbor(){
        int cityNumber = cityList.size();
        for(int i = 0; i < cityNumber - 1; i++){
            double minDistances[] = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
            int minIndexs[] = new int[3];
            for(int j = i + 1; j < cityNumber; j++){

                if(cityList.get(i).getPoint().distance(cityList.get(j).getPoint()) < minDistances[0]){
                    minDistances[0] = cityList.get(i).getPoint().distance(cityList.get(j).getPoint());
                    minIndexs[0] = j;
                } else if(cityList.get(i).getPoint().distance(cityList.get(j).getPoint()) < minDistances[1]){
                    minDistances[1] = cityList.get(i).getPoint().distance(cityList.get(j).getPoint());
                    minIndexs[1] = j;
                } else if(cityList.get(i).getPoint().distance(cityList.get(j).getPoint()) < minDistances[2]){
                    minDistances[2] = cityList.get(i).getPoint().distance(cityList.get(j).getPoint());
                    minIndexs[2] = j;
                }

            }
            Collections.swap(cityList, i + 1, minIndexs[(int)(Math.random()*3)]);
        }
    }

    public static void lineListCreate() {
        int cityNumber = cityList.size();
        for(int i = 0; i < cityNumber - 1; i++){
            double x_1 = cityList.get(i).getPoint().getX();
            double y_1 = cityList.get(i).getPoint().getY();
            double x_2 = cityList.get(i+1).getPoint().getX();
            double y_2 = cityList.get(i+1).getPoint().getY();

            int ID_1 = cityList.get(i).id;
            int ID_2 = cityList.get(i+1).id;

            pathList.add(new Line(new Line2D.Double(x_1, y_1, x_2, y_2), ID_1, ID_2));
        }
        // create last line with first and last point
        pathList.add(new Line(new Line2D.Double(
            cityList.get(cityNumber-1).getPoint().getX(), 
            cityList.get(cityNumber-1).getPoint().getY(), 
            cityList.get(0).getPoint().getX(), 
            cityList.get(0).getPoint().getY()), 
            cityList.get(cityNumber-1).getId(), 
            cityList.get(0).getId()));
    }
    
    static int calculateDistance() {
        int sumOfLines = 0;

        for(int i = 0; i < cityCounter; i++){
            double x1 = pathList.get(i).getLine().getP1().getX();
            double x2 = pathList.get(i).getLine().getP2().getX();
            double y1 = pathList.get(i).getLine().getP1().getY();
            double y2 = pathList.get(i).getLine().getP2().getY();

            sumOfLines += (int)Math.round(Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2)));
        }

        return sumOfLines;
    }

    static void switchToLines(int index_1, int index_2) {
        Line Line1 = pathList.get(index_1);
        Line Line2 = pathList.get(index_2);
        double tempX = Line1.getX2();
        double tempY = Line1.getY2();
        int tempID = Line1.getPoint_2_id();
        Line1.setLine((new Line2D.Double(Line1.getX1(), Line1.getY1(), Line2.getX1(), Line2.getY1())));
        Line2.setLine((new Line2D.Double(tempX, tempY, Line2.getX2(), Line2.getY2())));
        Line1.setPoint_2_id(Line2.getPoint_1_id());
        Line2.setPoint_1_id(tempID);

        reversePath(index_1+1, index_2-1);
    }

    static void reversePath(int index_1, int index_2){
        while(index_1 < index_2){
            Line tempLine = pathList.get(index_1);
            pathList.set(index_1, pathList.get(index_2));
            pathList.set(index_2, tempLine);
            pathList.get(index_1).switchPoints();
            pathList.get(index_2).switchPoints();
            index_1++;
            index_2--;
        }
        if(index_1 == index_2)
            pathList.get(index_2).switchPoints();
    }

    static void printList() {
        for(int i = 0; i < cityCounter; i++){
            System.out.println(pathList.get(i).getPoint_1_id());
        }
        System.out.println();
    }

    static void printPoints() {
        for(int i = 0; i < cityCounter; i++){
            System.out.println(pathList.get(i).getX1() + " " + pathList.get(i).getY1());
        }
        System.out.println();
    }

    static void printToFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        String output = "";
        output += calculateDistance() + "\n";
        for(int i = 0; i < cityCounter; i++){
           output += (pathList.get(i).getPoint_1_id()) + "\n";
        }
        
        fileWriter.write(output);
        fileWriter.close();
    }

    public static void twoOpt(){
        for(int i = 0; i < cityCounter; i++){
            int i_length = pathList.get(i).lineLength();
            for(int j = i + 1; j < cityCounter; j++){
                int j_length = pathList.get(j).lineLength();

                Line Line1 = new Line(new Line2D.Double(pathList.get(i).getX1(), pathList.get(i).getY1(), pathList.get(j).getX1(), pathList.get(j).getY1()), 0,0);
                Line Line2 = new Line(new Line2D.Double(pathList.get(i).getX2(), pathList.get(i).getY2(), pathList.get(j).getX2(), pathList.get(j).getY2()), 0,0);

                int line1_length = Line1.lineLength();
                int line2_length = Line2.lineLength();
                

                if(i_length + j_length > line1_length + line2_length){
                    switchToLines(i, j);
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {

        // read from input and create City objects
        readFromFile(args[0]);

        // apply randomized nearest neighbor algorithm to cityList
        randomizedNearestNeighbor();

        // creating lines from cityList objects
        lineListCreate();

        // running two opt 50 times, gave better results in our experiments.
        for(int k = 0; k < 50; k++){
            twoOpt();
        }

        //print
        printToFile(args[1]);
    }
}
