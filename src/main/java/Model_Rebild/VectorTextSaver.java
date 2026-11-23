package Model_Rebild;

import Model.*;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VectorTextSaver {

    public static void save(List<Shape> vector, File file) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            w.write("# drawProject vector file v1\n");
            for (Shape shape : vector) {
                writeShape(w, shape);
            }
        }
    }

    private static void writeShape(BufferedWriter w, Shape shape) throws IOException {
        // Разворачиваем цепочку: внешний → внутренний
        List<ShapeDecorator> decorators = new ArrayList<>();
        Shape current = shape;
        while (current instanceof ShapeDecorator dec) {
            decorators.add(dec);
            current = dec.getInner();
        }
        if (!(current instanceof Polygon polygon)) {
            return;   // не полигон — пропускаем (в векторе только полигоны)
        }

        w.write("POLYGON\n");
        for (double[] v : polygon.getVertices()) {
            w.write(String.format(Locale.ROOT, "  VERTEX %.2f %.2f%n", v[0], v[1]));
        }
        w.write(String.format(Locale.ROOT, "  STROKE_COLOR %s%n", toHex(polygon.getStrokeColor())));
        w.write(String.format(Locale.ROOT, "  STROKE_WIDTH %.2f%n", polygon.getStrokeWidth()));

        // Декораторы — в порядке «внутри → снаружи» (реверс, т.к. собрали снаружи внутрь)
        for (int i = decorators.size() - 1; i >= 0; i--) {
            writeDecorator(w, decorators.get(i));
        }
        w.write("END\n");
    }

    private static void writeDecorator(BufferedWriter w, ShapeDecorator dec) throws IOException {
        if (dec instanceof FillDecorator fill) {
            w.write(String.format(Locale.ROOT, "  FILL %s%n", toHex(fill.getFillColor())));
        } else if (dec instanceof BorderDecorator border) {
            w.write(String.format(Locale.ROOT, "  BORDER %s %.2f%n",
                    toHex(border.getStrokeColor()), border.getWidth()));
        } else if (dec instanceof ShadowDecorator shadow) {
            w.write(String.format(Locale.ROOT, "  SHADOW %s %.2f %.2f%n",
                    toHex(shadow.getShadowColor()), shadow.getDx(), shadow.getDy()));
        }
        // Новый декоратор = добавить else-if сюда
    }

    private static String toHex(Color c) {
        return String.format("#%02X%02X%02X",
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255));
    }
}