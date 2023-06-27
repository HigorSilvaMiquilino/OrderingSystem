package com.example.orderingsystem.orderingdatabase.repository;

import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.conn.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientRepository {
    public static void save(ClientModel clientModel){
        String sql = ("INSERT INTO `orderingdb`.`ClientModel` " +
                    "(`firstName`,`lastName`,`email`,`password`,`phoneNumber`," +
                     "`age`,`client_enum_value`,`client_enum_type_client`) " +
                     "VALUES ('%s','%s','%s','%s','%s','%d','%d','%s');")
                .formatted(clientModel.getFirstName(),clientModel.getLastName(), clientModel.getEmail(),
                           clientModel.getPassword(), clientModel.getPhoneNumber(), clientModel.getAge(),
                           clientModel.getClientEnum().getVALUE(), clientModel.getClientEnum().getTYPE_CLIENT());

        try (final Connection connection = ConnectionFactory.getConnection();
             final Statement execute = connection.createStatement()) {
             final int rowsAffected = execute.executeUpdate(sql);
             System.out.println("Rows Affected: "+rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------
}
