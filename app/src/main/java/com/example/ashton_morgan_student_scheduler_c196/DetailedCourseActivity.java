package com.example.ashton_morgan_student_scheduler_c196;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DetailedCourseActivity extends AppCompatActivity {
    private SchedulerViewModel schedulerViewModel;
    private int termIdForCourse;
    private List<Course> coursesForTerm = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final DetailedCourseAdapter adapter = new DetailedCourseAdapter();
        recyclerView.setAdapter(adapter);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schedulerViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for (Course c : courses) {
                    if (c.getTermID() == termIdForCourse) {
                        coursesForTerm.add(c);
                    }
                }
                adapter.setCourses(coursesForTerm);
            }
        });



    }






}
