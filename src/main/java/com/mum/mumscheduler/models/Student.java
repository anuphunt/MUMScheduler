package com.mum.mumscheduler.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "student")
@Getter
@Setter
public class Student {
    @Id
    String id;
    String username;
    String password;
    String name;
    String entry;
    String address;
    String email;
    String track;
    String nationality;
    List<Section> sections;

    public Student() {
    }

    public Student(String id, String username, String password, String name, String entry, String address, String email, String track, String nationality, List<Section> sections) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.entry = entry;
        this.address = address;
        this.email = email;
        this.track = track;
        this.nationality = nationality;
        this.sections = sections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String isIntlStudent() {
        return nationality;
    }

    public void setIntlStudent(String nationality) {
        this.nationality = nationality;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
