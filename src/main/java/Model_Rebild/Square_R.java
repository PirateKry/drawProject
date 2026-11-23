package Model_Rebild;

import javafx.scene.canvas.GraphicsContext;

public class Square_R extends Pixel_R{

    private double width = 0;
    private double height = 0;

    public Square_R(int x, int y, double a, double b) {
        super(x,y);
        this.width = a;
        this.height = b;
    }

    public void setA(double a){this.width = a;}
    public void setB(double b){this.height = b;}

    public double getA(){return width;}
    public double getB(){return height;}

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);                    // растр = заливка, не контур
        g.fillRect(x, y, width, height);              // ← заполненный прямоугольник
    }
}
