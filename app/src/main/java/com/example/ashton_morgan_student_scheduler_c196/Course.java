package com.example.ashton_morgan_student_scheduler_c196;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String title;
    private String startDate;
    private String endDate;
    private String status;
    private String mentor;
    private String assessment;
    private String notes;

    public Course(String title, String startDate, String endDate, String status, String mentor, String assessment) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.mentor = mentor;
        this.assessment = assessment;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getMentor() {
        return mentor;
    }

    public String getAssessment() {
        return assessment;
    }
}
