package com.murat_uslu.Controllers;

import com.murat_uslu.Views.MainView;
import com.murat_uslu.utils.MemoryInfo;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {
    private Stage stage;
    private MainView mainView;
    private MemoryInfo memoryInfo;

    private double freeMemory;
    private double activeMemory;
    private double inactiveMemory;
    private double wiredMemory;

    private ScheduledExecutorService scheduler;

    public MainController(Stage stage, MainView mainView){
        setStage(stage);
        setMainView(mainView);
        adjustStage();

        memoryInfo = new MemoryInfo();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    private void adjustStage(){
        stage.setTitle(mainView.getSceneTitle());
        stage.setScene(mainView.getScene());
        stage.setResizable(false);
        positionScreen();
    }


    private void obtainMemoryInfo(){
        String info = memoryInfo.getMemoryInfo();
        String[] infoArr = MemoryInfo.parseMemoryInfo(info);

        freeMemory = Double.parseDouble(infoArr[0]);
        activeMemory = Double.parseDouble(infoArr[1]);
        inactiveMemory = Double.parseDouble(infoArr[2]);
        wiredMemory = Double.parseDouble(infoArr[3]);
    }

    private void updateProgress(){
        double availableMemory = freeMemory + inactiveMemory;
        double totalMemory = 18.0 * 1024;
        double freePercent = (availableMemory / totalMemory) * 100.0;
        double usedPercent = 100.0 - freePercent;

        String progressText = "Used Memory:\n%.2f%%".formatted(usedPercent);

        mainView.getCircularProgress1().setProgress(usedPercent, progressText);

    }

    private void positionScreen(){
        Rectangle2D rectangle2D = Screen.getPrimary().getBounds();
        double x = rectangle2D.getMaxX();
        double y = rectangle2D.getMinY();

        stage.setX(x);
        stage.setY(y);

    }

    public void startScheduledTask() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            // Your repeated method
            obtainMemoryInfo();

            // Optional: update JavaFX UI safely
            javafx.application.Platform.runLater(() -> {
                updateProgress(); // Make sure this touches only JavaFX UI
            });

        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public void showStage(){
        stage.setOnCloseRequest(event -> {
            if (scheduler != null && !scheduler.isShutdown()) {
                scheduler.shutdownNow(); // This stops the scheduled task
            }
        });
        startScheduledTask();
        stage.show();
    }
}
