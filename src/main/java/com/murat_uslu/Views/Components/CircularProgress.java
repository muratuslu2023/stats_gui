package com.murat_uslu.Views.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CircularProgress {
    private double paneWidth = 340.0;
    private double paneHeight = 340.0;

    private double arcLayoutX = paneWidth / 2.0;
    private double arcLayoutY = paneHeight / 2.0;

    private Pane pane;
    private Arc arc;
    private Circle circle;
    private Color arcColor;
    private Color circleColor;
    private Color circle2Color;

    private Circle circle2;

    private Label label;
    private Font labelFont;

    private double progress;

    private static double defaultProgress = 40.0;
    private static String defaultProgressText = "Progress";

    private String progressText = "";

    public CircularProgress(){
        this(defaultProgress);
    }

    public CircularProgress(double progress){
        this(defaultProgress, defaultProgressText);
    }

    public CircularProgress(double progress, String progressText){
        pane = new StackPane(); // PANE
        circle = new Circle();
        circle2 = new Circle();
        arc = new Arc();
        label = new Label();
        labelFont = new Font("Arial", 24);
        label.setTextAlignment(TextAlignment.CENTER);
        setProgress(progress, progressText);
    }

    private void adjustProgress(){
        pane.getChildren().clear();

        if(progress > 90.0){
            arcColor = Color.rgb(206,25,25,1);
        }else{
            arcColor = Color.rgb(38, 78, 210, 1);
        }

        circleColor = new Color(.5, .5, .5, 1);
        circle2Color = new Color(.5, .5, .5, .5);

        pane.setPrefWidth(paneWidth);
        pane.setPrefHeight(paneHeight);

        circle.setRadius(110.0);
        circle.setFill(circleColor);
        circle.setStroke(null);
        circle.setStrokeWidth(0.0);
        circle.setCenterX(0.0);
        circle.setCenterY(0.0);


        circle2.setRadius(120.0);
        circle2.setFill(circle2Color);
        circle2.setStroke(null);
        circle2.setStrokeWidth(0.0);
        circle2.setCenterX(0.0);
        circle2.setCenterY(0.0);

        Pane arcContainer = new Pane();
        arcContainer.setPrefSize(220.0, 220.0); // Fixed size container
        arcContainer.setMaxSize(220.0, 220.0);
        arcContainer.setMinSize(220.0, 220.0);

        arc.setRadiusX(115.0);
        arc.setRadiusY(115.0);
        arc.setType(ArcType.OPEN);
        arc.setStartAngle(90.0); // Start from top
        arc.setLength(-(progress / 100.0) * 360.0); // Clockwise
        arc.setFill(null);
        arc.setStroke(arcColor);
        arc.setStrokeWidth(10.0);
        arc.getStrokeDashArray().clear();
        arc.setStrokeLineCap(StrokeLineCap.ROUND);

        arc.setCenterX(110.0); // Half of container width
        arc.setCenterY(110.0); // Half of container height

        arcContainer.getChildren().add(arc);

        label.setText(progressText);
        label.setFont(labelFont);

        pane.getChildren().addAll(circle, circle2, arcContainer, label);
    }

    public StackPane getProgressPane(String labelText) {
        adjustProgress();
        return (StackPane) pane;
    }

    public void setProgressText(String progressText){
        this.progressText = progressText;
    }

    public void setProgress(double progress, String progressText) {
        setProgressText(progressText);
        adjustProgress();
        this.progress = progress;
    }

    public double getProgress() {
        return progress;
    }

}
