package geometry;

import java.util.List;

public class Point {

    private double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Point getMiddlePointOfPointList(List<Point> pointList){
        double sumX = 0, sumY = 0;
        for(Point point : pointList){
            sumX += point.getX();
            sumY += point.getY();
        }

        return new Point(sumX / pointList.size(), sumY / pointList.size());
    }

    public static boolean compare(Point p1, Point p2){
        return p1.getX() == p2.getX() && p2.getY() == p2.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
