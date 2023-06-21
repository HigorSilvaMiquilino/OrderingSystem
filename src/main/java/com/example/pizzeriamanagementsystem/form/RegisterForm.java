package com.example.pizzeriamanagementsystem.form;

import com.example.pizzeriamanagementsystem.models.EmployeeEnum;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Name: ");
        Label lastnameLabel = new Label("Last Name: ");
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        Label ageLabel = new Label("Age: ");
        Label salaryLabel = new Label("Salary: ");
        Label employeeLabel = new Label("Charge: ");

        TextField nameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField emailTextField = new TextField();

        PasswordField passwordField = new PasswordField();

        DatePicker datePicker = new DatePicker();

        TextField salaryTextField = new TextField();

        ChoiceBox<EmployeeEnum> choiceBoxEnum = new ChoiceBox<>();
        choiceBoxEnum.getItems().setAll(EmployeeEnum.values());
        choiceBoxEnum.setMinWidth(174);



        Button registerBtn = new Button("Register");


        emailTextField.setPromptText("user@gmail.com");
        emailTextField.setMinWidth(300);
        lastNameTextField.setMinWidth(300);
        passwordField.setMinWidth(300);
        salaryTextField.setMinWidth(300);

        passwordField.setPromptText("*******");
        registerBtn.setMinWidth(300);

        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10,10,10,10));
        
        GridPane.setConstraints(nameLabel,0,0);
        GridPane.setConstraints(nameTextField,1,0);
        GridPane.setConstraints(lastnameLabel,0,1);
        GridPane.setConstraints(lastNameTextField,1,1);
        GridPane.setConstraints(emailLabel,0,2);
        GridPane.setConstraints(emailTextField,1,2);
        GridPane.setConstraints(passwordLabel,0,3);
        GridPane.setConstraints(passwordField,1,3);

        GridPane.setConstraints(ageLabel,0,4);
        GridPane.setConstraints(datePicker,1,4);

        GridPane.setConstraints(salaryLabel,0,5);
        GridPane.setConstraints(salaryTextField,1,5);

        GridPane.setConstraints(employeeLabel,0,6);
        GridPane.setConstraints(choiceBoxEnum,1,6);

        GridPane.setConstraints(registerBtn,1,7);


        gridPane.setHgap(20);
        gridPane.setVgap(20);


        gridPane.getChildren().addAll(nameLabel,nameTextField,lastnameLabel,lastNameTextField,emailLabel,
                emailTextField,passwordLabel,passwordField,ageLabel,datePicker,salaryLabel,salaryTextField,
                employeeLabel,choiceBoxEnum,registerBtn);

        Scene scene = new Scene(gridPane,850,700);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }



    public static void main(String[] args) {
        launch(args);
    }
}
