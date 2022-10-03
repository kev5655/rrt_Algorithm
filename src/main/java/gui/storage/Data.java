package gui.storage;

public class Data {

    private static double fieldWidth = 14000;
    private static double fieldHeight = 8000;
    private static double fieldSize = 1000;
    private static double stationWidth = 700;
    private static double stationHeight = 350;
    private static int numberOfStation = 12;

    private static double resizeFaktor = 10;

    private static boolean enableOriginalGame = true;


    public static double getFieldWidth() {
        return fieldWidth / resizeFaktor;
    }

    public static double getFieldHeight() {
        return fieldHeight / resizeFaktor;
    }

    public static double getFieldSize() {
        return fieldSize / resizeFaktor;
    }

    public static double getStationWidth() {
        return stationWidth / resizeFaktor;
    }

    public static double getStationHeight() {
        return stationHeight / resizeFaktor;
    }

    public static int getNumberOfStation() {
        return numberOfStation;
    }

    public static double getResizeFaktor() {
        return resizeFaktor;
    }

    public static boolean isEnableOriginalGame() {
        return enableOriginalGame;
    }
}
