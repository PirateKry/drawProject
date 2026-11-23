package Model_Rebild;

import Model.PixelType;

public class TriangleTool_R extends AbstractTool_R {

    private double a = 30, b = 30, c = 30;

    public TriangleTool_R(DrCanvas_R c, PixelFactory_R f) {
        super(c, f);
    }

    public void setA(double a) { this.a = a; }
    public void setB(double b) { this.b = b; }
    public void setC(double c) { this.c = c; }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }


    @Override protected PixelType getType(){
        return PixelType.TRIANGLE;
    }
    @Override protected double[] buildArgs(){
        return new double[]{a, b, c};
    }
    @Override public String getName(){
        return "Triangle";
    }
}