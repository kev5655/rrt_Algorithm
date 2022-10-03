package gui.drawer;

import javafx.scene.Group;

public class Displayer {

    private final Group group;
    private final Drawable drawable;

    public Displayer(Drawable drawable, Group group){
        this.group = group;
        this.drawable = drawable;
    }


    public void show() {
        group.getChildren().addAll(drawable.draw());
    }

}
