package com.Alex.OnlineStoreApplications.service.dto;

import javax.validation.constraints.*;

public class RegistrationDto {

    @Pattern(regexp="[A-Za-z]{3,100}")
    @NotEmpty
    private String firstName;
    @Pattern(regexp="[A-Za-z]{3,100}")
    @NotEmpty
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp="[A-Z0-9a-z]{8,30}")
    @Size(min = 8)
    private String password;
    @Pattern(regexp="[0-9]{10,20}")
    @NotEmpty
    private String phoneNumber;


    public String getFirstName() {
        return firstName;
    }

    public void setFirst_name(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLast_name(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhone_number(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
