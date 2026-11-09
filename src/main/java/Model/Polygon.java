package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Polygon extends Pixel{
    private final List<double[]> vertices;
    private Color fillColor = null;
    private Color strokeColor = null;
    private double strokeWidth = 2;
    private Color shadowColor = null;
    private double shadowOffSetX = 0;
    private double shadowOffSetY = 0;

    public Polygon(List<double[]> vertices){
        super((int) vertices.get(0)[0],(int)vertices.get(0)[1],0);
        if(vertices.size() < 3)
        {
            throw new IllegalArgumentException("Поликон должен иметь хотябы 3 точки");
        }
        this.vertices = List.copyOf(vertices);
    }

    public void setFillColor(Color c) {this.fillColor = c;}
    public void setStrokeColor(Color c) {this.strokeColor = c;}
    public void setStrokeWidth(double w) {this.strokeWidth = w;}
    public void setShadow(Color c, double dx, double dy){
        this.shadowColor = c;
        this.shadowOffSetX = dx;
        this.shadowOffSetY = dy;
    }

    @Override
    public void draw(GraphicsContext g){
        double[] xs = new double[vertices.size()];
        double[] ys = new double[vertices.size()];
        for(int i = 0;i < vertices.size(); i++)
        {
            xs[i] = vertices.get(i)[0];
            ys[i] = vertices.get(i)[1];
        }
        g.strokePolygon(xs,ys,vertices.size());//базовая отрисовка, ток контур
    }

    public List<double[]> getVertices() {
        return vertices;
    }
}
