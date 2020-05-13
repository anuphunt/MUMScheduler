package com.mum.mumscheduler.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "entry")
public class Entry {

    @Id
    private String intake;
    private int noOfBlocks;
    private int noOfFaculties;
    private int noOfStudents;
    private int noOfFppStudents;
    private int noOfMppStudents;
    private int numOfUSStudents;

    private List<Block> blocks;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    public Entry() {

    }

    public Entry(String intake, int noOfBlocks, int noOfFaculties, int noOfStudents, String startDate, String endDate) {
        this.intake = intake;
        this.noOfBlocks = noOfBlocks;
        this.noOfFaculties = noOfFaculties;
        this.noOfStudents = noOfStudents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public int getNoOfBlocks() {
        return noOfBlocks;
    }

    public void setNoOfBlocks(int noOfBlocks) {
        this.noOfBlocks = noOfBlocks;
    }

    public int getNoOfFaculties() {
        return noOfFaculties;
    }

    public void setNoOfFaculties(int noOfFaculties) {
        this.noOfFaculties = noOfFaculties;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}