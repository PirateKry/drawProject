package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FillDecorator extends ShapeDecorator {

    private final Color fillColor;

    public FillDecorator(Shape inner, Color fillColor){
        super(inner);
        this.fillColor = fillColor;
    }

    public Color getFillColor(){
        return fillColor;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(fillColor);
        g.fillPolygon(getXs(), getYs(), getVertexCount());
        inner.draw(g);
    }
}