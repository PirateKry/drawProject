package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class BorderDecorator extends ShapeDecorator{
    private final Color strokeColor;
    private final double width;

    public BorderDecorator(Shape inner, Color strokeColor, double width){
        super(inner);
        this.strokeColor = strokeColor;
        this.width = width;
    }

    @Override
    public void draw(GraphicsContext g)
    {
        inner.draw(g);

        List<double[]> verts = inner.getVertices();
        int n = verts.size();
        double[] xs = new double[n];
        double[] ys = new double[n];
        for (int i = 0; i < n; i++) {
            xs[i] = verts.get(i)[0];
            ys[i] = verts.get(i)[1];
        }
        g.setStroke(strokeColor);
        g.setLineWidth(width);
        g.strokePolygon(xs, ys, n);
    }
}
