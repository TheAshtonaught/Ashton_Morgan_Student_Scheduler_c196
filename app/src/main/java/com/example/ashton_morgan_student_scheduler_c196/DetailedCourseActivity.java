package com.example.ashton_morgan_student_scheduler_c196;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailedCourseActivity extends AppCompatActivity {
    private SchedulerViewModel schedulerViewModel;
    public static final String EXTRA_COURSE_TERM_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_TERM_ID";
    public static final String EXTRA_COURSE_TERM_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_COURSE_TERM_TITLE";


    private int termID;
    private int ADD_COURSE_REQUEST = 1;
    private int EDIT_COURSE_REQUEST = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course);

        Intent intent = getIntent();
        termID = intent.getIntExtra(EXTRA_COURSE_TERM_ID, -99);
        String termTitle = intent.getStringExtra(EXTRA_COURSE_TERM_TITLE);
        setTitle(termTitle);



        FloatingActionButton addCourseButton = findViewById(R.id.add_course_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedCourseActivity.this, AddCourseActivity.class);
                intent.putExtra(AddCourseActivity.EXTRA_TERM_ID, termID);
                startActivityForResult(intent, ADD_COURSE_REQUEST);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.detailed_course_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final DetailedCourseAdapter adapter = new DetailedCourseAdapter();
        recyclerView.setAdapter(adapter);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schedulerViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                List<Course> coursesForTerm = new ArrayList<>();
                for (Course c : courses) {
                    if (c.getTermID() == termID) {
                        coursesForTerm.add(c);

                    }
                }
                adapter.setCourses(coursesForTerm);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                schedulerViewModel.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(DetailedCourseActivity.this, "Course Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnAssessmentButtonClickListener(new DetailedCourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                //TODO: Go to assessments for course

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
                Intent intent = new Intent(DetailedCourseActivity.this, AddCourseActivity.class);
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_ID, course.getId());
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_TITLE, course.getTitle());
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_START_DATE, course.getStartDate());
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_END_DATE, course.getEndDate());
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_STATUS, course.getStatus());
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_NOTES, course.getNotes());
                intent.putExtra(AddCourseActivity.EXTRA_COURSE_MENTOR, course.getMentor());
                startActivityForResult(intent, EDIT_COURSE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_COURSE_REQUEST && resultCode == RESULT_OK) {

            String courseTitle =  data.getStringExtra(AddCourseActivity.EXTRA_COURSE_TITLE);
            String courseStartDate = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_START_DATE);
            String courseEndDate = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_END_DATE);
            String courseStatus = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_STATUS);
            String courseMentor = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_MENTOR);
            String courseNotes = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_NOTES);

            Course course = new Course(courseTitle, courseStartDate, courseEndDate, courseStatus, courseMentor, courseNotes, termID);
            schedulerViewModel.insert(course);

            Toast.makeText(this, "Course Added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddCourseActivity.EXTRA_COURSE_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Update error", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseTitle =  data.getStringExtra(AddCourseActivity.EXTRA_COURSE_TITLE);
            String courseStartDate = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_START_DATE);
            String courseEndDate = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_END_DATE);
            String courseStatus = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_STATUS);
            String courseMentor = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_MENTOR);
            String courseNotes = data.getStringExtra(AddCourseActivity.EXTRA_COURSE_NOTES);

            Course course = new Course(courseTitle, courseStartDate, courseEndDate, courseStatus, courseMentor, courseNotes, termID);
            course.setId(id);
            schedulerViewModel.update(course);

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "No changes made", Toast.LENGTH_SHORT).show();
        }


    }






}
