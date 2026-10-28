package Model;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public class CircleTool implements Tool {
    private final DrCanvas canvas;
    private final PixelFactory factory;

    private int startX;
    private int startY;
    private Circle current;
    private Color color = Color.BLACK;

    public CircleTool(DrCanvas canvas, PixelFactory factory) {
        this.canvas = canvas;
        this.factory = factory;
    }

    @Override
    public void onMousePressed(double x, double y) {
        startX = (int) x;
        startY = (int) y;
        current = (Circle) factory.createPixel(PixelType.CIRCLE, startX, startY, 0, color);
        canvas.addPixel(current);
    }

    @Override
    public void onMouseDragged(double x, double y) {
        if (current == null) return;
        int radius = (int) Math.hypot(x - startX, y - startY);
        current.setSize(radius * 2);
        current.setPosition(startX, startY);
        canvas.repaint();
    }

    @Override
    public void onMouseRelesead(double x, double y) {
        if (current == null) return;
        int radius = (int) Math.hypot(x - startX, y - startY);
        current.setSize(radius * 2);

        if (current.getSize() < 2) {
            current.setSize(2);
        }
        canvas.repaint();
        current = null;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.CROSSHAIR;
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override public void setColor(Color color) {
        this.color = color;
    }

    @Override public Color getColor() {
        return color;
    }
}