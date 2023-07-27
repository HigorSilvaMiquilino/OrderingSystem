package com.example.orderingsystem.controller;

import com.example.orderingsystem.CustomersData;
import com.example.orderingsystem.ProductData;
import com.example.orderingsystem.data;
import com.example.orderingsystem.orderingdatabase.conn.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class MainDishesController implements Initializable {
    @FXML
    public Label customerLabel;
    @FXML
    public ImageView imageCustomer;
    @FXML
    private Label InventoryLabel;

    @FXML
    private Label menu_change;

    @FXML
    private Label menu_total;

    @FXML
    private TextField menu_amount;

    @FXML
    private Button addBtnInventory;

    @FXML
    private BorderPane borderPaneMainDishes;

    @FXML
    private Button clearBtnInventory;

    @FXML
    private Pane contentPane;

    @FXML
    private TableColumn<ProductData, String> date_column_inventory;

    @FXML
    private Button deleteBtnInventory;

    @FXML
    private ImageView imageInventory;

    @FXML
    private ImageView imageMenu;

    @FXML
    private ImageView imageViewInventory;

    @FXML
    private Button importBtnInventory;

    @FXML
    private AnchorPane inventoryPane;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private Label menuLabel;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private Label orderingAppLabel;

    @FXML
    private TextField priceTextField;

    @FXML
    private TableColumn<ProductData, String> price_column_inventory;

    @FXML
    private TextField productIdTextField;

    @FXML
    private TableColumn<ProductData, String> productId_column_inventory;

    @FXML
    private TableColumn<ProductData, String> description_column_inventory;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TableColumn<ProductData, String> productName_column_inventory;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private TableColumn<ProductData, String> status_column_inventory;

    @FXML
    private TextField stockTextField;

    @FXML
    private TableColumn<ProductData, String> stock_column_inventory;

    @FXML
    private TableView<ProductData> tableViewInventory;

    @FXML
    private TableView<ProductData> tableViewMenu;

    @FXML
    private TableColumn<ProductData, String> menu_col_price;

    @FXML
    private TableColumn<ProductData, String> menu_col_productName;

    @FXML
    private TableColumn<ProductData, String> menu_col_quantity;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TableColumn<ProductData, String> type_column_inventory;

    @FXML
    private Button updateBtnInventory;

    @FXML
    private VBox vBoxMainDishes;

    @FXML
    private TextArea textAreaDescription;

    @FXML
    private AnchorPane customerPane;

    @FXML
    private TableView<CustomersData> customerTableView;

    @FXML
    private TableColumn<CustomersData, String> cashier_column_Customers;

    @FXML
    private TableColumn<CustomersData, String> customerID_column_Customers;

    @FXML
    private TableColumn<CustomersData, String> total_column_Customers;


    @FXML
    private TableColumn<CustomersData, String> date_column_Customers;



    Alert alert;

    private ObservableList<ProductData> cardListData = FXCollections.observableArrayList();

    private Connection connection;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        inventoryTypeList();
        inventoryStatusList();
        inventoryShowData();

        menuDisplayCard();
        menuGetOrder();
        menuDisplayTotal();
        menuShowOrderData();

        customerShowData();
    }


    public void inventoryClicked(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
        inventoryPane.setVisible(true);
        customerPane.setVisible(false);
    }

    public void menuClicked(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
        inventoryPane.setVisible(false);
        customerPane.setVisible(false);

        menuDisplayCard();
        menuDisplayTotal();
        menuShowOrderData();
    }

    public void customerClicked(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
        inventoryPane.setVisible(false);
        customerPane.setVisible(true);

        customerShowData();
    }

    private final String[] typeList = {"Meals", "Drinks"};
    public void inventoryTypeList() {
        List<String> typeL = new ArrayList<>(Arrays.asList(typeList));
        ObservableList listData = FXCollections.observableArrayList(typeL);
        typeChoiceBox.setItems(listData);
        typeChoiceBox.setValue("Drinks");

    }

    private final String[] statusList = {"Available", "Unavailable"};
    public void inventoryStatusList() {
        List<String> statusL = new ArrayList<>();

        Collections.addAll(statusL, statusList);

        ObservableList listData = FXCollections.observableArrayList(statusL);
        statusChoiceBox.setItems(listData);
        statusChoiceBox.setValue("Unavailable");
    }


    //------------------------------------------------------------------------------------------------------------------
    // Inventory

    public void inventoryImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(contentPane.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString(), 120, 120, false, true);

            imageViewInventory.setImage(image);
        }
    }

    public void inventoryAddBtn() {
        if (productIdTextField.getText().isEmpty()
                || productNameTextField.getText().isEmpty()
                || typeChoiceBox.getSelectionModel().getSelectedItem() == null
                || stockTextField.getText().isEmpty()
                || priceTextField.getText().isEmpty()
                || statusChoiceBox.getSelectionModel().getSelectedItem() == null
                || data.path == null
                || textAreaDescription.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {

            String checkProdID = "SELECT prod_id FROM product WHERE prod_id = '" + productIdTextField.getText() + "'";
            try {
                connection = ConnectionFactory.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                result = statement.executeQuery(checkProdID);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(productIdTextField.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO product"
                            + "(prod_id, prod_name, type, stock, price, status, image, date, description)"
                            + "VALUES (?,?,?,?,?,?,?,?,?)";

                    prepare = connection.prepareStatement(insertData);
                    prepare.setString(1, productIdTextField.getText());
                    prepare.setString(2, productNameTextField.getText());
                    prepare.setString(3, (String) typeChoiceBox.getSelectionModel().getSelectedItem());
                    prepare.setString(4, stockTextField.getText());
                    prepare.setString(5, priceTextField.getText());
                    prepare.setString(6, (String) statusChoiceBox.getSelectionModel().getSelectedItem());

                    String path = data.path;
                    path = path.replace("\\", "\\\\");
                    prepare.setString(7, path);

                    java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.setString(9,textAreaDescription.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ObservableList<ProductData> inventoryDataList() {

        ObservableList<ProductData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";

        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData productData;
            while (result.next()) {
                productData = new ProductData(result.getInt("id"),
                        (result.getString("prod_id")),
                        (result.getString("prod_name")),
                        (result.getString("type")),
                        (result.getInt("stock")),
                        (result.getDouble("price")),
                        (result.getString("status")),
                        (result.getString("image")),
                        (result.getDate("date")),
                        (result.getString("description")));
                listData.add(productData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<ProductData> inventoryListData;

    public void inventoryShowData() {
        inventoryListData = inventoryDataList();

        productId_column_inventory.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName_column_inventory.setCellValueFactory(new PropertyValueFactory<>("productName"));
        type_column_inventory.setCellValueFactory(new PropertyValueFactory<>("type"));
        stock_column_inventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        price_column_inventory.setCellValueFactory(new PropertyValueFactory<>("price"));
        status_column_inventory.setCellValueFactory(new PropertyValueFactory<>("status"));
        date_column_inventory.setCellValueFactory(new PropertyValueFactory<>("date"));
        description_column_inventory.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableViewInventory.setItems(inventoryListData);
    }

    public void inventoryClearBtn() {

        productIdTextField.setText("");
        productNameTextField.setText("");
        typeChoiceBox.setValue("Drinks");
        stockTextField.setText("");
        priceTextField.setText("");
        statusChoiceBox.setValue("Unavailable");
        data.path = "";
        data.id = 0;
        imageViewInventory.setImage(null);
        textAreaDescription.setText("");
    }

    public void inventorySelectData() {
        ProductData productData = tableViewInventory.getSelectionModel().getSelectedItem();
        int num = tableViewInventory.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        productIdTextField.setText(productData.getProductId());
        productNameTextField.setText(productData.getProductName());
        stockTextField.setText(String.valueOf(productData.getStock()));
        priceTextField.setText(String.valueOf(productData.getPrice()));
        typeChoiceBox.setValue(productData.getType());
        statusChoiceBox.setValue(productData.getStatus());


        data.path = productData.getImage();

        String path = "File:" + productData.getImage();
        Image image = new Image(path, 120, 120, false, true);
        imageViewInventory.setImage(image);

        data.date = String.valueOf(productData.getDate());
        data.id = productData.getId();
        textAreaDescription.setText(productData.getDescription());
    }

    public void inventoryUpdateBtn() {
        if (productIdTextField.getText().isEmpty()
                || productNameTextField.getText().isEmpty()
                || typeChoiceBox.getSelectionModel().getSelectedItem() == null
                || stockTextField.getText().isEmpty()
                || priceTextField.getText().isEmpty()
                || statusChoiceBox.getSelectionModel().getSelectedItem() == null
                || data.path == null
                || data.id == 0
                || textAreaDescription.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE product SET prod_id = '"
                    + productIdTextField.getText() + "', prod_name = '"
                    + productNameTextField.getText() + "', type = '"
                    + typeChoiceBox.getSelectionModel().getSelectedItem() + "', stock = '"
                    + stockTextField.getText() + "', price = '"
                    + priceTextField.getText() + "', status = '"
                    + statusChoiceBox.getSelectionModel().getSelectedItem() + "', image = '"
                    + path + "', date = '" + data.date
                    + "', description = '" + textAreaDescription.getText() + "' WHERE id = " + data.id;

            try {
                connection = ConnectionFactory.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText("I want to make sure what it does");
                alert.setContentText("Are you sure you want to UPDATE Product ID:  " + productIdTextField.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    prepare = connection.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void inventoryDeleteBtn() {
        if (data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Product ID: " + productIdTextField.getText() + "?");
            Optional<ButtonType> optional = alert.showAndWait();

            if (optional.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM product WHERE id = " + data.id;
                try {
                    prepare = connection.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Menu

    public ObservableList<ProductData> menuGetData()  {

        String sql = "SELECT * FROM product";

        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prod;

            while (result.next()) {
                prod = new ProductData(result.getInt("id")
                        , result.getString("prod_id")
                        , result.getString("prod_name")
                        , result.getString("type")
                        , result.getInt("stock")
                        , result.getDouble("price")
                        , result.getString("image")
                        , result.getDate("date")
                        , result.getString("description"));
                listData.add(prod);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listData;
    }

    public void menuDisplayCard() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menuGridPane.getChildren().clear();
        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/example/orderingsystem/card.fxml"));
                Pane pane = load.load();
                CardController cardC = load.getController();

                cardC.setProductData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menuGridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<ProductData> menuGetOrder() {
        customerId();
        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;

        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prod;

            while (result.next()) {
                prod = new ProductData(result.getInt("id")
                        , result.getString("prod_id")
                        , result.getString("prod_name")
                        , result.getString("type")
                        , result.getInt("quantity")
                        , result.getDouble("price")
                        , result.getString("image")
                        , result.getDate("date"));
                listData.add(prod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listData;
    }

    private ObservableList<ProductData> menuOrderListData;

    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();


        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewMenu.setItems(menuOrderListData);

    }

    private int cID;

    public void customerId() {

        String sql = "SELECT MAX(customer_id) FROM customer";
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = connection.prepareStatement(checkCID);
            result = prepare.executeQuery();

            int checkId = 0;
            if (result.next()) {
                checkId = result.getInt("MAX(customer_id)");
            }

            if (cID == 0) {
                cID += 1;
            } else if (cID == checkId) {
                cID += 1;
            }

            data.cID = cID;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double totalP;

    public void menuGetTotal() {
        customerId();
        String total = "SELECT SUM(price) FROM customer WHERE customer_id = " + cID;

        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            prepare = connection.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(price)");
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void menuDisplayTotal() {
        menuGetTotal();
        menu_total.setText("$" + totalP);
    }


    private double amount;
    private double change;

    public void menuAmount() {
        menuGetTotal();

        if (menu_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            amount = Double.parseDouble(menu_amount.getText());
            if (amount < totalP) {
                menu_amount.setText("");
            } else {
                change = (amount - totalP);
                menu_change.setText("$" + change);
            }
        }
    }

    public void menuPayBtn() {

        if (totalP == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            menuGetTotal();
            String insertPay = "INSERT INTO receipt (customer_id,total,date,em_username) " +
                    "VALUES(?,?,?,?)";

            try {
                connection = ConnectionFactory.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {

                if (amount == 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Something wrong :3");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure?");
                    Optional<ButtonType> optional = alert.showAndWait();

                    if (optional.get().equals(ButtonType.OK)) {
                        customerId();
                        menuGetTotal();
                        prepare = connection.prepareStatement(insertPay);
                        prepare.setString(1, String.valueOf(cID));
                        prepare.setString(2, String.valueOf(totalP));

                        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                        prepare.setString(3, String.valueOf(sqlDate));
                        prepare.setString(4, data.username);

                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successful.");
                        alert.showAndWait();

                        menuShowOrderData();
                        menuRestart();
                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        menu_total.setText("$0.0");
        menu_amount.setText("");
        menu_change.setText("$0.0");
    }

    private int getId;

    public void menuSelectOrder() {
        ProductData prod = tableViewMenu.getSelectionModel().getSelectedItem();
        int num = tableViewMenu.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        getId = prod.getId();
    }

    public void menuRemoveBtn() {

        if (getId == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            String deleteData = "DELETE FROM customer WHERE id = " + getId;
            try {
                connection = ConnectionFactory.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete thie order?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    prepare = connection.prepareStatement(deleteData);
                    prepare.executeUpdate();
                }

                menuShowOrderData();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }



    //------------------------------------------------------------------------------------------------------------------
    //Customers

    public ObservableList<CustomersData> customersDataList() {

        ObservableList<CustomersData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            CustomersData cData;

            while (result.next()) {
                cData = new CustomersData(result.getInt("id")
                        , result.getInt("customer_id")
                        , result.getDouble("total")
                        , result.getDate("date")
                        , result.getString("em_username"));
                listData.add(cData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<CustomersData> customerListData;

    public void customerShowData() {
        customerListData = customersDataList();

        customerID_column_Customers.setCellValueFactory(new PropertyValueFactory<>("id"));
        total_column_Customers.setCellValueFactory(new PropertyValueFactory<>("total"));
        date_column_Customers.setCellValueFactory(new PropertyValueFactory<>("date"));
        cashier_column_Customers.setCellValueFactory(new PropertyValueFactory<>("emUsername"));

        customerTableView.setItems(customerListData);

    }

    //------------------------------------------------------------------------------------------------------------------
}
