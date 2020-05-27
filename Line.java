import java.awt.geom.Line2D;

public class Line {
    int lineID;
    Line2D line;

    public Line(Line2D line, int id){
        this.lineID = id;
        this.line = line;
    }

    public int getLineID() {
        return lineID;
    }

    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public Line2D getLine() {
        return line;
    }

    public void setLine(Line2D line) {
        this.line = line;
    }
}