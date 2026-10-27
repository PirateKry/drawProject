package Model;

import java.awt.*;

abstract class Pixel {
    protected int x;
    protected int y;
    protected int size;

    public Pixel(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {}
}
