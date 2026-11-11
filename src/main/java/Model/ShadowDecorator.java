package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class ShadowDecorator extends ShapeDecorator {
    private final Color shadowColor;
    private final double dx;
    private final double dy;

    public ShadowDecorator(Shape inner, Color shadowColor, double dx, double dy) {
        super(inner);
        this.shadowColor = shadowColor;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void draw(GraphicsContext g)
    {
        List<double[]> verts = inner.getVertices();
        int n = verts.size();
        double[] xs = new double[n];
        double[] ys = new double[n];
        for (int i = 0; i < n; i++) {
            xs[i] = verts.get(i)[0] + dx;
            ys[i] = verts.get(i)[1] + dy;
        }
        g.setFill(shadowColor);
        g.fillPolygon(xs, ys, n);
        inner.draw(g);
    }

    @Override
    public List<double[]> getVertices() {
        return inner.getVertices();
    }
}