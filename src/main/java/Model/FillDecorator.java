package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class FillDecorator extends ShapeDecorator{
    private final Color fillColor;

    public FillDecorator(Shape inner, Color fillColor)
    {
        super(inner);
        this.fillColor = fillColor;
    }
    @Override
    public void draw(GraphicsContext g)
    {
        List<double[]> verts = inner.getVertices();
        int n = verts.size();
        double[] xs = new double[n];
        double[] ys = new double[n];

        for (int i = 0; i < n; i++)
        {
            xs[i] = verts.get(i)[0];
            ys[i] = verts.get(i)[1];
        }
        g.setFill(fillColor);
        g.fillPolygon(xs, ys, n);

        inner.draw(g);
    }
}
