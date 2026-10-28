package Model;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public class PointTool implements Tool{
    private final DrCanvas canvas;
    private final PixelFactory factory;
    private Color color = Color.BLACK;
    private final int pintSize;

    public PointTool(DrCanvas canvas, PixelFactory factory, int pintSize){
        this.canvas = canvas;
        this.factory = factory;
        this.pintSize = pintSize;
    }

    @Override
    public void onMousePressed(double x, double y){
        canvas.addPixel(factory.createPixel(PixelType.POINT, (int) x, (int) y, pintSize, color));
    }

    @Override
    public void onMouseDragged(double x, double y){
        canvas.addPixel(factory.createPixel(PixelType.POINT, (int) x, (int) y, pintSize, color));
    }

    @Override
    public void onMouseRelesead(double x, double y){

    }

    @Override
    public Cursor getCursor(){
        return Cursor.CROSSHAIR;
    }

    @Override
    public String getName(){
        return "Point";
    }

    @Override public void setColor(Color color) {
        this.color = color;
    }

    @Override public Color getColor() {
        return color;
    }
}
