package com.murat_uslu;

import com.murat_uslu.Controllers.MainController;
import com.murat_uslu.Views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage){
        MainView mainView = new MainView(stage, "System Information");
        MainController mainController = new MainController(stage, mainView);

        mainController.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}