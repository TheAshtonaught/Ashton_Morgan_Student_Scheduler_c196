package com.example.ashton_morgan_student_scheduler_c196;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BasicCourseAdapter extends RecyclerView.Adapter<BasicCourseAdapter.BasicCourseHolder> {
    private List<Course> courses = new ArrayList<>();
    private OnItemClickListener courseStartAlertListener;
    private OnItemClickListener courseEndAlertListener;

    @NonNull
    @Override
    public BasicCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basic_course_item, parent, false);
        return new BasicCourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicCourseHolder holder, final int position) {
        Course currentCourse = courses.get(position);
        holder.basicCourseTitleTextView.setText(currentCourse.getTitle());
        holder.basicCourseStatusTextView.setText(currentCourse.getStatus());
        holder.basicCourseStartDateTextView.setText(currentCourse.getStartDate());
        holder.basicCourseEndDateTextView.setText(currentCourse.getEndDate());
        holder.courseStartAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseStartAlertListener.onItemClick(courses.get(position));
            }
        });

        holder.courseEndAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseEndAlertListener.onItemClick(courses.get(position));
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

    public Course getCourseAt(int pos) {
        return courses.get(pos);
    }

    class BasicCourseHolder extends RecyclerView.ViewHolder {
        private TextView basicCourseTitleTextView;
        private TextView basicCourseStatusTextView;
        private TextView basicCourseStartDateTextView;
        private TextView basicCourseEndDateTextView;
        private ImageButton courseStartAlertButton;
        private ImageButton courseEndAlertButton;

        public BasicCourseHolder(@NonNull View itemView) {
            super(itemView);

            basicCourseTitleTextView = itemView.findViewById(R.id.basic_course_title);
            basicCourseStatusTextView = itemView.findViewById(R.id.basic_course_status);
            basicCourseStartDateTextView = itemView.findViewById(R.id.basic_course_start_date);
            basicCourseEndDateTextView = itemView.findViewById(R.id.basic_course_end_date);
            courseStartAlertButton = itemView.findViewById(R.id.alert_course_start);
            courseEndAlertButton = itemView.findViewById(R.id.alert_course_end);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }



    public void setOnCourseStartAlertButtonClickListener(OnItemClickListener listener) {
        this.courseStartAlertListener = listener;
    }

    public void setOnCourseEndAlertButtonClickListener(OnItemClickListener listener) {
        this.courseEndAlertListener = listener;
    }
}
