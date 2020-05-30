package com.example.ashton_morgan_student_scheduler_c196;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermHolder> {
    private List<Term> terms = new ArrayList<>();

    @NonNull
    @Override
    public TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_item,parent, false);
        return new TermHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermHolder holder, int position) {
        Term currentTerm = terms.get(position);
        holder.termTitleTextView.setText(currentTerm.getTitle());
        holder.startDateTextView.setText(currentTerm.getStartDate());
        holder.endDateTextView.setText(currentTerm.getEndDate());

    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public void setTerms(List<Term> terms) {
        this.terms =terms;
        notifyDataSetChanged();
    }

    class TermHolder extends RecyclerView.ViewHolder {
        private TextView termTitleTextView;
        private TextView startDateTextView;
        private TextView endDateTextView;
        
        public TermHolder(View itemView) {
            super(itemView);
            termTitleTextView = itemView.findViewById(R.id.term_title);
            startDateTextView = itemView.findViewById(R.id.start_date);
            endDateTextView = itemView.findViewById(R.id.end_date);
            
        }
    }
}
