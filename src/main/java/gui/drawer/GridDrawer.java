package gui.drawer;

import javafx.scene.Node;
import javafx.scene.shape.Line;

import java.util.LinkedList;
import java.util.List;

public class GridDrawer implements Drawable {
    
    private final double fieldWidth;
    private final double fieldHeight;
    private final double fieldSize;
    private List<Node> grid = new LinkedList<>();
    
    public GridDrawer(double fieldWidth, double fieldHeight, double fieldSize){
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.fieldSize = fieldSize;
        init();
    }

    public void init() {
        // Vertikale Linien
        for (double x = 0; x <= fieldWidth; x = x + fieldSize) {
            grid.add(new Line(x, 0, x, fieldHeight));
        }
        // Horizontale Linien
        for (double y = 0; y <= fieldHeight; y = y + fieldSize) {
            grid.add(new Line(0, y, fieldWidth, y));
        }
    }


    @Override
    public List<Node> draw() {
        return grid;
    }
}
