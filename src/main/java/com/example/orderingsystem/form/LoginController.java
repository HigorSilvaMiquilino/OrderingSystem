package com.example.orderingsystem.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class LoginController {

    @FXML
    Label emailLabel, passwordLabel, messageLabel;
    @FXML
    TextField emailTextField, passwordTextField;
    @FXML
    GridPane gridPaneLogin;
    @FXML
    Button buttonLogin, buttonSingUp;

    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    @FXML
    protected void loginButtonPressed(ActionEvent event) {

    }

    @FXML
    protected void singUpButtonPressed() throws IOException {
        new SceneSwitch(gridPaneLogin, "/com/example/orderingsystem/register.fxml");
    }

}

