package Model;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;


public interface Tool {

    void onMousePressed(double x, double y);
    void onMouseDragged(double x, double y);
    void onMouseRelesead(double x, double y);


    Cursor getCursor();
    String getName();

    void setColor(Color color);
    Color getColor();

}
