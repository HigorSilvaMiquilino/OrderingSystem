package com.example.orderingsystem.form;

import com.example.orderingsystem.models.ClientEnum;
import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.service.ClientService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    Label nameLabel, lastNameLabel, emailLabel, passwordLabel, birthdayLabel, phoneNumberLabel, typeClientLabel;
    @FXML
    TextField nameTextField, lastNameTextField, emailTextField, phoneNumberTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    DatePicker birthdayDatePicker;
    @FXML
    ChoiceBox<ClientEnum> typeClientEnum;
    @FXML
    GridPane gridPaneRegister;




    @FXML
    public void registerButtonPressed(){
        final String name = nameTextField.getText();
        final String lastName = lastNameTextField.getText();
        final String email = emailTextField.getText();
        final String password = passwordField.getText();
        final String phoneNumber = phoneNumberTextField.getText();
        LocalDate now = LocalDate.now();
        LocalDate birthdayDate = birthdayDatePicker.getValue();
        int years = Period.between(birthdayDate, now).getYears();
        ClientEnum enumeration = typeClientEnum.getValue();

        ClientModel james =  new ClientModel(name,lastName,email,password,phoneNumber,years,enumeration);
        ClientService.save(james);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeClientEnum.getItems().setAll(ClientEnum.values());
    }


}
