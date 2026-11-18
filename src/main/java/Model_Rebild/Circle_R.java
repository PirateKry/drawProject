package Model_Rebild;

import javafx.scene.canvas.GraphicsContext;

public class Circle_R extends Pixel_R{

    private double rad = 0;

    public Circle_R(int x, int y, double rad) {
        super(x,y);
        this.rad = rad;
    }

    public void setRad(double rad){this.rad = rad;}
    public double getRad(){return rad;}

    @Override
    public void draw(GraphicsContext g){
        applyStrokeStyle(g);
        g.strokeOval(x - rad/2.0, y - rad/2.0, rad, rad);
    }
}
