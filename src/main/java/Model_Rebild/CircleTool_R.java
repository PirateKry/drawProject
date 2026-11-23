package Model_Rebild;

import Model.PixelType;

public class CircleTool_R extends AbstractTool_R{
    private double rad = 10;
    public CircleTool_R(DrCanvas_R c, PixelFactory_R f) {
        super(c,f);
    }

    @Override
    protected PixelType getType(){
        return PixelType.CIRCLE;
    }
    @Override
    protected double[] buildArgs(){
        return new double[]{rad};
    }
    @Override
    public String getName(){
        return "Circle";
    }

    public void setRad(double rad){
        this.rad = rad;
    }
    public double getRad(){
        return rad;
    }
}
