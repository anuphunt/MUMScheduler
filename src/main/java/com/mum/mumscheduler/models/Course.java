package com.mum.mumscheduler.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "courses")
public class Course {
    @Id
    private String courseId;
   @NotNull
    private String courseName;
    private String prerequisites;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    public Course(){
            }

    public Course(String courseId, String courseName, String prerequisites, String startDate, String endDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.prerequisites = prerequisites;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
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
