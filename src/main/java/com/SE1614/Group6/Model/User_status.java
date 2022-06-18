package com.SE1614.Group6.Model;

public enum User_status {
    customer_new("New Customer"),
    longtime_customer("Longtime Customer"),
    potential_customer("Potential Customer");
    private final String displayValue;

    private User_status(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

