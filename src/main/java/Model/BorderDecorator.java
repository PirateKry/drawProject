package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BorderDecorator extends ShapeDecorator {

    private final Color strokeColor;
    private final double width;

    public BorderDecorator(Shape inner, Color strokeColor, double width) {
        super(inner);
        this.strokeColor = strokeColor;
        this.width = width;
    }

    public Color getStrokeColor(){
        return strokeColor;
    }
    public double getWidth(){
        return width;
    }

    @Override
    public void draw(GraphicsContext g) {
        inner.draw(g);
        g.setStroke(strokeColor);
        g.setLineWidth(width);
        g.strokePolygon(getXs(), getYs(), getVertexCount());
    }
}