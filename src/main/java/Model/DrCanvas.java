package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class DrCanvas extends javafx.scene.canvas.Canvas {

    private final List<Shape> shapes = new ArrayList<>();   // было List<Pixel>
    private Tool curTool;

    public DrCanvas(double width, double height) {
        super(width, height);

        setOnMousePressed(e -> { if (curTool != null) curTool.onMousePressed(e.getX(), e.getY()); repaint(); });
        setOnMouseDragged(e -> { if (curTool != null) curTool.onMouseDragged(e.getX(), e.getY()); repaint(); });
        setOnMouseReleased(e -> { if (curTool != null) curTool.onMouseRelesead(e.getX(), e.getY()); repaint(); });
        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && curTool instanceof PolygonTool pt) {
                pt.finish();
                repaint();
            }
        });
    }

    public void setCurTool(Tool t) { this.curTool = t; }

    public void addShape(Shape s) { shapes.add(s); }
    public void addPixel(Pixel p) { shapes.add(p); }   // совместимость с растровыми инструментами

    public void repaint() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (Shape s : shapes) s.draw(gc);
    }

    public Shape getLastShape() {
        return shapes.isEmpty() ? null : shapes.get(shapes.size() - 1);
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

    public DrCanvas() { this(800, 540); }
}