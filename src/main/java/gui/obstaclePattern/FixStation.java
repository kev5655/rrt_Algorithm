package gui.obstaclePattern;

import gui.dataClass.Data;
import gui.drawer.ObstacleDrawer;
import gui.geometry.Point;
import gui.geometry.Rectangle;
import javafx.scene.Group;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FixStation {

    double fieldWidth = Data.getFieldWidth();
    double fieldHeight = Data.getFieldHeight();
    double field = Data.getField();
    double stationWidth = Data.getStationWidth();
    double stationHeight = Data.getStationHeight();

    boolean enableOriginalGame = Data.isEnableOriginalGame();

    List<Point> pointStations = new LinkedList<>();
    List<Integer> angleList = new LinkedList<>();

    public FixStation(){
        //pointStations.add(new Point(50,50));
        //pointStations.add(new Point(150,150));
        pointStations.add(new Point(650,450));
        pointStations.add(new Point(650,650));
        pointStations.add(new Point(550,750));
        pointStations.add(new Point(50,650));

        pointStations.add(new Point(1350,50));
        pointStations.add(new Point(1250,150));
        pointStations.add(new Point(750,450));
        pointStations.add(new Point(750,650));
        pointStations.add(new Point(850,750));
        pointStations.add(new Point(1350,650));

        //angleList.add(-135);
        //angleList.add(-45);
        angleList.add(-135);
        angleList.add(-270);
        angleList.add(-90);
        angleList.add(-180);

        angleList.add(0);
        angleList.add(-45);
        angleList.add(-135);
        angleList.add(-270);
        angleList.add(-90);
        angleList.add(-180);
    }

    public Group getObstacle(){
        List<Integer> location = new LinkedList<>();
        location.add(Integer.MAX_VALUE);

        for (int i = 0; i < pointStations.size(); i++) {
            int angle = angleList.get(i);
            Point pointOfMiddleField = pointStations.get(i);

            List<Point> points = getAllPointsOfRect(pointOfMiddleField.getX(),
                    pointOfMiddleField.getY(), angle);

            angle *= -1;

            new Rectangle(points.get(0), points.get(1), points.get(2), points.get(3), angle);
        }

        return new ObstacleDrawer().getObstacles();
    }

    private List<Point> getAllPointsOfRect(double x, double y, double angle){
        double x1diff = (stationHeight / 2) * Math.cos(Math.toRadians(angle)) - (stationWidth / 2) * Math.sin(Math.toRadians(angle));
        double y1diff = (stationHeight / 2) * Math.sin(Math.toRadians(angle)) + (stationWidth / 2) * Math.cos(Math.toRadians(angle));
        double x1 = x - x1diff;
        double y1= y - y1diff;

        double x2diff = (stationHeight / 2)  * Math.cos(Math.toRadians(angle)) - (stationWidth / 2) * -1 * Math.sin(Math.toRadians(angle));
        double y2diff = (stationHeight / 2)  * Math.sin(Math.toRadians(angle)) + (stationWidth / 2) * -1 * Math.cos(Math.toRadians(angle));
        double x2 = x - x2diff;
        double y2= y - y2diff;


        double x3diff = (stationHeight / 2) * -1 * Math.cos(Math.toRadians(angle)) - (stationWidth / 2)  * Math.sin(Math.toRadians(angle));
        double y3diff = (stationHeight / 2) * -1 * Math.sin(Math.toRadians(angle)) + (stationWidth / 2)  * Math.cos(Math.toRadians(angle));
        double x3 = x - x3diff;
        double y3= y - y3diff;

        double x4diff = (stationHeight / 2) * -1 * Math.cos(Math.toRadians(angle)) - (stationWidth / 2) * -1 * Math.sin(Math.toRadians(angle));
        double y4diff = (stationHeight / 2) * -1 * Math.sin(Math.toRadians(angle)) + (stationWidth / 2) * -1 * Math.cos(Math.toRadians(angle));
        double x4 = x - x4diff;
        double y4= y - y4diff;

        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x3, y3);
        Point p4 = new Point(x4, y4);

        return List.of(p1, p2, p3, p4);
    }
}
