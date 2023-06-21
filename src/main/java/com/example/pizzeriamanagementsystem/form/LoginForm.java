package com.example.pizzeriamanagementsystem.form;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label labelPassword = new Label();
        Label labelEmail = new Label();
        Label message = new Label("   Doesn't have an account? ");
        Label labelValidationEmail =    new Label("                              ");
        Label labelValidationPassword = new Label("                              ");

        TextField textFieldEmail = new TextField();
        PasswordField passwordField = new PasswordField();

        Button btn = new Button("Login");
        Button btnSingUp = new Button("Sing Up");


        GridPane gridPane = new GridPane();

        textFieldEmail.setPromptText("user@gmail.com ");
        textFieldEmail.setMinWidth(300);
        labelEmail.setText("Email: ");

        passwordField.setPromptText("*********");
        passwordField.setMinWidth(300);
        labelPassword.setText("Password");

        btn.setMinWidth(100);
        btn.setOnAction(actionEvent -> {
            validationEmail(textFieldEmail,gridPane);
            validationPassword(passwordField,gridPane);

        });

        btnSingUp.setMinWidth(100);
        btnSingUp.setOnAction(actionEvent -> {
            System.out.println("Signing up....");
            RegisterForm registerForm = new RegisterForm();
            registerForm.start(new Stage());
            stage.close();
        });

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));


        GridPane.setConstraints(labelEmail, 0, 0);
        GridPane.setConstraints(labelPassword, 0, 1);

        GridPane.setConstraints(textFieldEmail, 1, 0);
        GridPane.setConstraints(passwordField, 1, 1);
        GridPane.setConstraints(btn, 1, 2);
        GridPane.setConstraints(btnSingUp, 1, 3);
        GridPane.setHalignment(btn, HPos.RIGHT);
        GridPane.setHalignment(btnSingUp, HPos.RIGHT);

        GridPane.setConstraints(message, 1, 3);
        GridPane.setHalignment(message, HPos.LEFT);

        GridPane.setConstraints(labelValidationEmail, 1, 5);
        GridPane.setConstraints(labelValidationPassword, 1, 6);

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.getChildren().addAll(labelEmail, labelPassword, textFieldEmail, passwordField, btn,btnSingUp,message,
                                        labelValidationEmail,labelValidationPassword);

        Scene scene = new Scene(gridPane, 850, 700);
        stage.setTitle("WellCome");
        stage.setScene(scene);
        stage.show();
    }

    public void validationPassword(PasswordField passwordField,GridPane gridPane){
        Label label = new Label("At least 8 characters Password");
        if (passwordField.getLength() >= 8){
            System.out.println("User typed: "+passwordField.getText());
            passwordField.clear();
            label.setText("");
            GridPane.setConstraints(label,1, 6);
            gridPane.getChildren().addAll(label);
        } else {
            label.setTextFill(Color.RED);
            passwordField.clear();
            GridPane.setConstraints(label, 1, 6);
            gridPane.getChildren().addAll(label);
        }
    }

    public void validationEmail(TextField textFieldEmail,GridPane gridPane){
        Label label = new Label("Email Field is empty");
        if (!textFieldEmail.getText().trim().isBlank()){
            System.out.println("User typed: "+textFieldEmail.getText());
            textFieldEmail.clear();
            label.setText("");
            GridPane.setConstraints(label, 1, 5);
            gridPane.getChildren().addAll(label);
        }else {
            label.setTextFill(Color.RED);
            textFieldEmail.clear();
            GridPane.setConstraints(label, 1, 5);
            gridPane.getChildren().addAll(label);

        }

    }


    public static void main(String[] args) {
        launch();
    }


}
