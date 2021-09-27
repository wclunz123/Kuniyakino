package com.example.kuniyakino.model;

public class Account {
    String name;
    String email;
    String password;
    String addressLine1;
    String addressLine2;
    long mobileNumber;
    String paymentMethod;
    String cardNo;

    public Account() {
    }

    public Account(String name, String email, String password, String addressLine1, String addressLine2, long mobileNumber, String paymentMethod, String cardNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.mobileNumber = mobileNumber;
        this.paymentMethod = paymentMethod;
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
