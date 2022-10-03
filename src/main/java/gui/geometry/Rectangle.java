package gui.geometry;

import java.util.List;

public class Rectangle {

    private Line AB, AC, DB, DC;
    private Point A, B, C, D;
    private Integer angle = null;

    public Rectangle(Line AB , Line AC, Line DB, Line DC, Integer angle){
        this.angle = angle;
        init(AB, AC, DB, DC);

    }

    public Rectangle(Line AB , Line AC, Line DB, Line DC){
        init(AB, AC, DB, DC);
    }

    public Rectangle(Point A, Point B, Point C, Point D, Integer angle){
        Line AB = new Line(A, B);
        Line AC = new Line(A, C);
        Line DB = new Line(D, B);
        Line DC = new Line(D, C);
        this.angle = angle;
        init(AB, AC, DB, DC);
    }

    public Rectangle(Point A, Point B, Point C, Point D){
        Line AB = new Line(A, B);
        Line AC = new Line(A, C);
        Line DB = new Line(D, B);
        Line DC = new Line(D, C);
        init(AB, AC, DB, DC);
    }

    private void init(Line AB , Line AC, Line DB, Line DC){
        this.AB = AB;
        this.AC = AC;
        this.DB = DB;
        this.DC = DC;
        this.A = AB.getP1();
        this.B = AB.getP2();
        this.C = AC.getP2();
        this.D = DB.getP1();
        try {
            isValidRectangle();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void isValidRectangle() throws IllegalAccessException {
        if(! Point.compare(AB.getP1(), AC.getP1())) {
            throw new IllegalAccessException("Punkt A von Linie AB und AC sind nicht die gleichen -> A von AB: " + AB.getP1() + " A von AC: " + AC.getP1());
        }
        else if(! Point.compare(DB.getP1(), DC.getP1())){
            throw new IllegalAccessException("Punkt D von Linie DB und DC sind nicht die gleichen -> D von DB: " + DB.getP1() + " D von DC: " +  DC.getP1());
        }
        else if (! Point.compare(AB.getP2(), DB.getP2())){
            throw new IllegalAccessException("Punkt B von Linie AB und DB sind nicht die gleichen -> B von AB: " + AB.getP2() + " B von DB: " + DB.getP2());
        }
        else if (! Point.compare(AC.getP2(), DC.getP2())){
            throw new IllegalAccessException("Punkt C von Linie AC und DC sind nicht die gleichen -> C von AC: " + AC.getP2() + " B von DB: " + DC.getP2());
        }
    }


    public boolean isPointInRectangle(Point point){
        double bax = B.getX() - A.getX();
        double bay = B.getY() - A.getY();
        double dax = C.getX() - A.getX();
        double day = C.getY() - A.getY();

        if ((point.getX() - A.getX()) * bax + (point.getY() - A.getY()) * bay < 0.0) return false;
        if ((point.getX() - B.getX()) * bax + (point.getY() - B.getY()) * bay > 0.0) return false;
        if ((point.getX() - A.getX()) * dax + (point.getY() - A.getY()) * day < 0.0) return false;
        if ((point.getX() - C.getX()) * dax + (point.getY() - C.getY()) * day > 0.0) return false;

        return true;
    }

    public boolean isLineInRectangle(Line line){
        if(line.isOverlappingALine(AB) || line.isOverlappingALine(AC) || line.isOverlappingALine(DB) || line.isOverlappingALine(DC)){
            return true;
        }
        return false;

    }

    public List<Line> getLines(){
        return List.of(AB, AC, DB, DC);
    }

    public List<Point> getPoints(){
        return List.of(A, B, C, D);
    }

    public List<Point> getP1AndP2() {
        return List.of(A, B);
    }

    public List<Point> getP3AndP4() {
        return List.of(C, D);
    }

    public List<Point> getP2AndP4() { return List.of(B, D); }

    public List<Point> getP1AndP3() { return List.of(A, C); }

    public Integer getAngle() {
        return angle;
    }



}
