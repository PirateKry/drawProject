package Model_Rebild;

import Model.PixelType;
import Model.Tool;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public abstract class AbstractTool_R implements Tool{
    protected final DrCanvas_R canvas;
    protected final PixelFactory_R factory;
    protected Color color = Color.BLACK;

    protected AbstractTool_R(DrCanvas_R canvas, PixelFactory_R factory) {
        this.canvas = canvas;
        this.factory = factory;
    }

    protected abstract double[] buildArgs();

    protected abstract PixelType getType();

    @Override
    public void onMousePressed(double x, double y)  { draw(x, y); }
    @Override
    public void onMouseDragged(double x, double y)  { draw(x, y); }
    @Override
    public void onMouseReleased(double x, double y) { }

    private void draw(double x, double y) {
        Pixel_R p = factory.createPixel(getType(), (int) x, (int) y, buildArgs());
        p.setColor(color);
        canvas.addPixel(p);
    }

    @Override public Cursor getCursor(){
        return Cursor.CROSSHAIR;
    }
    @Override public void setColor(Color c){
        this.color = c;
    }
    @Override public Color getColor(){
        return color;
    }

}
