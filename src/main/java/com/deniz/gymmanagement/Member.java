package com.deniz.gymmanagement;


public class Member {
    private String FirstName;
    private String LastName;
    private String Address;
    private String Phone;

    public Member(String firstName, String lastName, String address, String phone) {
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Phone = phone;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }
}
