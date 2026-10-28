package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;

public class Point extends Pixel {

    //вызов конструктора pixel
    public Point(int x, int y, int size) {
        super(x,y,size);
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        g.fillOval(x, y, size, size);
    }
}
