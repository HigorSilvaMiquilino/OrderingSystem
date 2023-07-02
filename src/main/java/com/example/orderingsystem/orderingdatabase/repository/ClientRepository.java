package com.example.orderingsystem.orderingdatabase.repository;

import com.example.orderingsystem.models.ClientEnum;
import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.conn.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    public static void save(ClientModel clientModel) {
        String sql = ("INSERT INTO `orderingdb`.`ClientModel` " +
                "(`firstName`,`lastName`,`email`,`password`,`phoneNumber`," +
                "`age`,`client_enum_value`,`client_enum_type_client`) " +
                "VALUES ('%s','%s','%s','%s','%s','%d','%d','%s');")
                .formatted(clientModel.getFirstName(), clientModel.getLastName(), clientModel.getEmail(),
                        clientModel.getPassword(), clientModel.getPhoneNumber(), clientModel.getAge(),
                        clientModel.getClientEnum().getVALUE(), clientModel.getClientEnum().getTYPE_CLIENT());

        try (final Connection connection = ConnectionFactory.getConnection();
             final Statement execute = connection.createStatement()) {
            final int rowsAffected = execute.executeUpdate(sql);
            System.out.println("Rows Affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    public static List<ClientModel> findAll() {
        String sql = "SELECT * FROM orderingdb.clientmodel";
        List<ClientModel> clients = new ArrayList<>();
        try (final Connection connection = ConnectionFactory.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String firstName = resultSet.getString("firstName");
                final String lastName = resultSet.getString("lastName");
                final String email = resultSet.getString("email");
                final String password = resultSet.getString("password");
                final String phoneNumber = resultSet.getString("phoneNumber");
                final int age = resultSet.getInt("age");
                final int employee_enum_value = resultSet.getInt("client_enum_value");
                final String employee_enum_type_client = resultSet.getString("client_enum_type_client");

                ClientEnum clientEnum = null;
                if (employee_enum_value == 1 && employee_enum_type_client.equals("NEW")) {
                    clientEnum = ClientEnum.NEW;
                } else if (employee_enum_value == 2 && employee_enum_type_client.equals("REGULAR")) {
                    clientEnum = ClientEnum.REGULAR;
                }

                ClientModel clientModel = new ClientModel(id, firstName, lastName, email,
                        password, phoneNumber, age, clientEnum);
                clients.add(clientModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    //------------------------------------------------------------------------------------------------------------------


    public static List<ClientModel> findByName(String name) {
        String sql = "SELECT * FROM orderingdb.clientmodel where firstName like '%%%s%%';".formatted(name);
        List<ClientModel> clients = new ArrayList<>();
        try (final Connection connection = ConnectionFactory.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String firstName = resultSet.getString("firstName");
                final String lastName = resultSet.getString("lastName");
                final String email = resultSet.getString("email");
                final String password = resultSet.getString("password");
                final String phoneNumber = resultSet.getString("phoneNumber");
                final int age = resultSet.getInt("age");
                final int employee_enum_value = resultSet.getInt("client_enum_value");
                final String employee_enum_type_client = resultSet.getString("client_enum_type_client");

                ClientEnum clientEnum = null;
                if (employee_enum_value == 1 && employee_enum_type_client.equals("NEW")) {
                    clientEnum = ClientEnum.NEW;
                } else if (employee_enum_value == 2 && employee_enum_type_client.equals("REGULAR")) {
                    clientEnum = ClientEnum.REGULAR;
                }

                ClientModel clientModel = new ClientModel(id, firstName, lastName, email,
                        password, phoneNumber, age, clientEnum);
                clients.add(clientModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    //------------------------------------------------------------------------------------------------------------------

    public static List<ClientModel> findByEmail(String emailTextField) {
        String sql = "SELECT * FROM orderingdb.clientmodel where email like ?";
        List<ClientModel> clientModels = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = ClientRepository.createPreparedStatement(connection,sql,emailTextField);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String firstName = resultSet.getString("firstName");
                final String lastName = resultSet.getString("lastName");
                final String email = resultSet.getString("email");
                final String password = resultSet.getString("password");
                final String phoneNumber = resultSet.getString("phoneNumber");
                final int age = resultSet.getInt("age");
                final int employee_enum_value = resultSet.getInt("client_enum_value");
                final String employee_enum_type_client = resultSet.getString("client_enum_type_client");

                ClientEnum clientEnum = null;
                if (employee_enum_value == 1 && employee_enum_type_client.equals("NEW")) {
                    clientEnum = ClientEnum.NEW;
                } else if (employee_enum_value == 2 && employee_enum_type_client.equals("REGULAR")) {
                    clientEnum = ClientEnum.REGULAR;
                }

                ClientModel clientModel = new ClientModel(id, firstName, lastName, email,
                        password, phoneNumber, age, clientEnum);
                clientModels.add(clientModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return clientModels;
    }

    private static PreparedStatement createPreparedStatement(Connection connection, String sql, String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        return  preparedStatement;
    }

    //------------------------------------------------------------------------------------------------------------------
}