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

    public SquareTool(DrCanvas canvas, PixelFactory factory) {
        this.canvas = canvas;
        this.factory = factory;
    }

    @Override
    public void onMousePressed(double x, double y) {
        startX = (int) x;
        startY = (int) y;
        current = (Square) factory.createPixel(PixelType.SQUARE, startX, startY, 0, color);
        canvas.addPixel(current);
    }

    @Override
    public void onMouseDragged(double x, double y) {
        if (current == null) return;
        int dx = Math.abs((int) x - startX);
        int dy = Math.abs((int) y - startY);
        int side = Math.max(dx, dy);            // квадрат — по большей стороне
        current.setSize(side);
        current.setPosition(startX, startY);
        canvas.repaint();
    }

    @Override
    public void onMouseRelesead(double x, double y) {
        if (current == null) return;
        int dx = Math.abs((int) x - startX);
        int dy = Math.abs((int) y - startY);
        int side = Math.max(dx, dy);
        current.setSize(side < 2 ? 2 : side);
        canvas.repaint();
        current = null;
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
}