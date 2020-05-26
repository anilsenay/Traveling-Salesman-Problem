import java.awt.geom.Point2D;

public class City {
    int id;
    Point2D point;
    
    public City(int id, Point2D point){
        this.id = id;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D newPoint) {
        this.point = newPoint;
    }

}