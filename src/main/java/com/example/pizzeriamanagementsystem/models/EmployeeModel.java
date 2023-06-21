package com.example.pizzeriamanagementsystem.models;

import javafx.scene.control.DatePicker;

import java.util.Objects;

public class EmployeeModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    private Double salary ;
    private EmployeeEnum employeeEnum;

    public EmployeeModel() {
    }

    public EmployeeModel(Integer id, String firstName, String lastName, String email,
                         String password, Integer age, Double salary, EmployeeEnum employeeEnum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.salary = salary;
        this.employeeEnum = employeeEnum;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", employeeEnum=" + employeeEnum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel that = (EmployeeModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(email, that.email)
                && Objects.equals(password, that.password)
                & Objects.equals(age, that.age)
                && Objects.equals(salary, that.salary)
                && employeeEnum == that.employeeEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, age, salary, employeeEnum);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public EmployeeEnum getEmployeeEnum() {
        return employeeEnum;
    }

    public void setEmployeeEnum(EmployeeEnum employeeEnum) {
        this.employeeEnum = employeeEnum;
    }
}
