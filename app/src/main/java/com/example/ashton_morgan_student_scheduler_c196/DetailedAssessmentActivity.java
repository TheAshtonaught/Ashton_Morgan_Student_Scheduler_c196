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

public class DetailedAssessmentActivity extends AppCompatActivity {
    private SchedulerViewModel schedulerViewModel;
    public static final String EXTRA_ASSESSMENT_COURSE_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_ASSESSMENT_COURSE_ID";
    private int ADD_ASSESSMENT_REQUEST = 1;
    private int EDIT_ASSESSMENT_REQUEST = 2;
    private int courseID;
    private List<Assessment> assessmentsForCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_assessment);

        Intent intent = getIntent();
        courseID = intent.getIntExtra(EXTRA_ASSESSMENT_COURSE_ID, -99);
        setTitle("Assessments");

        RecyclerView recyclerView = findViewById(R.id.assessment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final DetailedAssessmentAdapter adapter = new DetailedAssessmentAdapter();
        recyclerView.setAdapter(adapter);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schedulerViewModel.getAllAssessments().observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(List<Assessment> assessments) {
                assessmentsForCourse = new ArrayList<>();
                for (Assessment c : assessments) {
                    if (c.getCourseId() == courseID) {
                        assessmentsForCourse.add(c);

                    }
                }
                adapter.setAssessments(assessmentsForCourse);
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
                schedulerViewModel.delete(adapter.getAssessmentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(DetailedAssessmentActivity.this, "Assessment Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        FloatingActionButton addCourseButton = findViewById(R.id.add_assessment_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DetailedAssessmentActivity.this, AddAssessmentActivity.class);
//                intent.putExtra(AddAssessmentActivity.EXTRA_COURSE_ID, courseID);
//                startActivityForResult(intent, ADD_ASSESSMENT_REQUEST);

            }
        });

        adapter.setOnBtnClickListener(new DetailedAssessmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Assessment assessment) {
                //TODO: edit assessment
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
