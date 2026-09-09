package com.kinal.mercado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/com/kinal/mercado/view/ProductoView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestión de Productos - QuickMart");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}