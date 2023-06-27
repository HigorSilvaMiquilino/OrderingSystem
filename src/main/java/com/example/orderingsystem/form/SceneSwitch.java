package com.example.orderingsystem.form;

import com.example.orderingsystem.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch(GridPane currentGridPane, String fxml) throws IOException{
        GridPane nextGridPane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        currentGridPane.getChildren().removeAll();
        currentGridPane.getChildren().setAll(nextGridPane);
    }
}
