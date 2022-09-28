package rrtStar;

public class Env {

    protected static int [] xRange = new int []{0,1400};
    protected static int [] yRange = new int []{0,800};

    public static int[] getxRange() {
        return xRange;
    }

    public static int[] getyRange() {
        return yRange;
    }

    public static int getMinX(){
        return xRange[0];
    }

    public static int getMaxX(){
        return xRange[1];
    }

    public static int getMinY(){
        return yRange[0];
    }

    public static int getMaxY(){
        return yRange[1];
    }
}
