package gui.obstaclePattern;

import gui.storage.Data;
import gui.storage.Obstacles;
import gui.geometry.Point;
import gui.geometry.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomStation extends StationPatternUtils implements StationPattern{

    double fieldWidth = Data.getFieldWidth();
    double fieldHeight = Data.getFieldHeight();
    double field = Data.getFieldSize();
    int numberOfStation = Data.getNumberOfStation();

    boolean enableOriginalGame = Data.isEnableOriginalGame();


    @Override
    public List<Rectangle> getRectangleList() {
        List<Integer> location = new LinkedList<>();
        List<Rectangle> rectangleList = new LinkedList<>();
        location.add(Integer.MAX_VALUE);

        for (int i = 0; i < numberOfStation; i++) {

            int angle = generateAngle();
            Point pointOfMiddleField = generatePointOnRandomField(location);

            List<Point> points = super.getAllPointsOfRect(pointOfMiddleField.getX(),
                    pointOfMiddleField.getY(), angle);

            angle *= -1;

            Rectangle rectangle = new Rectangle(points.get(0), points.get(1), points.get(2), points.get(3), angle);
            Obstacles.addObstacle(rectangle);
            rectangleList.add(rectangle);
        }
        return rectangleList;
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
}
