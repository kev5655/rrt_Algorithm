package rrt;

public class TestGenerateNewPoint {

    public static void main(String[] args) {
        Node n1 = new Node(new Point(1000,1000));
        Node n2 = new Node(new Point(2000, 2000));
        Point p = new Rrt(500).generateNewPoint(n1, n2);
        System.out.println(p);

        n1 = new Node(new Point(2000, 2000));
        n2 = new Node(new Point(2000,1000));
        p = new Rrt(500).generateNewPoint(n1, n2);
        System.out.println(p);
    }


}
