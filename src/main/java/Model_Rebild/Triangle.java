package Model_Rebild;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Pixel_R{
    private double a = 0;
    private double b = 0;
    private double c = 0;

    public Triangle(int x, int y, double a, double b, double c) {
        super(x,y);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(double a){this.a = a;}
    public void setB(double b){this.b = b;}
    public void setC(double c){this.c = c;}

    public double getA(){return a;}
    public double getB(){return b;}
    public double getC(){return c;}

    private boolean isValid() {//проверка на существование треугольника
        return a > 0 && b > 0 && c > 0
                && a + b > c
                && a + c > b
                && b + c > a;
    }

    @Override
    public void draw(GraphicsContext g){
        if (!isValid()) return;

        // Вершина A
        double ax = x;
        double ay = y;

        // Вершина B — на расстоянии a по горизонтали
        double bx = x + a;
        double by = y;

        // Вершина C — пересечение окружностей (A, b) и (B, c)
        double cx = (b * b - c * c + a * a) / (2 * a);
        double cy = Math.sqrt(b * b - cx * cx);

        double cX = ax + cx;
        double cY = ay - cy; // "-" чтобы треугольник смотрел вверх

        double[] xs = { ax, bx, cX };
        double[] ys = { ay, by, cY };

        applyStrokeStyle(g);
        g.strokePolygon(xs, ys, 3);
    }
    //как можно реализовать фабрику с разным кол-во атрибутов у абектов и другой реализацией,
    // не switch, подсказка смс классы корекции

    //дикаратор должен быть отдельно от растра, иначе галемотья, переделать

    //реалтизовать сохраниение в 2х форматах (пнг и файл в котором прописана последовательнсть действий
    // типа название элемента/кординаты/доп параметы, в текстовом формате) можно только для векора если сложно

}
