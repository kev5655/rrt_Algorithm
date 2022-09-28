package gui.obstaclePattern;

import gui.dataClass.Data;
import gui.drawer.ObstacleDrawer;
import gui.geometry.Point;
import gui.geometry.Rectangle;
import javafx.scene.Group;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomStation {

    double fieldWidth = Data.getFieldWidth();
    double fieldHeight = Data.getFieldHeight();
    double field = Data.getField();
    double stationWidth = Data.getStationWidth();
    double stationHeight = Data.getStationHeight();
    int numberOfStation = Data.getNumberOfStation();

    boolean enableOriginalGame = Data.isEnableOriginalGame();


    public Group getObstacle(){
        List<Integer> location = new LinkedList<>();
        List<Integer> blockingLocation = new LinkedList<>();
        location.add(Integer.MAX_VALUE);

        for (int i = 0; i < numberOfStation; i++) {

            int angle = generateAngle();
            Point pointOfMiddleField = generatePointOnRandomField(location);

            List<Point> points = getAllPointsOfRect(pointOfMiddleField.getX(),
                    pointOfMiddleField.getY(), angle);

            angle *= -1;

            new Rectangle(points.get(0), points.get(1), points.get(2), points.get(3), angle);
        }

        return new ObstacleDrawer().getObstacles();
    }

    private int generateAngle(){
        Random rand = new Random();
        int angle = 0;
        switch (rand.nextInt(8)){
            case 0 -> angle = 0;
            case 1 -> angle = -45;
            case 2 -> angle = -90;
            case 3 -> angle = -135;
            case 4 -> angle = -180;
            case 5 -> angle = -225;
            case 6 -> angle = -270;
            case 7 -> angle = -315;
        }
        return angle;
    }

    private Point generatePointOnRandomField(List<Integer> location){
        Random rand = new Random();
        Integer R = null;
        int xR, yR;
        do{
            xR = rand.nextInt((int)(fieldWidth / 100));
            yR = rand.nextInt((int)(fieldHeight / 100));
            if (isInEntryZone(xR, yR)) R = Integer.MAX_VALUE;
            else {
                R = xR + yR;
            }
        }while(location.contains(R));

        location.add(R);

        double x = (xR * field) + (field / 2);
        double y = (yR * field) + (field / 2);

        return new Point(x, y);
    }

    private boolean isInEntryZone(int xR, int yR){
        if(enableOriginalGame){
            if(yR == 7 &&
                    (xR == 0 || xR == 1 || xR == 2 || xR ==11 || xR == 12 || xR == 13)){
                return true;
            }
        }
        return false;
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
