public class SinCos {

    public static void main(String[] args) {


        double x = 4;
        double y = 1;
        double angle = 45;

        double x1 = x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle));
        double y1 = x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle));


        System.out.println(x1 + " " + y1);


    }
}
