package com.example.orderingsystem.orderingdatabase.service;

import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.repository.ClientRepository;

public class ClientService {

    public static void save(ClientModel clientModel){
        ClientRepository.save(clientModel);
    }
}
