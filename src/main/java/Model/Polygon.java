package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Polygon implements Shape {

    private final List<double[]> vertices;   // ≥ 3 вершины
    private Color strokeColor = Color.BLACK;
    private double strokeWidth = 2.0;

    public Polygon(List<double[]> vertices) {
        if (vertices == null || vertices.size() < 3) {
            throw new IllegalArgumentException(
                    "Полигон должен иметь минимум 3 вершины, получено: "
                            + (vertices == null ? 0 : vertices.size()));
        }

        this.vertices = vertices.stream()
                .map(v -> new double[]{v[0], v[1]})
                .toList();
    }

    //свойства контура
    public void setStrokeColor(Color c) { this.strokeColor = c; }
    public void setStrokeWidth(double w) { this.strokeWidth = w; }

    public Color getStrokeColor() { return strokeColor; }
    public double getStrokeWidth() { return strokeWidth; }

    //вершины
    @Override
    public List<double[]> getVertices() {
        return vertices;
    }

    //отрисовка
    @Override
    public void draw(GraphicsContext g) {
        int n = vertices.size();
        double[] xs = new double[n];
        double[] ys = new double[n];
        for (int i = 0; i < n; i++) {
            xs[i] = vertices.get(i)[0];
            ys[i] = vertices.get(i)[1];
        }
        g.setStroke(strokeColor);
        g.setLineWidth(strokeWidth);
        g.strokePolygon(xs, ys, n);
    }
}