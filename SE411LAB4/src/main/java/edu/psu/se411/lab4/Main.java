package edu.psu.se411.lab4;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("My Project");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}