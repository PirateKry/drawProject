package Model;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PolygonTool implements Tool {
    private final DrCanvas canvas;
    private final List<double[]> currentVertices = new ArrayList<>();
    private final Function<Polygon, Shape> decorator;
    private Color color = Color.BLACK;

    public PolygonTool(DrCanvas canvas) {
        this(canvas, p -> p);   // без декораторов по умолчанию
    }

    public PolygonTool(DrCanvas canvas, Function<Polygon, Shape> decorator) {
        this.canvas = canvas;
        this.decorator = decorator;
    }

    @Override
    public void onMousePressed(double x, double y) {
        currentVertices.add(new double[]{x, y});
        canvas.repaint();
    }

    @Override public void onMouseDragged(double x, double y) { }
    @Override public void onMouseRelesead(double x, double y) { }

    public void finish() {
        if (currentVertices.size() >= 3) {
            Polygon p = new Polygon(new ArrayList<>(currentVertices));
            p.setStrokeColor(color);
            canvas.addShape(decorator.apply(p));
        }
        currentVertices.clear();
    }

    @Override public Cursor getCursor() { return Cursor.CROSSHAIR; }
    @Override public String getName()   { return "Polygon"; }
    @Override public void setColor(Color c) { this.color = c; }
    @Override public Color getColor()       { return color; }
}