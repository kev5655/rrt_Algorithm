package gui.drawer;

import gui.dataClass.Obstacles;
import gui.geometry.Rectangle;
import gui.geometry.Point;
import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javafx.scene.text.Text;


import java.util.LinkedList;
import java.util.List;


public class ObstacleDrawer {

    private Group obstacles = new Group();
    private List<Color> rectangleCornerColors = new LinkedList<>(List.of(Color.RED, Color.BLUE, Color.GREEN, Color.BROWN));


    public ObstacleDrawer(){
        List<Rectangle> rectangleList = Obstacles.getObstacleList();
        List<Line> lines = new LinkedList<>();
        List<Line> points = new LinkedList<>();
        List<Text> texts = new LinkedList<>();

        for(Rectangle rectangle : rectangleList){
            for(gui.geometry.Line line :  rectangle.getLines()){
                double x1 = line.getP1().getX();
                double y1 = line.getP1().getY();
                double x2 = line.getP2().getX();
                double y2 = line.getP2().getY();
                lines.add(getFxLine(x1, y1, x2, y2));
            }

            if(rectangle.getAngle() != null){
                // Winkel der Station
                texts.add(getFxText(rectangle.getPoints(), rectangle.getAngle().toString()));

                //IN Label der Station
                texts.add(getFxText(rectangle.getP3AndP4(), "IN"));

                //OUT Label der Station
                texts.add(getFxText(rectangle.getP1AndP2(), "OUT"));
            }


            // Mark all Corner of Station
            for (int i = 0; i < rectangle.getPoints().size(); i++) {
                points.add(getCornerMarker(rectangle.getPoints().get(i), rectangleCornerColors.get(i)));

            }

            texts.add(getCornerText(rectangle.getPoints().get(0), "p1 A"));
            texts.add(getCornerText(rectangle.getPoints().get(1), "p2 B"));
            texts.add(getCornerText(rectangle.getPoints().get(2), "p3 C"));
            texts.add(getCornerText(rectangle.getPoints().get(3), "p4 D"));

        }

        obstacles.getChildren().addAll(lines);
        obstacles.getChildren().addAll(points);
        obstacles.getChildren().addAll(texts);
    }

    private Line getFxLine(double x1, double y1, double x2, double y2){
        Line fxline = new Line(x1, y1, x2, y2);
        fxline.setStroke(Color.BLACK);
        return fxline;
    }

    public Text getFxText(List<Point> points, String text){
        Point middleOfStation = Point.getMiddlePointOfPointList(points);
        return generateAndLayoutingText(middleOfStation.getX(), middleOfStation.getY(), text);
    }

    private Text generateAndLayoutingText(double x, double y, String s){
        Text text = new Text(s);
        double width = text.getLayoutBounds().getWidth();
        double height = text.getLayoutBounds().getHeight();
        text.setX(x - width / 2);
        text.setY(y + (height / 3));
        text.setScaleX(0.8);
        text.setScaleY(0.8);

        return text;
    }

    public Line getCornerMarker(Point p, Color color){
        Line fxPoint = new Line(p.getX(), p.getY(), p.getX(), p.getY());
        fxPoint.setStroke(color);
        fxPoint.setStrokeWidth(4);
        return fxPoint;
    }
    public Text getCornerText(Point p, String s){
        return new Text(p.getX(), p.getY(), s);
    }

    public Group getObstacles() {
        return obstacles;
    }
}
