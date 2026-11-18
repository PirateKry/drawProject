package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public interface Shape {
    List<double[]> getVertices();
    void draw(GraphicsContext g);
}
