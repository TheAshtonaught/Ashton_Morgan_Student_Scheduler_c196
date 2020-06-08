package com.example.ashton_morgan_student_scheduler_c196;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment_table")
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String dueDate;
    private String title;
    private String type;
    private String courseTitle;
    private int courseId;

    public Assessment(String dueDate, String title, String type, String courseTitle, int courseId) {
        this.dueDate = dueDate;
        this.title = title;
        this.type = type;
        this.courseTitle = courseTitle;
        this.courseId = courseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCourseId() {
        return courseId;
    }
}
