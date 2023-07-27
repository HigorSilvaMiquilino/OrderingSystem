package com.example.orderingsystem.models;

import java.util.Objects;

public class ProductModel {
    private Integer id;
    private String name;
    private Integer price;
    private ProductEnum category;


    public static final class ProductBuilder  {
        private Integer id;
        private String name;
        private Integer price;
        private ProductEnum category;

        public ProductBuilder(){
        }

        public static ProductBuilder aProduct(){
            return new ProductBuilder();
        }

        public ProductBuilder id(Integer id){
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder price(Integer price){
            this.price = price;
            return this;
        }

        public ProductBuilder category(ProductEnum category){
            this.category = category;
            return this;
        }


        public ProductModel build(){
            ProductModel productModel = new ProductModel();
            productModel.id = this.id;
            productModel.name = this.name;
            productModel.price = this.price;
            productModel.category = this.category;
            return productModel;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductEnum getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCategory(ProductEnum category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel productModel = (ProductModel) o;
        return Objects.equals(id, productModel.id)
                && Objects.equals(name, productModel.name)
                && Objects.equals(price, productModel.price)
                && category == productModel.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
