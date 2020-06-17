package com.example.ashton_morgan_student_scheduler_c196;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Button termsButton;
    private Button coursesButton;
    private Button assessmentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Student Scheduler");

        termsButton = findViewById(R.id.all_terms_button);
        coursesButton = findViewById(R.id.all_courses_button);
        assessmentsButton = findViewById(R.id.all_assessments_button);

        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AllCoursesActivity.class);
                startActivity(intent);
            }
        });

        assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AllAssessmentsActivity.class);
                startActivity(intent);

            }
        });


    }
}
