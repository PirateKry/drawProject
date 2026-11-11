package Model;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public class SquareTool implements Tool {
    private final DrCanvas canvas;
    private final PixelFactory factory;

    private int startX;
    private int startY;
    private Square current;
    private Color color = Color.BLACK;
    private int br_size = 10;

    public SquareTool(DrCanvas canvas, PixelFactory factory) {
        this.canvas = canvas;
        this.factory = factory;
    }

    @Override
    public void onMousePressed(double x, double y) {
        drowSquareTool(x,y);
    }

    @Override
    public void onMouseDragged(double x, double y) {
        drowSquareTool(x,y);
    }

    @Override
    public void onMouseRelesead(double x, double y) {
    }

    @Override
    public Cursor getCursor() {
        return Cursor.CROSSHAIR;
    }

    @Override
    public String getName() {
        return "Square";
    }

    @Override public void setColor(Color color) {
        this.color = color;
    }

    @Override public Color getColor() {
        return color;
    }

    public void drowSquareTool(double x, double y){
        canvas.addPixel(factory.createPixel(PixelType.SQUARE, (int) x, (int) y, br_size, color));
    }
}