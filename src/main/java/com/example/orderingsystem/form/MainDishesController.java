package com.example.orderingsystem.form;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainDishesController implements Initializable {




    @FXML
    ImageView imageFood, imageDrinks, imageDesserts, imageOrdering, imageUser,imageAbout;
    @FXML
    BorderPane borderPaneMainDishes;
    @FXML
    VBox vBoxMainDishes;
    @FXML
    Label foodLabel, drinksLabel, dessertsLabel, orderingAppLabel, orderingLabel,userLabel,aboutLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void foodClicked(MouseEvent mouseEvent) {
    }
    @FXML
    public void imageFoodClicked(KeyEvent keyEvent) {
    }
    @FXML
    public void drinksClicked(MouseEvent mouseEvent) {
    }
    @FXML
    public void imageDrinkClicked(KeyEvent keyEvent) {
    }
    @FXML
    public void dessertsClicked(MouseEvent mouseEvent) {
    }
    @FXML
    public void imageDessertsClicked(KeyEvent keyEvent) {
    }
    @FXML
    public void orderingClicked(MouseEvent mouseEvent) {
    }
    @FXML
    public void imageOrderingClicked(KeyEvent keyEvent) {
    }
    @FXML
    public void userClicked(MouseEvent mouseEvent) {
    }
    @FXML
    public void imageUserClicked(KeyEvent keyEvent) {
    }
    @FXML
    public void aboutClicked(MouseEvent mouseEvent) {
    }
    @FXML
    public void imageAboutClicked(KeyEvent keyEvent) {
    }
}
