package Model_Rebild;

import Model.PixelType;
import java.util.HashMap;
import java.util.Map;

public class PixelFactory_R {

    //функциональный интерфейс, принимающий x,y и несколько/0 параметров типа double
    @FunctionalInterface
    public interface PixelCreator {
        Pixel_R create(int x, int y, double... args);
    }

    //массив-словарь reg хранит данные типа ключ(название элемента из списка PixelType) и
    // значения(лямбда-функция создатель PixelCreator)
    private final Map<PixelType, PixelCreator> reg = new HashMap<>();

    //нихилый конструктор а.к регистрация - где перечислены все типы лямбд-функций
    public PixelFactory_R() {
        register(PixelType.CIRCLE,   (x, y, a)    -> new Circle_R(x, y, a[0]));
        register(PixelType.SQUARE,   (x, y, a)    -> new Square_R(x, y, a[0], a[1]));
        register(PixelType.TRIANGLE, (x, y, a)    -> new Triangle(x, y, a[0], a[1], a[2]));
    }

    //метод помещающий данные(ключ - название из списка и значение - лямбда-фукнция, создающую ту или ниную фигуру)
    public void register(PixelType type, PixelCreator creator) {
        reg.put(type, creator);
    }

    //меод ищущий по члючу(список с названиями) элемент и если есть вызывает нужную лямбда функцию
    public Pixel_R createPixel(PixelType type, int x, int y, double... args) {
        PixelCreator creator = reg.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Неизвестный тип: " + type);
        }
        return creator.create(x, y, args);
    }
}
