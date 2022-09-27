package dataClass;

import geometry.Rectangle;
import geometry.Point;

import java.util.LinkedList;
import java.util.List;

public class Obstacles {

    public static List<Rectangle> rectangleList = new LinkedList<>();

    public static boolean isPointInObstacle(Point point){
        for(Rectangle rectangle : rectangleList){
            if(rectangle.isPointInRectangle(point)){
                return true;
            }
        }
        return false;
    }

    public static void addObstacle(Rectangle rectangle){
        rectangleList.add(rectangle);
    }

    public static List<Rectangle> getObstacleList() {
        return rectangleList;
    }

}
