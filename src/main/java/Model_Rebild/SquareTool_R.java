package Model_Rebild;

import Model.PixelType;

public class SquareTool_R extends AbstractTool_R{
    private double a = 10, b = 10;
    public SquareTool_R(DrCanvas_R c, PixelFactory_R f){
        super(c,f);
    }

    @Override
    protected PixelType getType(){
        return PixelType.SQUARE;
    }
    @Override
    protected double[] buildArgs(){
        return new double[]{a,b};
    }
    @Override
    public String getName(){
        return "Square";
    }

    public void setA(double a){
        this.a = a;
    }
    public void setB(double b){
        this.b = b;
    }
}
