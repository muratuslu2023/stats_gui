package com.murat_uslu.Views;

import com.murat_uslu.Views.Components.CircularProgress;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MainView {


    private Scene scene;
    private Stage stage;
    private String sceneTitle;

    private StackPane root;

    private HBox hBox;

    private final double sceneWidth = 300.0;
    private final double sceneHeight = 300.0;

    private CircularProgress circularProgress1;
    private CircularProgress circularProgress2;
    private CircularProgress circularProgress3;


    public MainView(Stage stage, String sceneTitle){
        setStage(stage);
        setSceneTitle(sceneTitle);
        root = new StackPane();
        hBox = new HBox();

        hBox.setAlignment(Pos.CENTER);


        circularProgress1 = new CircularProgress(0.0,"");
        circularProgress2 = new CircularProgress(0.0,"");
        circularProgress3 = new CircularProgress(0.0,"");

        hBox.getChildren().addAll(
                circularProgress1.getProgressPane("Progress1")
                //circularProgress2.getProgressPane("Progress2"),
                //circularProgress3.getProgressPane("Progress3")
        );

        /*FlowPane flow = new FlowPane();
        flow.setAlignment(Pos.CENTER);
        flow.setHgap(20);
        flow.setVgap(20);
        flow.setPrefWidth(sceneWidth);

        flow.getChildren().addAll(
                circularProgress1.getProgressPane("Progress1"),
                circularProgress2.getProgressPane("Progress2"),
                circularProgress3.getProgressPane("Progress3")
        );


        StackPane a = new StackPane(flow);

        ScrollPane scrollPane = new ScrollPane(a);

        scrollPane.setFitToWidth(true);  // Optional: makes content stretch horizontally
        scrollPane.setPannable(true);*/


        root.getChildren().add(hBox);

        scene = new Scene(root, sceneWidth, sceneHeight);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getSceneTitle() {
        return sceneTitle;
    }

    public void setSceneTitle(String sceneTitle) {
        this.sceneTitle = sceneTitle;
    }

    public CircularProgress getCircularProgress1() {
        return circularProgress1;
    }

    public CircularProgress getCircularProgress2() {
        return circularProgress2;
    }

    public CircularProgress getCircularProgress3() {
        return circularProgress3;
    }
}
