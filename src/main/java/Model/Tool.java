package Model;

import java.awt.*;

public interface Tool {

    public void onMousePressed(Point p);
    public void onMouseDragged(Point p);
    public void onMouseRelesead(Point p);


    public Cursor GetCursor();
    public Cursor GetName();

}
