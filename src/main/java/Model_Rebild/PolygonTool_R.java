package Model_Rebild;

import Model.Polygon;
import Model.Shape;
import Model.Tool;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PolygonTool_R implements Tool {

    private final DrCanvas_R canvas;
    private final List<double[]> currentVertices = new ArrayList<>();
    private final Function<Polygon, Shape> decorator;
    private Color color = Color.BLACK;

    //без декораторов по умолчанию.
    public PolygonTool_R(DrCanvas_R canvas) {
        this(canvas, p -> p);
    }

    //с декоратором: p -> decoratePolygon(p) из контроллера.
    public PolygonTool_R(DrCanvas_R canvas, Function<Polygon, Shape> decorator) {
        this.canvas = canvas;
        this.decorator = decorator;
    }

    @Override
    public void onMousePressed(double x, double y) {
        currentVertices.add(new double[]{x, y});
        canvas.repaint();
    }

    @Override public void onMouseDragged(double x, double y) { }
    @Override public void onMouseReleased(double x, double y) { }

    //вызывается по двойному клику из DrCanvas_R.
    public void finish() {
        if (currentVertices.size() >= 3) {
            Polygon p = new Polygon(new ArrayList<>(currentVertices));
            p.setStrokeColor(color);
            p.setStrokeWidth(2.0);
            Shape decorated = decorator.apply(p);
            canvas.addShape(decorated);
        }
        currentVertices.clear();
        canvas.repaint();
    }

    @Override public Cursor getCursor(){
        return Cursor.CROSSHAIR;
    }
    @Override public String getName(){
        return "Polygon";
    }
    @Override public void setColor(Color c){
        this.color = c;
    }
    @Override public Color getColor(){
        return color;
    }
}