import gui.storage.Data;
import gui.storage.Obstacles;
import gui.drawer.Displayer;
import gui.drawer.StationDrawer;
import gui.drawer.GridDrawer;
import gui.geometry.Point;
import gui.obstaclePattern.RandomStation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import rrt.Node;
import rrt.Rrt;

import java.util.LinkedList;
import java.util.List;

public class MyRRT extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double fieldWidth = Data.getFieldWidth();
    double fieldHeight = Data.getFieldHeight();
    double field = Data.getFieldSize();

    Group allNodes;

    @Override
    public void start(Stage primaryStage) throws Exception {

        allNodes = new Group();

        Scene scene = new Scene(allNodes,  fieldWidth + 300,  fieldHeight + 300);

        //scene.setOnMouseMoved(e -> {
        //    DataClass.Point point = new DataClass.Point(e.getX(), e.getY());
        //    boolean isOver = DataClass.Obstacle.isPointInObstacle(point);
        //    System.out.println(e.getX() + " " + e.getY() + "  " + isOver);
        //});

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        Node startNode = new Node(new rrt.Point(50, 750));
        Node goalNode = new Node(new rrt.Point(1350, 750));
        Rrt rrtStar = new Rrt(startNode, goalNode, 4, 10, 0, 10000, allNodes);

        Thread pathPlaning = new Thread(()-> {
            rrtStar.findPath();
            //Platform.runLater(rrtStar::findPath);
        });

        Platform.runLater(() -> {
            new Displayer(new GridDrawer(fieldWidth, fieldHeight, field), allNodes).show();

            //new Displayer(new ObstacleDrawer(new FixStation()), allNodes).show();
            new Displayer(new StationDrawer(new RandomStation()), allNodes).show();

            //testLineInObstacle();
            pathPlaning.start();
        });
    }

    private void testInObstacel() {
        int gird = 1;
        for (int x = 0; x < fieldWidth; x+=gird) {
            for (int y = 0; y < fieldHeight; y+=gird) {
                Point point = new Point((double)x, (double)y);
                boolean isOnObstacle = Obstacles.isPointInObstacle(point);
                if(isOnObstacle){
                    javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(x, y, gird, gird);
                    r.setFill(Color.rgb(0,0,0,0.2));
                    allNodes.getChildren().add(r);
                }
            }
        }
    }

    public void testLineInObstacle(){
        int grid = 10;
        for (int x = 0; x < fieldWidth; x += grid) {
            for (int y = 0; y < fieldHeight; y += grid) {
                gui.geometry.Line line = new gui.geometry.Line(new Point(x, y), new Point(x +  100, y));
                if (! Obstacles.isLineInObstacle(line)) {
                    allNodes.getChildren().add(new Line(line.getP1().getX(), line.getP1().getY(), line.getP2().getX(), line.getP2().getY()));
                }
            }
        }

        for (int x = 0; x < fieldHeight; x += grid) {
            for (int y = 0; y < fieldWidth; y += grid) {
                gui.geometry.Line line = new gui.geometry.Line(new Point(x, y), new Point(x +  100, y));
                if (! Obstacles.isLineInObstacle(line)) {
                    allNodes.getChildren().add(new Line(line.getP2().getY(), line.getP2().getX(), line.getP1().getY(), line.getP1().getX()));
                }
            }
        }


    }

    private void showGrid() {
        List<Line> lines = new LinkedList<>();
        for (double x = 0; x <= fieldWidth; x = x + field) {
            lines.add(new Line(x, 0, x, fieldHeight));
        }
        for (double y = 0; y <= fieldHeight; y = y + field) {
            lines.add(new Line(0, y, fieldWidth, y));
        }

        allNodes.getChildren().addAll(lines);
    }
}

