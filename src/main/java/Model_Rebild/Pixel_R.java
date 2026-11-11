package Model_Rebild;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Pixel_R {
    protected int x;
    protected int y;

    protected Color color = Color.BLACK;
    protected double lineWidth = 3.0;

    public Pixel_R(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Color getColor() {return color;}

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setColor(Color color){
        this.color = color;
    }

    protected void applyStrokeStyle(GraphicsContext g) {
        g.setStroke(color);
        g.setLineWidth(lineWidth);
    }

    public abstract void draw(GraphicsContext g);
}
