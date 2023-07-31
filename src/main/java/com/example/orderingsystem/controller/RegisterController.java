package com.example.orderingsystem.controller;

import com.example.orderingsystem.models.ClientEnum;
import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.service.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public Button returnBtn;
    @FXML
    GridPane gridPaneRegister;
    @FXML
    Label nameLabel, lastNameLabel, emailLabel, passwordLabel, birthdayLabel, phoneNumberLabel, typeClientLabel,
            errorNameLabel, errorLastNameLabel, errorEmailLabel, errorPasswordLabel, errorBirthdayLabel,
            errorPhoneLabel, errorTypeLabel;

    @FXML
    TextField nameTextField, lastNameTextField, emailTextField, phoneNumberTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    DatePicker birthdayDatePicker;
    @FXML
    ChoiceBox<ClientEnum> typeClientEnum;


    String errorMessage = "-fx-text-fill: RED;";
    String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";


    @FXML
    public void registerButtonPressed() throws IOException {
        if (validatedFields()) {
            ClientModel client = new ClientModel(nameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    passwordField.getText(),
                    phoneNumberTextField.getText(),
                    Period.between(birthdayDatePicker.getValue(), LocalDate.now()).getYears(),
                    typeClientEnum.getValue());
            ClientService.save(client);
            new SceneSwitch(gridPaneRegister, "/com/example/orderingsystem/login.fxml");
        } else {
            System.out.println("Invalid");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeClientEnum.getItems().setAll(ClientEnum.values());


        nameTextField.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                nameTextField.setStyle("");
                errorNameLabel.setText("");

            }
        });

        lastNameTextField.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                lastNameTextField.setStyle("");
                errorLastNameLabel.setText("");
            }
        });

        emailTextField.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                emailTextField.setStyle("");
                errorEmailLabel.setText("");
            }
        });

        passwordField.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                passwordField.setStyle("");
                errorPasswordLabel.setText("");
            }
        });

        phoneNumberTextField.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                phoneNumberTextField.setStyle("");
                errorPhoneLabel.setText("");
            }
        });

        birthdayDatePicker.valueProperty().addListener((Observable, oldValue, newValue) -> {
            if (newValue != null) {
                birthdayDatePicker.setStyle("");
                errorBirthdayLabel.setText("");
            }
        });

        typeClientEnum.valueProperty().addListener((Observable, oldValue, newValue) -> {
            if (newValue != null) {
                typeClientEnum.setStyle("");
                errorTypeLabel.setText("");
            }
        });


    }

    private boolean validatedFields() {
        boolean isValid = true;

        if (emailTextField.getText().isBlank()) {
            emailTextField.setStyle(errorStyle);
            errorEmailLabel.setStyle(errorMessage);
            errorEmailLabel.setText("Email field is empty, not allowed");
            emailTextField.clear();
            isValid = false;
        } else if (isExistingEmail(emailTextField.getText().trim())) {
            emailTextField.setStyle(errorStyle);
            errorEmailLabel.setStyle(errorMessage);
            errorEmailLabel.setText("Email already exist");
            emailTextField.clear();
            isValid = false;
        } else if (lastNameTextField.getText().isBlank()) {
            lastNameTextField.setStyle(errorStyle);
            errorLastNameLabel.setStyle(errorMessage);
            errorLastNameLabel.setText("Last Name field is empty, not allowed");
            isValid = false;
        } else if (nameTextField.getText().isBlank()) {
            nameTextField.setStyle(errorStyle);
            errorNameLabel.setStyle(errorMessage);
            errorNameLabel.setText("Name field is empty, not allowed");
            isValid = false;
        } else if (passwordField.getText().isBlank()) {
            passwordField.setStyle(errorStyle);
            errorPasswordLabel.setStyle(errorMessage);
            errorPasswordLabel.setText("Password field is empty, not allowed");
            isValid = false;
        } else if (!phoneNumberTextField.getText().trim().matches("\\d{11}")) {
            phoneNumberTextField.setStyle(errorStyle);
            errorPhoneLabel.setStyle(errorMessage);
            errorPhoneLabel.setText("Only digits allowed, 11 digits needed '(99) 9999-9999'");
            phoneNumberTextField.clear();
            isValid = false;
        } else if (birthdayDatePicker.getValue() == null) {
            birthdayDatePicker.setStyle(errorStyle);
            errorBirthdayLabel.setStyle(errorMessage);
            errorBirthdayLabel.setText("Date field is empty, not allowed");
            isValid = false;
        } else if (typeClientEnum.getValue() == null) {
            typeClientEnum.setStyle(errorStyle);
            errorTypeLabel.setStyle(errorMessage);
            errorTypeLabel.setText("Date field is empty, not allowed");
            isValid = false;
        }
        return isValid;
    }

    private boolean isExistingEmail(String email) {
        final List<ClientModel> clientModelList = ClientService.findAll();
        for (ClientModel clients : clientModelList) {
            if (clients.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void returnButtonPressed(ActionEvent event) throws IOException {
        new SceneSwitch(gridPaneRegister, "/com/example/orderingsystem/login.fxml");
    }
}
