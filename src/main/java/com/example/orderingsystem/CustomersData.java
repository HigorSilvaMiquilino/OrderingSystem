package com.example.orderingsystem;

import java.sql.Date;

public class CustomersData {

    private Integer id;
    private Integer customerId;
    private Double total;
    private Date date;
    private String emUsername;

    public CustomersData(Integer id, Integer customerId, Double total, Date date, String emUsername) {
        this.id = id;
        this.customerId = customerId;
        this.total = total;
        this.date = date;
        this.emUsername = emUsername;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public String getEmUsername() {
        return emUsername;
    }
}
