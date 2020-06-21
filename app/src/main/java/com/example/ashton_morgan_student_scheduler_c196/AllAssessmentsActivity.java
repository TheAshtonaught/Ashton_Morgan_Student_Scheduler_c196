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

public class AllAssessmentsActivity extends AppCompatActivity {
    public static final String EXTRA_NOTIFICATION_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_NOTIFICATION_TITLE";
    public static final String EXTRA_NOTIFICATION_TEXT =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_NOTIFICATION_TEXT";
    private SchedulerViewModel schedulerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_assessments);
        setTitle("All Assessments");

        RecyclerView recyclerView = findViewById(R.id.all_assessments_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BasicAssessmentAdapter adapter = new BasicAssessmentAdapter();
        recyclerView.setAdapter(adapter);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schedulerViewModel.getAllAssessments().observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(List<Assessment> assessments) {
                adapter.setAssessments(assessments);
            }
        });

        FloatingActionButton addAssessmentFromBasicButton = findViewById(R.id.basic_add_assessment_button);
        addAssessmentFromBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllAssessmentsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnAssessmentAlertListener(new BasicAssessmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Assessment assessment) {
                Intent intent = new Intent(AllAssessmentsActivity.this, AssessmentDateNotificationService.class);
                intent.putExtra(EXTRA_NOTIFICATION_TITLE, "Upcomming Assessment");
                String notifText = assessment.getTitle() + " due on " + assessment.getDueDate();
                intent.putExtra(EXTRA_NOTIFICATION_TEXT, notifText);

                startService(intent);
            }
        });



    }
}
