package com.example.ashton_morgan_student_scheduler_c196;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AddCourseActivity extends AppCompatActivity {
    public static final String EXTRA_COURSE_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_TITLE";
    public static final String EXTRA_COURSE_START_DATE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_START_DATE";
    public static final String EXTRA_COURSE_END_DATE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_END_DATE";
    public static final String EXTRA_COURSE_STATUS =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_STATUS";
    public static final String EXTRA_COURSE_MENTOR =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_MENTOR";
    public static final String EXTRA_COURSE_NOTES =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_NOTES";
    public static final String EXTRA_COURSE_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_ID";
    public static final String EXTRA_TERM_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_TERM_ID";

    private EditText editCourseTitle;
    private EditText editCourseStartDate;
    private EditText editCourseEndDate;
    private EditText editCourseStatus;
    private EditText editCourseMentor;
    private EditText editCourseNotes;

    private int termID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        setUI();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COURSE_ID)) {
            setTitle("Edit Course");
            populateCourseData(intent);

        } else {
            setTitle("Add Course");
        }
        termID = intent.getIntExtra(EXTRA_TERM_ID, -99);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_term_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_term:
                saveCourse();
                return true;
            default:
                cancelAddCourse();
                return true;
        }
    }

    public void saveCourse() {
        String courseTitle = editCourseTitle.getText().toString();
        String courseStartDateString = editCourseStartDate.getText().toString();
        String courseEndDateString = editCourseEndDate.getText().toString();
        String courseStatus = editCourseStatus.getText().toString();
        String courseMentor = editCourseMentor.getText().toString();
        String courseNotes = editCourseNotes.getText().toString();

        if (courseTitle.trim().isEmpty() || courseStatus.trim().isEmpty() ||
                courseNotes.trim().isEmpty() || courseMentor.trim().isEmpty()) {
            Toast.makeText(this, "Fields Can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(courseStartDateString);
            Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(courseEndDateString);

            Intent data = new Intent();
            data.putExtra(EXTRA_COURSE_TITLE, courseTitle);
            data.putExtra(EXTRA_COURSE_START_DATE, courseStartDateString);
            data.putExtra(EXTRA_COURSE_END_DATE, courseEndDateString);
            data.putExtra(EXTRA_COURSE_STATUS, courseStatus);
            data.putExtra(EXTRA_COURSE_MENTOR, courseMentor);
            data.putExtra(EXTRA_COURSE_NOTES, courseNotes);


            int id = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_COURSE_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();

        } catch (ParseException e) {
            Toast.makeText(this, "Enter a valid date format MM/dd/yyyy", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }
    }

    public void cancelAddCourse() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void populateCourseData(Intent intent) {
        editCourseTitle.setText(intent.getStringExtra(EXTRA_COURSE_TITLE));
        editCourseStartDate.setText(intent.getStringExtra(EXTRA_COURSE_START_DATE));
        editCourseEndDate.setText(intent.getStringExtra(EXTRA_COURSE_END_DATE));
        editCourseStatus.setText(intent.getStringExtra(EXTRA_COURSE_STATUS));
        editCourseMentor.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR));
        editCourseNotes.setText(intent.getStringExtra(EXTRA_COURSE_NOTES));


    }

    private void setUI() {
        editCourseTitle = findViewById(R.id.edit_text_course_title);
        editCourseStartDate = findViewById(R.id.edit_text_course_start_date);
        editCourseEndDate = findViewById(R.id.edit_text_course_end_date);
        editCourseStatus = findViewById(R.id.edit_text_course_status);
        editCourseMentor = findViewById(R.id.edit_text_course_mentor);
        editCourseNotes = findViewById(R.id.edit_text_course_notes);

    }




}

















































