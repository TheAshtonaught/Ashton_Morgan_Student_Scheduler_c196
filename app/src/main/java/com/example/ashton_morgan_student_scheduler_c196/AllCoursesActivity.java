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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AllCoursesActivity extends AppCompatActivity {
    public static final String EXTRA_SEC_TO_NOTIFICATION =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_SEC_TO_NOTIFICATION";
    public static final String EXTRA_NOTIFICATION_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_NOTIFICATION_TITLE";
    public static final String EXTRA_NOTIFICATION_TEXT =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_NOTIFICATION_TEXT";
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

                Intent intent = new Intent(AllCoursesActivity.this, StartCourseNotificationService.class);
                //intent.putExtra(EXTRA_SEC_TO_NOTIFICATION, 12);
                intent.putExtra(EXTRA_NOTIFICATION_TITLE, "Course Starting");
                String notifText = course.getTitle() + " will begin on " + course.getStartDate();
                intent.putExtra(EXTRA_NOTIFICATION_TEXT, notifText);

                startService(intent);
            }
        });

        adapter.setOnCourseEndAlertButtonClickListener(new BasicCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {

                Intent intent = new Intent(AllCoursesActivity.this, EndCourseNotificationService.class);
                //intent.putExtra(EXTRA_SEC_TO_NOTIFICATION, 5);
                intent.putExtra(EXTRA_NOTIFICATION_TITLE, "Course Ending");
                String notifText = course.getTitle() + " will end on " + course.getEndDate();
                intent.putExtra(EXTRA_NOTIFICATION_TEXT, notifText);

                startService(intent);
            }
        });
    }


    //Function to schedule notification currently not implemented
    public int timeToNotify(String dateString) {
        int secToTrigger = 1;

        try {
            Date triggerDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
            Date today = new Date();

            long seconds = (triggerDate.getTime()-today.getTime())/1000;
            secToTrigger = (int) seconds;
        } catch (ParseException e) {
            e.printStackTrace();
            return secToTrigger;
        }
        return secToTrigger;
    }
}

























