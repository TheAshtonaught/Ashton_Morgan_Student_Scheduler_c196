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
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_TERM_REQUEST = 1;
    private SchedulerViewModel schViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton addTermButton = findViewById(R.id.add_term_button);
        addTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTermActivity.class);
                startActivityForResult(intent, ADD_TERM_REQUEST);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TermAdapter adapter = new TermAdapter();
        recyclerView.setAdapter(adapter);

        schViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);
        schViewModel.getAllTerms().observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(List<Term> terms) {
                adapter.setTerms(terms);
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
                schViewModel.delete(adapter.getTermAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Term Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_TERM_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddTermActivity.EXTRA_TERM_TITLE);
            String startDateString = data.getStringExtra(AddTermActivity.EXTRA_START_DATE_STRING);
            String endDateString = data.getStringExtra(AddTermActivity.EXTRA_END_DATE_STRING);

            Term term = new Term(title, startDateString, endDateString);
            schViewModel.insert(term);
            Toast.makeText(this, "Term Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Term not added", Toast.LENGTH_SHORT).show();
        }
    }
}
































