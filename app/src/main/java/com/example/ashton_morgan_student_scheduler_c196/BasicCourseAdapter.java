package com.example.ashton_morgan_student_scheduler_c196;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BasicCourseAdapter extends RecyclerView.Adapter<BasicCourseAdapter.BasicCourseHolder> {
    private List<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public BasicCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basic_course_item, parent, false);
        return new BasicCourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicCourseHolder holder, int position) {
        Course currentCourse = courses.get(position);
        holder.basicCourseTitleTextView.setText(currentCourse.getTitle());
        holder.basicCourseStatusTextView.setText(currentCourse.getStatus());


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

        public BasicCourseHolder(@NonNull View itemView) {
            super(itemView);

            basicCourseTitleTextView = itemView.findViewById(R.id.basic_course_title);
            basicCourseStatusTextView = itemView.findViewById(R.id.basic_course_status);
        }
    }
}
