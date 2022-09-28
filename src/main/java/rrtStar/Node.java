package rrtStar;


import java.util.LinkedList;
import java.util.List;

public class Node {

    private Point point;
    private Node beforNode;
    private List<Node> afterNode = new LinkedList<>();
    double coast;

    public Node(Point point){
        this.point = point;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public Point getPoint() {
        return point;
    }

    public void setX(double x){
        point.x = x;
    }

    public void setY(double y){
        point.y = y;
    }

    public double getX(){
        return point.x;
    }

    public double getY(){
        return point.y;
    }

    public Node getBeforNode() {
        return beforNode;
    }

    public List<Node> getAfterNode() {
        return afterNode;
    }

    public void setBeforNode(Node beforNode) {
        this.beforNode = beforNode;
    }

    public void addAfterNode(Node afterNode) {
        this.afterNode.add(afterNode);
    }

    @Override
    public String toString() {
        return "Node{" +
                "point=" + point +
                ", coast=" + coast +
                '}';
    }
}
