package com.example.orderingsystem.models;

import java.util.Objects;

public class ClientModel extends UserModel {
        ClientEnum clientEnum;

    public ClientModel(String firstName, String lastName, String email, String password, String phoneNumber, Integer age, ClientEnum clientEnum) {
        super(firstName, lastName, email, password, phoneNumber, age);
        this.clientEnum = clientEnum;
    }

    public ClientModel(Integer id, String firstName, String lastName, String email,
                       String password, String phoneNumber, Integer age, ClientEnum clientEnum) {

        super(id, firstName, lastName, email, password, phoneNumber, age);
        this.clientEnum = clientEnum;
    }

    public ClientEnum getClientEnum() {
        return clientEnum;
    }

    public void setClientEnum(ClientEnum clientEnum) {
        this.clientEnum = clientEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientModel that = (ClientModel) o;
        return clientEnum == that.clientEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientEnum);
    }



    @Override
    public String toString() {
        return (super.toString() + "ClientModel{" +
                "clientEnum=" + clientEnum +
                '}');



    }
}
