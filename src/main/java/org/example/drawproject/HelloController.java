package org.example.drawproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import Model.*;
import javafx.scene.paint.Color;

public class HelloController {
    // Элементы управления
    @FXML private DrCanvas drawingCanvas;
    @FXML private Label toolLabel;

    // Кнопки инструментов
    @FXML private Button pointButton;
    @FXML private Button circleButton;
    @FXML private Button squareButton;
    @FXML private Button polygonButton;
    @FXML private Button clearButton;

    // Общий выбор цвета для растровых инструментов
    @FXML private ColorPicker colorPicker;

    // Элементы для настройки декораторов
    @FXML private CheckBox fillCheck;
    @FXML private ColorPicker fillColorPicker;
    @FXML private CheckBox borderCheck;
    @FXML private ColorPicker borderColorPicker;
    @FXML private Spinner<Double> borderWidthSpinner;
    @FXML private CheckBox shadowCheck;
    @FXML private ColorPicker shadowColorPicker;
    @FXML private Spinner<Double> shadowDxSpinner;
    @FXML private Spinner<Double> shadowDySpinner;

    private PixelFactory factory;
    private Tool pointTool;
    private Tool circleTool;
    private Tool squareTool;
    private PolygonTool polygonTool;

    @FXML
    public void initialize() {
        factory = new PixelFactory();

        pointTool  = new PointTool(drawingCanvas, factory, 10);
        circleTool = new CircleTool(drawingCanvas, factory);
        squareTool = new SquareTool(drawingCanvas, factory);
        polygonTool = new PolygonTool(drawingCanvas);

        // Настройка цвета для растровых инструментов
        colorPicker.setValue(Color.BLACK);
        applyColor(colorPicker.getValue());
        colorPicker.setOnAction(e -> applyColor(colorPicker.getValue()));

        // Настройка Spinner'ов (толщина контура, смещение тени)
        borderWidthSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 20, 2));
        shadowDxSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-50, 50, 5));
        shadowDySpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-50, 50, 5));

        // Привязка кнопок
        pointButton.setOnAction(e -> selectTool(pointTool));
        circleButton.setOnAction(e -> selectTool(circleTool));
        squareButton.setOnAction(e -> selectTool(squareTool));
        polygonButton.setOnAction(e -> selectTool(polygonTool));
        clearButton.setOnAction(e -> drawingCanvas.clear());

        // Устанавливаем инструмент по умолчанию
        selectTool(pointTool);
        polygonTool = new PolygonTool(drawingCanvas, p -> decoratePolygon(p));
    }

    private void applyColor(Color c) {
        pointTool.setColor(c);
        circleTool.setColor(c);
        squareTool.setColor(c);
        polygonTool.setColor(c); // передаем цвет и в полигон
    }

    private void selectTool(Tool tool) {
        drawingCanvas.setCurTool(tool);
        toolLabel.setText("Инструмент: " + tool.getName());
    }

    public Shape decoratePolygon(Polygon p) {
        Shape shape = p;

        // Контур - 3я
        if (borderCheck.isSelected()) {
            shape = new BorderDecorator(shape, borderColorPicker.getValue(),
                    borderWidthSpinner.getValue().doubleValue());
        }
        // Заливка - 2я
        if (fillCheck.isSelected()) {
            shape = new FillDecorator(shape, fillColorPicker.getValue());
        }
        // Тень - 1я
        if (shadowCheck.isSelected()) {
            shape = new ShadowDecorator(shape, shadowColorPicker.getValue(),
                    shadowDxSpinner.getValue().doubleValue(),
                    shadowDySpinner.getValue().doubleValue());
        }
        return shape;
    }
}