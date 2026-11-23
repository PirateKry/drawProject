package Model_Rebild;

import Model.*;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VectorTextLoader {

    public static List<Shape> load(File file) throws IOException {
        List<Shape> result = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            List<double[]> vertices = null;
            Color strokeColor = Color.BLACK;
            double strokeWidth = 2.0;
            Shape shape = null;
            boolean inPolygon = false;

            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "POLYGON" -> {
                        vertices = new ArrayList<>();
                        strokeColor = Color.BLACK;
                        strokeWidth = 2.0;
                        shape = null;
                        inPolygon = true;
                    }
                    case "VERTEX" -> {
                        if (vertices != null) {
                            vertices.add(new double[]{
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])});
                        }
                    }
                    case "STROKE_COLOR" -> strokeColor = Color.web(parts[1]);
                    case "STROKE_WIDTH" -> strokeWidth = Double.parseDouble(parts[1]);
                    case "FILL" -> {
                        if (shape == null) shape = buildBase(vertices, strokeColor, strokeWidth);
                        shape = new FillDecorator(shape, Color.web(parts[1]));
                    }
                    case "BORDER" -> {
                        if (shape == null) shape = buildBase(vertices, strokeColor, strokeWidth);
                        shape = new BorderDecorator(shape,
                                Color.web(parts[1]), Double.parseDouble(parts[2]));
                    }
                    case "SHADOW" -> {
                        if (shape == null) shape = buildBase(vertices, strokeColor, strokeWidth);
                        shape = new ShadowDecorator(shape,
                                Color.web(parts[1]),
                                Double.parseDouble(parts[2]),
                                Double.parseDouble(parts[3]));
                    }
                    case "END" -> {
                        if (shape == null) shape = buildBase(vertices, strokeColor, strokeWidth);
                        result.add(shape);
                        inPolygon = false;
                        shape = null;
                        vertices = null;
                    }
                    default -> { /* неизвестные строки игнорируем */ }
                }
            }
        }
        return result;
    }

    private static Shape buildBase(List<double[]> vertices, Color color, double width) {
        if (vertices == null || vertices.size() < 3) {
            throw new IllegalStateException("Полигон без вершин");
        }
        Polygon p = new Polygon(vertices);
        p.setStrokeColor(color);
        p.setStrokeWidth(width);
        return p;
    }
}