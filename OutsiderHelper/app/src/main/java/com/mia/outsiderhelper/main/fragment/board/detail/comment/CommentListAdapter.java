package com.mia.outsiderhelper.main.fragment.board.detail.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.models.CommentBody;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentHolder> {

    ArrayList<CommentBody> comments;

    @NonNull
    @Override
    public CommentListAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, null);
        CommentHolder holder = new CommentHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.CommentHolder holder, int position) {
        CommentBody comment = comments.get(position);

        holder.txtCommentContent.setText(comment.getContent());
        holder.txtCommentUserNickname.setText(comment.getUserId());
        holder.txtCommentDate.setText(comment.getDate());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView txtCommentUserNickname;
        TextView txtCommentContent;
        TextView txtCommentDate;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);

            txtCommentUserNickname = itemView.findViewById(R.id.txt_comment_user_nickname);
            txtCommentContent = itemView.findViewById(R.id.txt_comment_content);
            txtCommentDate = itemView.findViewById(R.id.txt_comment_date);
        }
    }

    public void setComments(ArrayList<CommentBody> comments) {
        this.comments = comments;
    }

    public void addItem(CommentBody comment) {
        this.comments.add(comment);
        notifyItemInserted(comments.size() - 1);
    }
}
