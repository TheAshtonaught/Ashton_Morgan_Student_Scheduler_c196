package com.example.ashton_morgan_student_scheduler_c196;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BasicAssessmentAdapter extends RecyclerView.Adapter<BasicAssessmentAdapter.BasicAssessmentHolder> {
    private List<Assessment> assessments = new ArrayList<>();

    @NonNull
    @Override
    public BasicAssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basic_assessment_item, parent, false);
        return new BasicAssessmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicAssessmentHolder holder, int position) {
        Assessment currentAssessment = assessments.get(position);
        holder.assessmentTitle.setText(currentAssessment.getTitle());
        holder.assessmentType.setText(currentAssessment.getType());
        holder.assessmentDate.setText(currentAssessment.getDueDate());


    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
        notifyDataSetChanged();
    }

    public Assessment getAssessmentAt(int pos) {
        return assessments.get(pos);
    }


    class BasicAssessmentHolder extends RecyclerView.ViewHolder {
        private TextView assessmentTitle;
        private TextView assessmentType;
        private TextView assessmentDate;


        public BasicAssessmentHolder(@NonNull View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.basic_assessment_title);
            assessmentType = itemView.findViewById(R.id.basic_assessment_type);
            assessmentDate = itemView.findViewById(R.id.basic_assessment_date);

        }
    }
}
