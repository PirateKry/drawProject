package Model_Rebild;

import Model.PolygonTool;
import Model.Shape;
import Model.Tool;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class DrCanvas_R extends javafx.scene.canvas.Canvas {
    private final List<Pixel_R> raster  = new ArrayList<>();//растр
    private final List<Shape>   vector = new ArrayList<>();//вектор
    private Tool curTool;

    public DrCanvas_R() {
        super(800, 500);   // размеры по умолчанию
        initHandlers();
    }

    //конструктор с размерами
    public DrCanvas_R(double w, double h) {
        super(w, h);
        initHandlers();
    }

    private void initHandlers() {
        setOnMousePressed(e -> { if (curTool != null) curTool.onMousePressed(e.getX(), e.getY()); repaint(); });
        setOnMouseDragged(e -> { if (curTool != null) curTool.onMouseDragged(e.getX(), e.getY()); repaint(); });
        setOnMouseReleased(e -> { if (curTool != null) curTool.onMouseReleased(e.getX(), e.getY()); repaint(); });
        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && curTool instanceof PolygonTool_R pt) {
                pt.finish();
            }
        });
    }

    public void setCurTool(Tool t){
        this.curTool = t;
    }
    public void addPixel(Pixel_R p){
        raster.add(p);
    }
    public void addShape(Shape s){
        vector.add(s);
    }

    public void repaint() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (Pixel_R p : raster) p.draw(gc);
        for (Shape s : vector)  s.draw(gc);
    }

    public void clear() {
        raster.clear();
        vector.clear();
        repaint();
    }
}