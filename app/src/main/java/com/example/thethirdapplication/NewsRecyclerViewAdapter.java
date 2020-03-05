package com.example.thethirdapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thethirdapplication.models.Articles;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.RecyclerHolder> {

    private List<Articles> models;
    private OnRecycleViewNewsListener onRecycleViewNewsListener;
    private int keyPosition;

    public NewsRecyclerViewAdapter(List<Articles> models, OnRecycleViewNewsListener onRecycleViewNewsListener) {
        this.onRecycleViewNewsListener = onRecycleViewNewsListener;
        this.models = models;
    }

    public void updateList(List<Articles> models) {
        if (this.models != null) {
            this.models.clear();
            this.models.addAll(models);
        } else {
            this.models = models;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, null, false);
        NewsRecyclerViewAdapter.RecyclerHolder viewHolder = new NewsRecyclerViewAdapter.RecyclerHolder(view, onRecycleViewNewsListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.RecyclerHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView authorTextView;
        private TextView titleTextView;
        private TextView publishTextView;
        private OnRecycleViewNewsListener onRecycleViewNewsListener;

        public RecyclerHolder(@NonNull View itemView, OnRecycleViewNewsListener onRecycleViewNewsListener) {
            super(itemView);
            authorTextView = itemView.findViewById(R.id.author);
            titleTextView = itemView.findViewById(R.id.title);
            publishTextView = itemView.findViewById(R.id.publishedAt);
            this.onRecycleViewNewsListener = onRecycleViewNewsListener;
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            keyPosition = position;
            authorTextView.setText(models.get(position).getAuthor());
            titleTextView.setText("||" + models.get(position).getTitle());
            publishTextView.setText("*" + models.get(position).getPublishedAt());
        }

        @Override
        public void onClick(View v) {
            Log.i("myTag", authorTextView.getText() + "");
            onRecycleViewNewsListener.onNewsRecycleClick(keyPosition);
        }
    }

    public interface OnRecycleViewNewsListener {
        void onNewsRecycleClick(int key);
    }
}
