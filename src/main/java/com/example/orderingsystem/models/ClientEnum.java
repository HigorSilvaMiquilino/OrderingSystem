package com.example.orderingsystem.models;

public enum ClientEnum {

    NEW(1,"NEW"){
        @Override
        public double discountCalculator(double value){
            return value * 0.1;
        }
    },
    REGULAR(2,"REGULAR"){
        @Override
        public double discountCalculator(double value){
            return value * 0.3;
        }
    };

    private final int VALUE;
    private final String TYPE_CLIENT;

    ClientEnum(int value, String typeClient){
        this.VALUE = value;
        this.TYPE_CLIENT = typeClient;
    }

    public abstract double discountCalculator(double value);

    public String getAllTypes(){
        for (ClientEnum enumeration: values()) {
            return enumeration.getTYPE_CLIENT();
        }
        return null;
    }

    public int getAllValues(){
        for (ClientEnum enumeration: values()) {
            return enumeration.getVALUE();
        }
        return 0;
    }

    public int getVALUE(){
        return this.VALUE;
    }

    public String getTYPE_CLIENT(){
        return this.TYPE_CLIENT;
    }


    @Override
    public String toString() {
        return TYPE_CLIENT;
    }
}
