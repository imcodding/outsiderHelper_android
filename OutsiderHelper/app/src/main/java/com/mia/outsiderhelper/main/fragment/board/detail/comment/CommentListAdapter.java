package com.mia.outsiderhelper.main.fragment.board.detail.comment;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.outsiderhelper.models.CommentBody;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentHolder> {

    ArrayList<CommentBody> comments;

    @NonNull
    @Override
    public CommentListAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.CommentHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        public CommentHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setComments(ArrayList<CommentBody> comments) {
        this.comments = comments;
    }
}
