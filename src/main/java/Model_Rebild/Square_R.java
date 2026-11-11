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
    public void draw(GraphicsContext g){

        double x1,y1,x2,y2,x3,y3,x4,y4;

        x1 = x;//вершина а
        y1 = y;

        x2 = x+width;//вершина б
        y2 = y;

        x3 = x+width;//вершина в
        y3 = y+height;

        x4 = x;//вершина г
        y4 = y+height;

        double[] xs = {x1,x2,x3,x4};
        double[] ys = {y1,y2,y3,y4};

        applyStrokeStyle(g);
        g.strokePolygon(xs, ys, 4);
    }
}
