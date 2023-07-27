package com.example.orderingsystem.controller;

import com.example.orderingsystem.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch(Pane pane, String fxml) throws IOException{
        Pane nextPane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(nextPane);
    }
}
