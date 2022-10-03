package gui.drawer;

import gui.geometry.Rectangle;
import gui.geometry.Point;
import gui.obstaclePattern.StationPattern;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javafx.scene.text.Text;
import utils.Converter;


import java.util.LinkedList;
import java.util.List;


public class StationDrawer implements Drawable {

    private final List<Rectangle> rectangleList;

    public StationDrawer(StationPattern stationPattern){
        this.rectangleList = stationPattern.getRectangleList();

    }

    @Override
    public List<Node> draw() {
        List<Node> obstaclesList = new LinkedList<>();
        List<Line> lines = new LinkedList<>();
        List<Line> points = new LinkedList<>();
        List<Text> texts = new LinkedList<>();

        for(Rectangle rectangle : rectangleList){
            for(gui.geometry.Line line :  rectangle.getLines()){
                lines.add(Converter.geometryLineToFxLine(line));
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
                List<Color> rectangleCornerColors = new LinkedList<>(List.of(Color.RED, Color.BLUE, Color.GREEN, Color.BROWN));
                points.add(getCornerMarker(rectangle.getPoints().get(i), rectangleCornerColors.get(i)));

            }

            texts.add(getCornerText(rectangle.getPoints().get(0), "p1 A"));
            texts.add(getCornerText(rectangle.getPoints().get(1), "p2 B"));
            texts.add(getCornerText(rectangle.getPoints().get(2), "p3 C"));
            texts.add(getCornerText(rectangle.getPoints().get(3), "p4 D"));

        }
        obstaclesList.addAll(lines);
        obstaclesList.addAll(points);
        obstaclesList.addAll(texts);

        return obstaclesList;
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

}
