package com.example.orderingsystem.orderingdatabase.repository;

import com.example.orderingsystem.models.ProductEnum;
import com.example.orderingsystem.models.ProductModel;
import com.example.orderingsystem.orderingdatabase.conn.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static void save(ProductModel productModel){
        String sql = ("INSERT INTO `orderingdb`.`ProductModel` " +
                "(`name`,`price`,`product_enum_type`) " +
                "VALUES ('%s','%d','%s');")
                .formatted(productModel.getName(),productModel.getPrice(),productModel.getCategory().getTYPE_PRODUCT());

        try ( Connection connection = ConnectionFactory.getConnection();
              Statement statement = connection.createStatement()){
              int rowsAffected = statement.executeUpdate(sql);
              System.out.println("Rows Affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    public static List<ProductModel> findAll(){
        String sql = "SELECT * FROM orderingdb.productmodel";
        List<ProductModel> products = new ArrayList<>();
        try ( Connection connection = ConnectionFactory.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(sql);
              ResultSet resultSet = preparedStatement.executeQuery()) {

             while (resultSet.next()){
                 final int id = resultSet.getInt("id");
                 final String name = resultSet.getString("name");
                 final int price = resultSet.getInt("price");
                 final String product_enum_type = resultSet.getString("product_enum_type");

                 ProductEnum productEnum = switch (product_enum_type) {
                     case "FOOD" -> ProductEnum.FOOD;
                     case "DRINK" -> ProductEnum.DRINK;
                     case "DESSERT" -> ProductEnum.DESSERT;
                     default -> null;
                 };


                 final ProductModel productModel = ProductModel.ProductBuilder.aProduct()
                         .id(id)
                         .name(name)
                         .price(price)
                         .category(productEnum)
                         .build();
                 products.add(productModel);
             }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    //------------------------------------------------------------------------------------------------------------------

    public static List<ProductModel> findByName(String nameTextField){
        String sql = "SELECT * FROM orderingdb.productmodel where name like ?";
        List<ProductModel> products = new ArrayList<>();

         try ( Connection connection = ConnectionFactory.getConnection();
               PreparedStatement preparedStatement = ProductRepository.createPreparedStatement(connection, sql, nameTextField);
               ResultSet resultSet = preparedStatement.executeQuery()){

             while (resultSet.next()){
                 final int id = resultSet.getInt("id");
                 final String name = resultSet.getString("name");
                 final int price = resultSet.getInt("price");
                 final String product_enum_type = resultSet.getString("product_enum_type");

                 ProductEnum productEnum = switch (product_enum_type) {
                     case "FOOD" -> ProductEnum.FOOD;
                     case "DRINK" -> ProductEnum.DRINK;
                     case "DESSERT" -> ProductEnum.DESSERT;
                     default -> null;
                 };

                 final ProductModel productModel = ProductModel.ProductBuilder.aProduct()
                         .id(id)
                         .name(name)
                         .price(price)
                         .category(productEnum)
                         .build();
                 products.add(productModel);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return products;
    }

    private static PreparedStatement createPreparedStatement(Connection connection, String sql, String name) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        return preparedStatement;
    }

    //------------------------------------------------------------------------------------------------------------------


}
