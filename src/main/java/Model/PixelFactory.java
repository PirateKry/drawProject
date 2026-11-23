package Model;

import javafx.scene.paint.Color;

public class PixelFactory {

    public Pixel createPixel(PixelType type, int x, int y, int size, Color color) {

        Pixel p;
        switch (type){
            case POINT -> {
                p = new Point(x,y,size);
            }
            case CIRCLE -> {
                p = new Circle(x,y,size);
            }
            case SQUARE -> {
                p = new Square(x,y,size);
            }
            default -> {
                throw new IllegalArgumentException("Неизвестный тип Pixel " + type);
            }
        }
        p.setColor(color);
        return p;
    }

    public Pixel createPixel(PixelType t, int x, int y, int s) {
        return createPixel(t, x, y, s, Color.BLACK);
    }
}
