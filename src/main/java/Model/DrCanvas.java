package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class DrCanvas extends javafx.scene.canvas.Canvas {

    private List<Pixel> pixels = new ArrayList<>();
    private Tool curTool;

    public DrCanvas(double width, double height) {
        super(width, height);

        setOnMousePressed(e -> { if (curTool != null) curTool.onMousePressed(e.getX(), e.getY()); repaint(); });
        setOnMouseDragged(e -> { if (curTool != null) curTool.onMouseDragged(e.getX(), e.getY()); repaint(); });
        setOnMouseReleased(e -> { if (curTool != null) curTool.onMouseRelesead(e.getX(), e.getY()); repaint(); });
    }

    public void setCurTool(Tool t){
        this.curTool = t;
    }

    public void addPixel(Pixel p){
        pixels.add(p);
    }

    public void repaint(){
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0,0, getWidth(), getHeight());
        for(Pixel p : pixels) {
            p.draw(gc);
        }
    }

    //доступ к последней добавленной фигуре
    public Pixel getLastPixel() {
        return pixels.isEmpty() ? null : pixels.get(pixels.size() - 1);
    }

    public void clear() {
        pixels.clear();
        repaint();
    }

    public DrCanvas() {
        this(800, 540);   // размеры по умолчанию; FXML может их переопределить
    }
}
