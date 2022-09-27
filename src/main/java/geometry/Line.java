package geometry;

public class Line {

    Point p1;
    Point p2;

    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    // https://stackoverflow.com/questions/3838329/how-can-i-check-if-two-segments-intersect
    public boolean isOverlappingALine(Line line){
        return ccw(p1, line.getP1(), line.getP2()) != ccw(p2, line.getP1(), line.getP2()) && ccw(p1, p2, line.getP1()) != ccw(p1, p2, line.getP2());
    }
    // https://stackoverflow.com/questions/3838329/how-can-i-check-if-two-segments-intersect
    private boolean ccw(Point A, Point B, Point C){
        return (C.getY() - A.getY()) * (B.getX()-A.getX()) > (B.getY() - A.getY()) * (C.getX() - A.getX());
    }



    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "Line{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
