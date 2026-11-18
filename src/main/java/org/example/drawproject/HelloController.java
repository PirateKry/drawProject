package org.example.drawproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import Model.*;
import Model_Rebild.*;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML private DrCanvas_R drawingCanvas;
    @FXML private Label toolLabel;

    //элементы управления кнопки выбора инструмента
    @FXML private Button circleButton;
    @FXML private Button squareButton;
    @FXML private Button triangleButton;
    @FXML private Button polygonButton;
    @FXML private Button clearButton;

    //элементы управления общий цвет
    @FXML private ColorPicker colorPicker;

    //элементы управления растра (параметры фигур)
    @FXML private Spinner<Double> circleRadSpinner;
    @FXML private Spinner<Double> squareASpinner;
    @FXML private Spinner<Double> squareBSpinner;
    @FXML private Spinner<Double> triangleASpinner;
    @FXML private Spinner<Double> triangleBSpinner;
    @FXML private Spinner<Double> triangleCSpinner;

    //элементы управления полигона (декораторы векторного полигона)
    @FXML private CheckBox    fillCheck;
    @FXML private ColorPicker fillColorPicker;
    @FXML private CheckBox    borderCheck;
    @FXML private ColorPicker borderColorPicker;
    @FXML private Spinner<Double> borderWidthSpinner;
    @FXML private CheckBox    shadowCheck;
    @FXML private ColorPicker shadowColorPicker;
    @FXML private Spinner<Double> shadowDxSpinner;
    @FXML private Spinner<Double> shadowDySpinner;

    private PixelFactory_R factory;

    //растровые инструменты (пишут в raster)
    private CircleTool_R   circleTool;
    private SquareTool_R   squareTool;
    private TriangleTool_R triangleTool;

    //векторный инструмент (пишет в vector через декораторы)
    private PolygonTool_R polygonTool;

    //инициализация
    @FXML
    public void initialize() {
        factory = new PixelFactory_R();

        //растровые инструменты
        circleTool   = new CircleTool_R(drawingCanvas, factory);
        squareTool   = new SquareTool_R(drawingCanvas, factory);
        triangleTool = new TriangleTool_R(drawingCanvas, factory);

        //векторный инструмент: декораторы применяются в момент finish()
        polygonTool = new PolygonTool_R(drawingCanvas, p -> decoratePolygon(p));

        //спиннеры растровых параметров
        circleRadSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 200, 20));
        squareASpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 300, 40));
        squareBSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 300, 40));
        triangleASpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 300, 50));
        triangleBSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 300, 50));
        triangleCSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 300, 50));

        //спиннер «толкает» значение в инструмент
        circleRadSpinner.valueProperty().addListener((o, ov, nv) -> circleTool.setRad(nv));
        squareASpinner.valueProperty().addListener((o, ov, nv) -> squareTool.setA(nv));
        squareBSpinner.valueProperty().addListener((o, ov, nv) -> squareTool.setB(nv));
        triangleASpinner.valueProperty().addListener((o, ov, nv) -> triangleTool.setA(nv));
        triangleBSpinner.valueProperty().addListener((o, ov, nv) -> triangleTool.setB(nv));
        triangleCSpinner.valueProperty().addListener((o, ov, nv) -> triangleTool.setC(nv));

        //начальные значения
        circleTool.setRad(circleRadSpinner.getValue());
        squareTool.setA(squareASpinner.getValue());
        squareTool.setB(squareBSpinner.getValue());
        triangleTool.setA(triangleASpinner.getValue());
        triangleTool.setB(triangleBSpinner.getValue());
        triangleTool.setC(triangleCSpinner.getValue());

        //спиннеры декораторов
        borderWidthSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 20, 2));
        shadowDxSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-50, 50, 5));
        shadowDySpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-50, 50, 5));

        //цвет по умолчанию
        colorPicker.setValue(Color.BLACK);
        applyColor(colorPicker.getValue());
        colorPicker.setOnAction(e -> applyColor(colorPicker.getValue()));

        //обработчик событий нажатия кнопок
        circleButton.setOnAction(e -> selectTool(circleTool));
        squareButton.setOnAction(e -> selectTool(squareTool));
        triangleButton.setOnAction(e -> selectTool(triangleTool));
        polygonButton.setOnAction(e -> selectTool(polygonTool));
        clearButton.setOnAction(e -> drawingCanvas.clear());

        //инструмент по умолчанию
        selectTool(circleTool);
    }

    //общий цвет для всех инструментов
    private void applyColor(Color c) {
        circleTool.setColor(c);
        squareTool.setColor(c);
        triangleTool.setColor(c);
        polygonTool.setColor(c);   // цвет контура полигона
    }

    //Выбор активного инструмента
    private void selectTool(Tool tool) {
        drawingCanvas.setCurTool(tool);
        toolLabel.setText("Инструмент: " + tool.getName());
    }

    //сборка цепочки декораторов для полигона
    public Shape decoratePolygon(Polygon p) {
        Shape shape = p;

        // 1. Заливка
        if (fillCheck.isSelected()) {
            shape = new FillDecorator(shape, fillColorPicker.getValue());
        }
        // 2. Тень
        if (shadowCheck.isSelected()) {
            shape = new ShadowDecorator(shape, shadowColorPicker.getValue(),
                    shadowDxSpinner.getValue(), shadowDySpinner.getValue());
        }
        // 3. Контур
        if (borderCheck.isSelected()) {
            shape = new BorderDecorator(shape, borderColorPicker.getValue(),
                    borderWidthSpinner.getValue());
        }
        return shape;
    }
}