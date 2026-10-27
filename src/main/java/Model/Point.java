package Model;

import java.awt.*;

public class Point extends Pixel {

    //вызов конструктора pixel
    public Point(int x, int y, int size) {
        super(x,y,size);
    }

    @Override
    public void draw(Graphics g) {}
}
