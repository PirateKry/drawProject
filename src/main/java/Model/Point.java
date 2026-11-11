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
        double half = size / 2.0;
        double[] xs = { x, x - half, x + half };
        double[] ys = { y - half, y + half, y + half };

        applyStrokeStyle(g);
        g.strokePolygon(xs, ys, 3);
    }
}
