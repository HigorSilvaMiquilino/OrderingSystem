package com.example.pizzeriamanagementsystem;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoggingForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label labelPassword = new Label();
        Label labelEmail = new Label();
        Label message = new Label("   Doesn't have an account? ");

        TextField textFieldEmail = new TextField();
        TextField textFieldPassword = new TextField();

        Button btn = new Button("Login");
        Button btnSingUp = new Button("Sing Up");

        GridPane gridPane = new GridPane();

        textFieldEmail.setPromptText("user@gmail.com ");
        textFieldEmail.setMinWidth(300);
        labelEmail.setText("Email: ");

        textFieldPassword.setPromptText("******");
        textFieldPassword.setMinWidth(300);
        labelPassword.setText("Password");

        btn.setMinWidth(100);
        btn.setOnAction(actionEvent -> {
            System.out.println("Logging...");
        });

        btnSingUp.setMinWidth(100);
        btnSingUp.setOnAction(actionEvent -> System.out.println("Singing Up..."));

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));


        GridPane.setConstraints(labelEmail, 0, 0);
        GridPane.setConstraints(labelPassword, 0, 1);

        GridPane.setConstraints(textFieldEmail, 1, 0);
        GridPane.setConstraints(textFieldPassword, 1, 1);
        GridPane.setConstraints(btn, 1, 2);
        GridPane.setConstraints(btnSingUp, 1, 3);
        GridPane.setHalignment(btn, HPos.RIGHT);
        GridPane.setHalignment(btnSingUp, HPos.RIGHT);

        GridPane.setConstraints(message, 1, 3);
        GridPane.setHalignment(message, HPos.LEFT);

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.getChildren().addAll(labelEmail, labelPassword, textFieldEmail, textFieldPassword, btn,btnSingUp,message);



        Scene scene = new Scene(gridPane, 800, 600);
        stage.setTitle("WellCome");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
