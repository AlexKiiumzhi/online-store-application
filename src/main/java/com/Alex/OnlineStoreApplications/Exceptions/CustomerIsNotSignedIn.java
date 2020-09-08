package com.Alex.OnlineStoreApplications.Exceptions;

public class CustomerIsNotSignedIn extends RuntimeException{

    public CustomerIsNotSignedIn() {
        super("Customer is not signed in");
    }
}
