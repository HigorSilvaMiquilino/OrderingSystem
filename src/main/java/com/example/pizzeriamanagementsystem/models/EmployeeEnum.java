package com.example.pizzeriamanagementsystem.models;

public enum EmployeeEnum {
    MANAGER(1,"MANAGER"),
    CHEF(2,"CHEF"),
    SERVERS(3,"SERVERS"),
    DELIVERY_DRIVERS(4,"DELIVERY_DRIVERS"),
    CASHIERS(5,"CASHIERS"),
    MAINTENANCE(6,"MAINTENANCE");

    private final int VALUE;
    private final String PROFESSIONAL ;

     EmployeeEnum(int value, String PROFESSIONAL){
        this.VALUE = value;
        this.PROFESSIONAL = PROFESSIONAL;
    }

    public static EmployeeEnum getEmployee(String PROFESSIONAL){
        for(EmployeeEnum enumeration: values()){
            if (enumeration.getPROFESSIONAL().equals(PROFESSIONAL))
            return enumeration;
        }
        return null;
    }

    public String getAllProfessional(EmployeeEnum employeeEnum){
        for (EmployeeEnum enumeration :values() ){
            return enumeration.getPROFESSIONAL();
        }
        return null;
    }

    public int getVALUE(){
        return this.VALUE;
    }

    public String getPROFESSIONAL(){
        return this.PROFESSIONAL;
    }

    @Override
    public String toString() {
        return PROFESSIONAL;
    }
}
