import java.awt.geom.Line2D;

public class Line {
    int point_1_id;
    int point_2_id;
    Line2D line;

    public Line(Line2D line, int id_1, int id_2) {
        this.point_1_id = id_1;
        this.line = line;
        this.point_2_id = id_2;
    }

    public Line2D getLine() {
        return line;
    }

    public void setLine(Line2D line) {
        this.line = line;
    }

    public int getPoint_1_id() {
        return point_1_id;
    }

    public void setPoint_1_id(int point_1_id) {
        this.point_1_id = point_1_id;
    }

    public int getPoint_2_id() {
        return point_2_id;
    }

    public void setPoint_2_id(int point_2_id) {
        this.point_2_id = point_2_id;
    }

    public double getX1() {
        return line.getX1();
    }

    public double getY1() {
        return line.getY1();
    }

    public double getX2() {
        return line.getX2();
    }

    public double getY2() {
        return line.getY2();
    }

    public void switchPoints() {
        int tempID = this.point_1_id;
        this.point_1_id = point_2_id;
        this.point_2_id = tempID;

        this.line = new Line2D.Double(line.getX2(), line.getY2(), line.getX1(), line.getY1());
    }

    public boolean isIntersect(Line line) {

        if (this.point_1_id != line.getPoint_1_id() && this.point_1_id != line.getPoint_2_id()
        && this.point_2_id != line.getPoint_1_id() && this.point_2_id != line.getPoint_2_id()) {
                if(line.getLine().intersectsLine(this.getLine()))
                    return true;
            return false;
        } else
            return false;
    }
}