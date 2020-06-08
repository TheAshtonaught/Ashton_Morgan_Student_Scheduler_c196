package com.example.ashton_morgan_student_scheduler_c196;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailedCourseActivity extends AppCompatActivity {
    private SchedulerViewModel schedulerViewModel;
    public static final String EXTRA_COURSE_TERM_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_TERM_ID";
    public static final String EXTRA_COURSE_TERM_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_TERM_TITLE";

    private List<Course> coursesForTerm = new ArrayList<>();
    private int termID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course);

        FloatingActionButton addCourseButton = findViewById(R.id.add_course_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: ADD COURSE SCREEN
            }
        });

        Intent intent = getIntent();
        termID = intent.getIntExtra(EXTRA_COURSE_TERM_ID, -99);
        String termTitle = intent.getStringExtra(EXTRA_COURSE_TERM_TITLE);
        setTitle(termTitle);


        RecyclerView recyclerView = findViewById(R.id.detailed_course_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final DetailedCourseAdapter adapter = new DetailedCourseAdapter();
        recyclerView.setAdapter(adapter);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schedulerViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for (Course c : courses) {
                    if (c.getTermID() == termID) {
                        coursesForTerm.add(c);
                    }
                }
                adapter.setCourses(coursesForTerm);
            }
        });

        adapter.setOnAssessmentButtonClickListener(new DetailedCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                //TODO: Go to assessment for course

            }
        });

        adapter.setOnShareButtonClickListener(new DetailedCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = course.getNotes();
                String shareSub = "Notes";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));

            }
        });

        adapter.setOnEditButtonClickListener(new DetailedCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                //TODO: Go to add/edit course screen

            }
        });



        //TODO: swipe to delete



    }






}
