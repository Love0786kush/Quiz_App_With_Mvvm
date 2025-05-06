package com.example.quizappwithmvvm.Adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;             // ← Added
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.quizappwithmvvm.Model.QuizListModel;
import com.example.quizappwithmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizListViewHolder> {
    private List<QuizListModel> quizListModels = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    // Constructor
    public QuizListAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /** Allows fragments to read out the current list */
    public List<QuizListModel> getItems() {
        return quizListModels;
    }

    /** Update the adapter’s data and refresh */
    public void setQuizListModels(List<QuizListModel> quizListModels) {
        this.quizListModels = quizListModels != null
                ? quizListModels
                : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.each_quiz, parent, false);
        return new QuizListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizListViewHolder holder, int position) {
        QuizListModel model = quizListModels.get(position);
        holder.title.setText(model.getTitle());

        // Load image via Glide
        String url = model.getImage();
        if (url != null && !url.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.image_error)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(
                                @Nullable GlideException e,
                                Object modelObject,
                                Target<Drawable> target,
                                boolean isFirstResource
                        ) {
                            Log.e("GlideError",
                                    "Image failed at pos " + holder.getAdapterPosition() +
                                            ": " + (e != null ? e.getMessage() : "unknown"));
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(
                                Drawable resource,
                                Object modelObject,
                                Target<Drawable> target,
                                DataSource dataSource,
                                boolean isFirstResource
                        ) {
                            Log.d("GlideSuccess",
                                    "Image loaded at pos " + holder.getAdapterPosition());
                            return false;
                        }
                    })
                    .into(holder.quizImage);
        } else {
            holder.quizImage.setImageResource(R.drawable.placeholder);
        }

        // Handle clicks
        holder.cardViewLayout.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(currentPos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizListModels.size();
    }

    class QuizListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView quizImage;
        CardView cardViewLayout;

        QuizListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardText);
            quizImage = itemView.findViewById(R.id.cardImage);
            cardViewLayout = itemView.findViewById(R.id.cardViewLayout);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
