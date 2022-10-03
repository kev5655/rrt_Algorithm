package gui.obstaclePattern;

import gui.storage.Data;
import gui.geometry.Point;

import java.util.List;

public class StationPatternUtils {

    double stationWidth = Data.getStationWidth();
    double stationHeight = Data.getStationHeight();


    List<Point> getAllPointsOfRect(double x, double y, double angle){
        // 2 Quadrant bie 0 Grad
        double x1 = x - ((stationHeight / 2) * cos(angle) - (stationWidth / 2) * sin(angle));
        double y1= y - ((stationHeight / 2) * sin(angle) + (stationWidth / 2) * cos(angle));
        // 3 Quadrant bei 0 Grad
        double x2 = x - ((stationHeight / 2)  * cos(angle) - (stationWidth / 2) * -1 * sin(angle));
        double y2= y - ((stationHeight / 2)  * sin(angle) + (stationWidth / 2) * -1 * cos(angle));
        // 1 Quadrant bie 0 Grad
        double x3 = x - ((stationHeight / 2) * -1 * cos(angle) - (stationWidth / 2)  * sin(angle));
        double y3= y - ((stationHeight / 2) * -1 * sin(angle) + (stationWidth / 2)  * cos(angle));
        // 4 Quadrant bei 0 Grad
        double x4 = x - ((stationHeight / 2) * -1 * cos(angle) - (stationWidth / 2) * -1 * sin(angle));
        double y4= y - ((stationHeight / 2) * -1 * sin(angle) + (stationWidth / 2) * -1 * cos(angle));

        return List.of(
                new Point(x1, y1),
                new Point(x2, y2),
                new Point(x3, y3),
                new Point(x4, y4));
    }

    private double cos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }

    private double sin(double angle){
        return Math.sin(Math.toRadians(angle));
    }

}
