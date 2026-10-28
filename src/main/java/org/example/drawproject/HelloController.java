package org.example.drawproject;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {
    //эл управления
    @FXML private DrCanvas drawingCanvas;
    @FXML private Label toolLabel;
    @FXML private Button pointButton;
    @FXML private Button circleButton;
    @FXML private Button squareButton;
    @FXML private Button clearButton;
    @FXML private ColorPicker colorPicker;

    private PixelFactory factory;
    private Tool pointTool;
    private Tool circleTool;
    private Tool squareTool;

    @FXML
    public void initialize() {
        //фабрика
        factory = new PixelFactory();

        //инструменты(точка, сруг, квадрат)
        pointTool  = new PointTool(drawingCanvas, factory,3);
        circleTool = new CircleTool(drawingCanvas, factory);
        squareTool = new SquareTool(drawingCanvas, factory);

        colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        applyColor(colorPicker.getValue());

        drawingCanvas.setCurTool(pointTool);
        toolLabel.setText("Инструмент: " + pointTool.getName());

        colorPicker.setOnAction(e -> applyColor(colorPicker.getValue()));

        //привязка кнопок
        pointButton.setOnAction(e -> selectTool(pointTool));
        circleButton.setOnAction(e -> selectTool(circleTool));
        squareButton.setOnAction(e -> selectTool(squareTool));
        clearButton.setOnAction(e -> {
            drawingCanvas.clear();
        });
    }

    private void applyColor(javafx.scene.paint.Color c) {
        pointTool.setColor(c);
        circleTool.setColor(c);
        squareTool.setColor(c);
    }

    private void selectTool(Tool tool) {
        drawingCanvas.setCurTool(tool);
        toolLabel.setText("Инструмент: " + tool.getName());
    }
}