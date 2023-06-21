package com.example.pizzeriamanagementsystem.models;

import javafx.scene.control.DatePicker;

import java.util.Objects;

public class ClientModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    private ClientEnum clientEnum;

    public ClientModel() {
    }

    public ClientModel(Integer id, String firstName, String lastName, String email,
                         String password, Integer age, ClientEnum clientEnum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.clientEnum = clientEnum;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", clientEnum=" + clientEnum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientModel that = (ClientModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(email, that.email)
                && Objects.equals(password, that.password)
                && Objects.equals(age, that.age)
                && clientEnum == that.clientEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, age, clientEnum);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void setClientEnum(ClientEnum clientEnum) {
        this.clientEnum = clientEnum;
    }
}
