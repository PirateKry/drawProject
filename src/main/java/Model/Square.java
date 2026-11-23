package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;

public class Square extends Pixel {

    //вызов конструктора pixel
    public Square(int x, int y, int size) {
        super(x,y,size);
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext g) {
        applyStrokeStyle(g);
        g.strokeRect(x - size/2.0, y - size/2.0, size, size);}
}
