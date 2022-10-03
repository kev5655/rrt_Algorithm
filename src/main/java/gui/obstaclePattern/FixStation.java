package gui.obstaclePattern;

import gui.storage.Obstacles;
import gui.geometry.Point;
import gui.geometry.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class FixStation extends StationPatternUtils implements StationPattern{

    private final List<Point> pointStations = new LinkedList<>();
    private final List<Integer> angleList = new LinkedList<>();

    public FixStation(){
        pointStations.add(new Point(50,50));
        pointStations.add(new Point(150,150));
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

        angleList.add(-135);
        angleList.add(-45);
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


    @Override
    public List<Rectangle> getRectangleList() {
        List<Rectangle> rectangleList = new LinkedList<>();
        for (int i = 0; i < pointStations.size(); i++) {
            int angle = angleList.get(i);
            Point pointOfMiddleField = pointStations.get(i);

            List<Point> points = super.getAllPointsOfRect(pointOfMiddleField.getX(),
                    pointOfMiddleField.getY(), angle);

            angle = Math.abs(angle);

            Rectangle rectangle = new Rectangle(points.get(0),
                    points.get(1),
                    points.get(2),
                    points.get(3),
                    angle);
            Obstacles.addObstacle(rectangle);
            rectangleList.add(rectangle);
        }
        return rectangleList;
    }

}
