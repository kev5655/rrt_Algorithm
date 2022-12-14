package rrt;


import gui.storage.Obstacles;
import gui.geometry.Line;
import javafx.application.Platform;
import javafx.scene.Group;
import utils.Converter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Rrt {

    private double maxDistance; // For expanding Random Tree
    private double searchRadius; // For finding Neighborhoods only for RRT-Star
    private double goalOffset;
    private int maxIteration;
    private int actuallyIteration = 0;

    private Node startNode;
    private Node goalNode;
    private Node actuallyNode;
    private List<Node> allNodes = new LinkedList<>();
    private Random rand = new Random();

    private Group group;

    public Rrt(double maxDistance){
        this.maxDistance = maxDistance;
    }

    public Rrt(Node startNode, Node goalNode, double goalOffset, double maxDistance, double searchRadius, int maxIteration, Group group){
        this.startNode = startNode;
        this.startNode.setCoast(0);
        this.actuallyNode = startNode;
        this.goalNode = goalNode;
        this.group = group;

        this.goalOffset = goalOffset;
        this.maxDistance = maxDistance;
        this.searchRadius = searchRadius;
        this.maxIteration = maxIteration;
        allNodes.add(this.startNode);
    }

    public void findPath(){
        while (isNotInGoal(actuallyNode)){
            actuallyIteration++;
            Point randomPoint = getRandomPoint();

            Node [] connectedNodes = getTwoConnectedNodes(randomPoint);

            Node nearestNode = connectedNodes[0];
            Node newNode = connectedNodes[1];

            if(isObstacleBetweenNodes(nearestNode, newNode)){
                continue;
            }
            actuallyNode = newNode;
            allNodes.add(actuallyNode);

            updateGui(new Line(nearestNode, newNode));

//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }





    public boolean isNotInGoal(Node actuallyNode){
        // Bricht die While loop ab
        if(maxIteration <= actuallyIteration){
            System.out.println("\nKeine l??sung gefunden iterationen: " + actuallyIteration);
            return false;
        }
        // Bricht den While loop ab
        if(actuallyNode.getX() < goalNode.getX() + goalOffset && actuallyNode.getX() > goalNode.getX() - goalOffset){
            if(actuallyNode.getY() < goalNode.getY() + goalOffset && actuallyNode.getY() > goalNode.getY() - goalOffset){
                for(Node node : allNodes){
                    System.out.println(node);
                }
                System.out.println("\nL??sung gefunden iteration: " + actuallyIteration);
                return false;
            }
        }
        return true;
    }

    public Point getRandomPoint(){
        return new Point(
                rand.nextInt(Env.getMaxX())  + Env.getMinX(),
                rand.nextInt(Env.getMaxY())  + Env.getMinY());
    }

    public Node[] getTwoConnectedNodes(Point point){
        double min = 1000000;
        int indexOfNodeWithTheSmallesDistance = Integer.MAX_VALUE;
        for (int i = 0; i < allNodes.size(); i++) {
            double distance = Math.hypot(Math.abs(allNodes.get(i).getX() - point.x), Math.abs(allNodes.get(i).getY() - point.y));
            if(distance < min){
                min = distance;
                indexOfNodeWithTheSmallesDistance = i;
            }

        }
        if(indexOfNodeWithTheSmallesDistance == 1000000){
            throw new RuntimeException("allNodes ist 0 oder es git keine kleinere Distanz als Double.MAX_VALUE");
        }

        Node nearestNode = allNodes.get(indexOfNodeWithTheSmallesDistance);
        Node newNode = new Node(point);

        // Update x and y if distanz bigger than maxDistance
        if(min >= this.maxDistance){
            min = this.maxDistance;
            point = generateNewPoint(nearestNode, newNode);
            newNode = new Node(point);
        }

        Node [] connectetNodes = new Node[]{nearestNode, newNode};

        //Linking the Tree
        newNode.setBeforNode(nearestNode);
        nearestNode.addAfterNode(newNode);

        newNode.setCoast(nearestNode.getCoast() + min);

        return connectetNodes;
    }

    public Point generateNewPoint(Node nearestNode, Node newNode){
        //Node newNodeToZero = new Node(new Point())
        double xAbsolut =  newNode.getX() - nearestNode.getX();
        double yAbsolut =  newNode.getY() - nearestNode.getY();
        double z = this.maxDistance;
        double alpha;

        alpha = Math.toDegrees(Math.atan2(xAbsolut, yAbsolut));

        double x = z * Math.sin(Math.toRadians(alpha));
        double y = z * Math.cos(Math.toRadians(alpha));

        x = nearestNode.getX() + x;
        y = nearestNode.getY() + y;
        return new Point(x, y);
    }

    private boolean isObstacleBetweenNodes(Node nearestNode, Node actuallyNode) {
        Line line = new Line(nearestNode.getPoint(), actuallyNode.getPoint());
        return Obstacles.isLineInObstacle(line);
    }

    private void updateGui(Line line) {
        Platform.runLater(() -> {
            this.group.getChildren().add(Converter.geometryLineToFxLine(line));
        });
    }

}