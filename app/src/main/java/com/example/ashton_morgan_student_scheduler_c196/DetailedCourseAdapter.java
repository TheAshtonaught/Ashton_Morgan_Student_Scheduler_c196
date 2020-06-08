package com.example.ashton_morgan_student_scheduler_c196;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class DetailedCourseAdapter extends RecyclerView.Adapter<DetailedCourseAdapter.DetailedCourseHolder> {
    private List<Course> courses = new ArrayList<>();
    private OnItemClickListener editCourseListener;
    private OnItemClickListener shareNotesListener;
    private OnItemClickListener assessmentsListener;

    @NonNull
    @Override
    public DetailedCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);


        return new DetailedCourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedCourseHolder holder, final int position) {
        Course currentCourse = courses.get(position);
        holder.statusTextView.setText(currentCourse.getStatus());
        holder.courseTitleTextView.setText(currentCourse.getTitle());
        holder.startDateTextView.setText(currentCourse.getStartDate());
        holder.endDateTextView.setText(currentCourse.getEndDate());
        holder.notesTextView.setText(currentCourse.getNotes());
        holder.mentorInfoTextView.setText(currentCourse.getMentor());

        holder.shareNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareNotesListener.onItemClick(courses.get(position));
            }
        });

        holder.assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assessmentsListener.onItemClick(courses.get(position));
            }
        });

        holder.editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCourseListener.onItemClick(courses.get(position));
            }
        });


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
        private Button shareNotesButton;
        private Button assessmentsButton;
        private ImageButton editCourseButton;

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

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }

    public void setOnShareButtonClickListener(OnItemClickListener listener) {
        this.shareNotesListener = listener;
    }

    public void setOnAssessmentButtonClickListener(OnItemClickListener listener) {
        this.assessmentsListener = listener;
    }

    public void setOnEditButtonClickListener(OnItemClickListener listener) {
        this.editCourseListener = listener;
    }




}
