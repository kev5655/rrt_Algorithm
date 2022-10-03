package utils;

import javafx.scene.shape.Line;

public class Converter {

    public static javafx.scene.shape.Line geometryLineToFxLine(gui.geometry.Line line) {
        return new javafx.scene.shape.Line(line.getP1().getX(),
                line.getP1().getY(),
                line.getP2().getX(),
                line.getP2().getY());
    }

}
