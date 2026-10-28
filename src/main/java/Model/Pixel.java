package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;

abstract class Pixel {
    protected int x;
    protected int y;
    protected int size;
    protected Color color = Color.BLACK;

    public Pixel(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public abstract void draw(GraphicsContext g);
}
