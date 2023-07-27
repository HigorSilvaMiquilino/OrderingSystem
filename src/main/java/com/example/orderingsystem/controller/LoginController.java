package com.example.orderingsystem.controller;

import com.example.orderingsystem.HelloApplication;
import com.example.orderingsystem.data;
import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.service.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable  {

    @FXML
    GridPane gridPaneLogin;
    @FXML
    Label emailLabel, passwordLabel, messageLabel;
    @FXML
    TextField emailTextField, passwordTextField;
    @FXML
    Button buttonLogin, buttonSingUp;

    String errorMessage = "-fx-text-fill: RED;";
    String errorStyle = "-fx-border-color: RED; -fx-border-width: 2";

    @FXML
    protected void loginButtonPressed(ActionEvent event) throws IOException {

        if (validateFields()) {
            Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/com/example/orderingsystem/MainDishes.fxml")));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(Objects.requireNonNull(
                    HelloApplication.class.getResource("stylesheet/mainDishes.css")
            ).toExternalForm());

            stage.setMaximized(true);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Invalid");
        }
    }

    @FXML
    protected void singUpButtonPressed() throws IOException {
        new SceneSwitch(gridPaneLogin, "/com/example/orderingsystem/register.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                emailTextField.setStyle("");
                messageLabel.setText("");
                messageLabel.setStyle("");
            }
        });

        passwordTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                passwordTextField.setStyle("");
                messageLabel.setText("");
                messageLabel.setStyle("");
            }
        });
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (emailTextField.getText().isBlank()) {
            emailTextField.setStyle(errorStyle);
            messageLabel.setStyle(errorMessage);
            messageLabel.setText("Email field is empty");
            isValid = false;
        } else if (passwordTextField.getText().isBlank()) {
            passwordTextField.setStyle(errorStyle);
            messageLabel.setStyle(errorMessage);
            messageLabel.setText("Password field is empty");
            isValid = false;
        } else if (!isExistingEmail(emailTextField.getText())) {
            emailTextField.setStyle(errorStyle);
            messageLabel.setStyle(errorMessage);
            messageLabel.setText("Email not exist");
            isValid = false;
        } else if (!isExistingPassword(passwordTextField.getText())) {
            passwordTextField.setStyle(errorStyle);
            messageLabel.setStyle(errorMessage);
            messageLabel.setText("Password Invalid");
            isValid = false;
        }
        return isValid;
    }

    private boolean isExistingEmail(String email) {
        final List<ClientModel> clientModelList = ClientService.findAll();
        for (ClientModel clients : clientModelList) {
            if (clients.getEmail().equals(email)) {
                data.username = clients.getFirstName();
                return true;
            }
        }
        return false;
    }

    private boolean isExistingPassword(String password) {
        final List<ClientModel> clientModelList = ClientService.findAll();
        for (ClientModel clients : clientModelList) {
            if (clients.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}

