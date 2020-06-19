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

import java.util.List;

public class AllCoursesActivity extends AppCompatActivity {
    public static final String EXTRA_SEC_TO_NOTIFICATION =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_SEC_TO_NOTIFICATION";
    private SchedulerViewModel schedulerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
        setTitle("All Courses");

        RecyclerView recyclerView = findViewById(R.id.all_courses_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BasicCourseAdapter adapter = new BasicCourseAdapter();
        recyclerView.setAdapter(adapter);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schedulerViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.setCourses(courses);
            }
        });

        FloatingActionButton addCourseFromBasicButton = findViewById(R.id.basic_add_course_button);
        addCourseFromBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCoursesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        adapter.setOnCourseStartAlertButtonClickListener(new BasicCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {

                
                startService(new Intent(AllCoursesActivity.this, NotificationService.class));
            }
        });

        adapter.setOnCourseEndAlertButtonClickListener(new BasicCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {

            }
        });
    }
}

























