package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public Color getShadowColor(){
        return shadowColor;
    }
    public double getDx(){
        return dx;
    }
    public double getDy(){
        return dy;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(shadowColor);
        g.fillPolygon(getXsShifted(dx), getYsShifted(dy), getVertexCount());
        inner.draw(g);
    }
}