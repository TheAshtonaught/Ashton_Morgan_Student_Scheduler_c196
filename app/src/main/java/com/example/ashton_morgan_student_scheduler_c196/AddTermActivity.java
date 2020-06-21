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

public class AddTermActivity extends AppCompatActivity {
    public static final String EXTRA_TERM_TITLE =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_TERM_TITLE";
    public static final String EXTRA_START_DATE_STRING =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_START_DATE_STRING";
    public static final String EXTRA_END_DATE_STRING =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_END_DATE_STRING";
    public static final String EXTRA_TERM_ID =
            "com.example.ashton_morgan_student_scheduler_c196.EXTRA_TERM_ID";

    private EditText editTextTitle;
    private EditText editTextStartDate;
    private EditText editTextEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextStartDate = findViewById(R.id.edit_text_start_date);
        editTextEndDate = findViewById(R.id.edit_text_end_date);



        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_TERM_ID)) {
            setTitle("Edit Term");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TERM_TITLE));
            editTextStartDate.setText(intent.getStringExtra(EXTRA_START_DATE_STRING));
            editTextEndDate.setText(intent.getStringExtra(EXTRA_END_DATE_STRING));
        } else {
            setTitle("Add Term");
        }


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
                saveTerm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTerm() {
        String title = editTextTitle.getText().toString();
        String startDateString = editTextStartDate.getText().toString();
        String endDateString = editTextEndDate.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if date is valid
        try {
            Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(startDateString);
            Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(endDateString);

            Intent data = new Intent();
            data.putExtra(EXTRA_TERM_TITLE, title);
            data.putExtra(EXTRA_START_DATE_STRING, startDateString);
            data.putExtra(EXTRA_END_DATE_STRING, endDateString);

            int id = getIntent().getIntExtra(EXTRA_TERM_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_TERM_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();

        } catch (ParseException e) {
            Toast.makeText(this, "Enter a valid date format MM/dd/yyyy", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }


    }



}



























