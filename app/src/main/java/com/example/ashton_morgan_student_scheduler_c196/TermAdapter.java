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

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermHolder> {
    private List<Term> terms = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemClickListener btnListener;

    @NonNull
    @Override
    public TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_item, parent, false);

        return new TermHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermHolder holder, final int position) {
        Term currentTerm = terms.get(position);
        holder.termTitleTextView.setText(currentTerm.getTitle());
        holder.startDateTextView.setText(currentTerm.getStartDate());
        holder.endDateTextView.setText(currentTerm.getEndDate());
        holder.editTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnListener != null && position != RecyclerView.NO_POSITION) {
                    btnListener.onItemClick(terms.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }

    public Term getTermAt(int pos) {
        return terms.get(pos);
    }

    class TermHolder extends RecyclerView.ViewHolder {
        private TextView termTitleTextView;
        private TextView startDateTextView;
        private TextView endDateTextView;
        private ImageButton editTermButton;

        public TermHolder(final View itemView) {
            super(itemView);
            termTitleTextView = itemView.findViewById(R.id.term_title);
            startDateTextView = itemView.findViewById(R.id.start_date);
            endDateTextView = itemView.findViewById(R.id.end_date);
            editTermButton = itemView.findViewById(R.id.edit_term_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(terms.get(pos));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Term term);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnBtnClickListener(OnItemClickListener listener) {
        this.btnListener = listener;
    }



}
