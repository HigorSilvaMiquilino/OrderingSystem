package com.example.orderingsystem.controller;

import com.example.orderingsystem.ProductData;
import com.example.orderingsystem.data;
import com.example.orderingsystem.orderingdatabase.conn.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CardController implements Initializable {



    @FXML
    private Button cardBtn;

    @FXML
    private Pane cardPane;

    @FXML
    private ImageView imageViewCard;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelName;

    @FXML
    private Label priceLabel;

    @FXML
    private Spinner<Integer> spinnerCard;

    private SpinnerValueFactory<Integer> spin;

    private Image image;

    private String prodID;
    private String type;
    private String prod_date;
    private String prod_image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private ProductData productData;

    private Alert alert;

    public void setProductData(ProductData productData) {
        this.productData = productData;
        prodID = productData.getProductId();
        type = productData.getType();
        prod_date = String.valueOf(productData.getDate());
        prod_image = productData.getImage();
        labelName.setText(productData.getProductName());
        priceLabel.setText("$" + String.valueOf(productData.getPrice()));
        String path = "File:" + productData.getImage();
        image = new Image(path, 208, 185, false, true);
        imageViewCard.setImage(image);
        price = productData.getPrice();
        labelDescription.setText(productData.getDescription());
    }

    private int quantity;

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinnerCard.setValueFactory(spin);
    }

    private double totalPrice;
    private double price;

    public void addBtn() {
       MainDishesController mDishesController = new MainDishesController();
       mDishesController.customerId();

        quantity = spinnerCard.getValue();
        String check = "";
        String checkAvailable = "SELECT status FROM product WHERE prod_id = '" + prodID + "'";

        try {
            connect = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            int checkedStock = 0;
            String checkStock = "SELECT stock FROM product WHERE prod_id = '" + prodID + "'";

            prepare = connect.prepareStatement(checkStock);
            result = prepare.executeQuery();

            if (result.next()) {
                checkedStock = result.getInt("stock");
            }


            if(checkedStock == 0){

                String updateStock = "UPDATE product SET prod_name = '"
                        + labelName.getText() + "', type = '"
                        + type + "', stock = 0, price = " + price
                        + ", status =  '"
                        + "Unavailable" + "', image = '"
                        + prod_image + "', date = '"
                        + prod_date + "' WHERE prod_id = '"
                        + prodID+"'";
                prepare = connect.prepareStatement(updateStock);
                prepare.executeUpdate();
            }

            prepare = connect.prepareStatement(checkAvailable);
            result = prepare.executeQuery();

            if (result.next()) {
                check = result.getString("status");
            }

            if (!check.equals("Available") || quantity == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong :3");
                alert.showAndWait();
            } else {
                if (checkedStock < quantity) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This product is out of stock!");
                    alert.showAndWait();
                }else {
//                    prod_image = prod_image.replace("\\", "\\");

                    String insertData = "INSERT INTO customer " +
                            " (customer_id,prod_id, prod_name, type, quantity,price,date,image,em_username) " +
                            " VALUES(?,?,?,?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(data.cID));
                    prepare.setString(2,prodID);
                    prepare.setString(3, labelName.getText());
                    prepare.setString(4, type);
                    prepare.setString(5, String.valueOf(quantity));

                    totalPrice = (quantity * price);
                    prepare.setString(6, String.valueOf(totalPrice));

                    java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                    prepare.setString(7, String.valueOf(sqlDate));

                    prepare.setString(8,prod_image);
                    prepare.setString(9, data.username);

                    prepare.executeUpdate();

                    prod_image = prod_image.replace("\\", "\\\\");

                    int upStock = checkedStock - quantity;

                    String updateStock = "UPDATE product SET prod_name = '"
                            + labelName.getText() + "', type = '"
                            + type + "', stock = " + upStock + ", price = " + price
                            + ", status =  '"
                            + check + "', image = '"
                            + prod_image + "', date = '"
                            + prod_date + "' WHERE prod_id = '"
                            + prodID+"'";

                    prepare = connect.prepareStatement(updateStock);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    mDishesController.menuGetTotal();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }

}
