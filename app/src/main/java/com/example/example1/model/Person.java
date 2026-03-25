package com.example.example1.model;

public class Person {

    private String firstName;
    private String lastName;
    private String phone;

    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "First name=" + firstName +
                ", Last name=" + lastName +
                ", Phone=" + phone;
    }
}