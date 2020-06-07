package com.example.ashton_morgan_student_scheduler_c196;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class DetailedCourseAdapter extends RecyclerView.Adapter<DetailedCourseAdapter.DetailedCourseHolder> {
    private List<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public DetailedCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);


        return new DetailedCourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedCourseHolder holder, int position) {

        //TODO: HANDLE

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    class DetailedCourseHolder extends RecyclerView.ViewHolder {
        private TextView courseTitleTextView;
        private TextView statusTextView;
        private TextView startDateTextView;
        private TextView endDateTextView;
        private TextView notesTextView;
        private TextView mentorInfoTextView;
        private MaterialButton shareNotesButton;
        private MaterialButton assessmentsButton;
        private ImageButton editCourseButton;
        private Spinner termDropDown;

        public DetailedCourseHolder(@NonNull View itemView) {
            super(itemView);
            courseTitleTextView = itemView.findViewById(R.id.course_title);
            statusTextView = itemView.findViewById(R.id.status_textView);
            startDateTextView = itemView.findViewById(R.id.course_start_date);
            endDateTextView = itemView.findViewById(R.id.course_end_date);
            notesTextView = itemView.findViewById(R.id.notes_text_view);
            mentorInfoTextView = itemView.findViewById(R.id.mentor_info);
            shareNotesButton = itemView.findViewById(R.id.share_notes_button);
            assessmentsButton = itemView.findViewById(R.id.assessments_button);
            editCourseButton = itemView.findViewById(R.id.edit_course_button);
            termDropDown = itemView.findViewById(R.id.term_spinner);

        }
    }








}
