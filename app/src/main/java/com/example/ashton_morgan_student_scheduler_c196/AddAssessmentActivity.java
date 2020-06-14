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

public class AddAssessmentActivity extends AppCompatActivity {
    public static final String EXTRA_ASSESSMENT_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_ASSESSMENT_TITLE";
    public static final String EXTRA_ASSESSMENT_DUE_DATE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_ASSESSMENT_DUE_DATE";
    public static final String EXTRA_ASSESSMENT_TYPE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_ASSESSMENT_TYPE";
    public static final String EXTRA_ASSESSMENT_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_ASSESSMENT_ID";
    public static final String EXTRA_ASSESSMENT_COURSE_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_ASSESSMENT_COURSE_ID";

    private EditText editAssessmentTitle;
    private EditText editAssessmentDueDate;
    private EditText editAssessmentType;

    private int courseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        setUI();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ASSESSMENT_ID)) {
            setTitle("Edit Assessment");
            populateAssessmentData(intent);

        } else {
            setTitle("Add Assessment");
        }
        courseID = intent.getIntExtra(EXTRA_ASSESSMENT_COURSE_ID, -99);


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
                saveAssessment();
                return true;
            default:
                cancelAddAssessment();
                return true;
        }
    }

    public void saveAssessment() {
        String assessmentTitle = editAssessmentTitle.getText().toString();
        String assessmentDueDateString = editAssessmentDueDate.getText().toString();
        String assessmentType = editAssessmentType.getText().toString();


        if (assessmentTitle.trim().isEmpty() || assessmentDueDateString.trim().isEmpty() ||
                assessmentType.trim().isEmpty()) {
            Toast.makeText(this, "Fields Can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(assessmentDueDateString);

            Intent data = new Intent();
            data.putExtra(EXTRA_ASSESSMENT_TITLE, assessmentTitle);
            data.putExtra(EXTRA_ASSESSMENT_DUE_DATE, assessmentDueDateString);
            data.putExtra(EXTRA_ASSESSMENT_TYPE, assessmentType);

            int id = getIntent().getIntExtra(EXTRA_ASSESSMENT_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ASSESSMENT_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();

        } catch (ParseException e) {
            Toast.makeText(this, "Enter a valid date format MM/dd/yyyy", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }


    }

    public void cancelAddAssessment() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void setUI() {
        editAssessmentTitle = findViewById(R.id.edit_text_assessment_title);
        editAssessmentType = findViewById(R.id.edit_text_assessment_type);
        editAssessmentDueDate = findViewById(R.id.edit_text_assessment_due_date);

    }

    public void populateAssessmentData(Intent intent) {
        editAssessmentTitle.setText(intent.getStringExtra(EXTRA_ASSESSMENT_TITLE));
        editAssessmentDueDate.setText(intent.getStringExtra(EXTRA_ASSESSMENT_DUE_DATE));
        editAssessmentType.setText(intent.getStringExtra(EXTRA_ASSESSMENT_TYPE));

    }





}
