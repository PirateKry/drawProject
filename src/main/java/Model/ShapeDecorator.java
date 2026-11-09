package Model;

import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public abstract class ShapeDecorator implements Shape {
    protected final Shape inner;

    protected ShapeDecorator(Shape inner) {
        this.inner = inner;
    }

    @Override
    public void draw(GraphicsContext g) {
        inner.draw(g);
    }

    @Override
    public List<double[]> getVertices() {
        return inner.getVertices();
    }

}