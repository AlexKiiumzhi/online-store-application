package com.Alex.OnlineStoreApplications.Exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("Customer not found");
    }
}
