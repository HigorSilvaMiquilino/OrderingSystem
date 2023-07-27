package com.example.orderingsystem.orderingdatabase.service;

import com.example.orderingsystem.models.ProductModel;
import com.example.orderingsystem.orderingdatabase.repository.ProductRepository;

import java.util.List;

public class ProductService {

    public static void save(ProductModel productModel){
        ProductRepository.save(productModel);
    }

    public static List<ProductModel> findAll(){
       return ProductRepository.findAll();
    }

    public static List<ProductModel> findByName(String nameTextField){
       return ProductRepository.findByName(nameTextField);
    }
}
