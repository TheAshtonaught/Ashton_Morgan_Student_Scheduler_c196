package com.example.ashton_morgan_student_scheduler_c196;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailedAssessmentAdapter extends RecyclerView.Adapter<DetailedAssessmentAdapter.DetailedAssessmentHolder> {
    private List<Assessment> assessments = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemClickListener btnListener;


    @NonNull
    @Override
    public DetailedAssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assessment_item, parent, false);

        return new DetailedAssessmentAdapter.DetailedAssessmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedAssessmentHolder holder, final int position) {
        Assessment currentAssessment = assessments.get(position);
        holder.assessmentTitleTextView.setText(currentAssessment.getTitle());
        holder.assessmentDueDateTextView.setText(currentAssessment.getDueDate());
        holder.assessmentTypeTextView.setText(currentAssessment.getType());
        holder.editAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnListener != null && position != RecyclerView.NO_POSITION) {
                    btnListener.onItemClick(assessments.get(position));
                }
            }
        });


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


    class DetailedAssessmentHolder extends RecyclerView.ViewHolder {
        private TextView assessmentTitleTextView;
        private TextView assessmentDueDateTextView;
        private TextView assessmentTypeTextView;
        private ImageButton editAssessmentButton;

        public DetailedAssessmentHolder(View itemView) {
            super(itemView);
            assessmentTitleTextView = itemView.findViewById(R.id.assessment_title);
            assessmentDueDateTextView = itemView.findViewById(R.id.assessment_due_date);
            assessmentTypeTextView = itemView.findViewById(R.id.assessment_type);
            editAssessmentButton = itemView.findViewById(R.id.edit_assessment_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(assessments.get(pos));
                    }
                }
            });

        }


    }

    public interface OnItemClickListener {
        void onItemClick(Assessment assessment);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnBtnClickListener(OnItemClickListener listener) {
        this.btnListener = listener;
    }


}
