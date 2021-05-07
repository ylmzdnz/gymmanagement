package com.deniz.gymmanagement;


public class Club {
    private String Name;
    private String Address;
    private String Phone;

    public Club(String name, String address, String phone) {
        Name = name;
        Address = address;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }
}
