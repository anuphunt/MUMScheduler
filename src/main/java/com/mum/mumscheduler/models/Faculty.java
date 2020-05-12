package com.mum.mumscheduler.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Document(collection = "faculty")
public class Faculty {
    @Id
    private String facultyId;
    private String username;
    private String password;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String joinedDate;
    private List<Course> specializations;
    private List<Block> blocks;

    public Faculty(){}
    public Faculty(String facultyId, String username, String password, String joinedDate, List<Course> specializations, List<Block> blocks){
        this.username = username;
        this.password = password;
        this.facultyId = facultyId;
        this.joinedDate = joinedDate;
        this.specializations = specializations;
        this.blocks = blocks;
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

    //Getters
    public String getFacultyId(){return facultyId;}
    public String getName(){return name;}
    public String getJoinedDate(){return joinedDate;}
    public List<Course> getSpecializations(){return  specializations;}
    public List<Block> getBlocks(){return blocks;}

    //Setters
    public void setName(String name){this.name = name;}
    public void setFacultyId(String facultyId){this.facultyId = facultyId;}
    public void setJoinedDate(String joinedDate){this.joinedDate = joinedDate;}
    public void setSpecializations(List<Course> specializations){this.specializations = specializations;}
    public void setBlocks(List<Block> blocks){this.blocks = blocks;}





}
