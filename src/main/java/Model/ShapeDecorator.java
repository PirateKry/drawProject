package Model;

import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public abstract class ShapeDecorator implements Shape {

    protected final Shape inner;

    protected ShapeDecorator(Shape inner) {
        if (inner == null) {
            throw new IllegalArgumentException("inner не может быть null");
        }
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

    //массив X-координат вершин inner.
    protected double[] getXs() {
        return getXsShifted(0);
    }

    //массив Y-координат вершин inner.
    protected double[] getYs() {
        return getYsShifted(0);
    }

    //массив X-координат вершин inner со смещением dx (для тени).
    protected double[] getXsShifted(double dx) {
        List<double[]> verts = inner.getVertices();
        double[] xs = new double[verts.size()];
        for (int i = 0; i < verts.size(); i++) {
            xs[i] = verts.get(i)[0] + dx;
        }
        return xs;
    }

    //массив Y-координат вершин inner со смещением dy (для тени).
    protected double[] getYsShifted(double dy) {
        List<double[]> verts = inner.getVertices();
        double[] ys = new double[verts.size()];
        for (int i = 0; i < verts.size(); i++) {
            ys[i] = verts.get(i)[1] + dy;
        }
        return ys;
    }

    //кол-во вершин inner.
    protected int getVertexCount() {
        return inner.getVertices().size();
    }

    //публичный геттер — для разворачивания цепочки (сохранение, отладка).
    public Shape getInner() {
        return inner;
    }
}