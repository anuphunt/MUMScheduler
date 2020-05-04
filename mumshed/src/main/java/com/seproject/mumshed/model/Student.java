package com.seproject.mumshed.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {

    String id;
    String name;
    String entry;
    String address;
    String email;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEntry() {
        return entry;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

}
