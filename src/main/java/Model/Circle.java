package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;

public class Circle extends Pixel {

    //вызов конструктора pixel
    public Circle(int x, int y, int size) {
        super(x,y,size);
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setLineWidth(3);
        g.strokeOval(x - size/2.0, y - size/2.0, size, size);
    }

}
