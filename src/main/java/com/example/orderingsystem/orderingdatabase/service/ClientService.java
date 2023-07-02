package com.example.orderingsystem.orderingdatabase.service;

import com.example.orderingsystem.models.ClientModel;
import com.example.orderingsystem.orderingdatabase.repository.ClientRepository;

import java.util.List;

public class ClientService {

    public static void save(ClientModel clientModel){
        ClientRepository.save(clientModel);
    }

    public static List<ClientModel> findAll(){
        return ClientRepository.findAll();
    }

    public static List<ClientModel> findByName(String name){
        return ClientRepository.findByName(name);
    }

    public static List<ClientModel> findByEmail(String emailTextField){
       return ClientRepository.findByEmail(emailTextField);
    }
}
