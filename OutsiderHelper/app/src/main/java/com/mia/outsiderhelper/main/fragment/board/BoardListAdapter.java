package com.mia.outsiderhelper.main.fragment.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.board.detail.interfaces.OnItemClickListener;
import com.mia.outsiderhelper.models.BoardBody;

import java.util.ArrayList;

public class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.BoardHolder> {
    private ArrayList<BoardBody> boards;
    private OnItemClickListener listener;
    private String mDate;
    private String mTime;

    public BoardListAdapter(){}
    public BoardListAdapter(ArrayList<BoardBody> boards) {
        this.boards = boards;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BoardListAdapter.BoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, null);
        BoardHolder holder = new BoardHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardListAdapter.BoardHolder holder, int position) {
        BoardBody board = boards.get(position);
        dateFormat(board.getDate());

        holder.txtBoardTitle.setText(board.getTitle());
        holder.txtBoardHits.setText(String.valueOf(board.getHits()));
        holder.txtBoardNickname.setText(board.getUserId());
        holder.txtBoardDate.setText(mDate);
        holder.txtBoardTime.setText(mTime);
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public class BoardHolder extends RecyclerView.ViewHolder{
        TextView txtBoardTitle;
        TextView txtBoardHits;
        TextView txtBoardNickname;
        TextView txtBoardDate;
        TextView txtBoardTime;

        public BoardHolder(@NonNull View itemView) {
            super(itemView);

            txtBoardTitle = itemView.findViewById(R.id.txt_board_title);
            txtBoardHits = itemView.findViewById(R.id.txt_board_hits);
            txtBoardNickname = itemView.findViewById(R.id.txt_board_nickname);
            txtBoardDate = itemView.findViewById(R.id.txt_board_date);
            txtBoardTime = itemView.findViewById(R.id.txt_board_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

    }

    public void setBoards(ArrayList<BoardBody> boards) {
        this.boards = boards;
    }

    public BoardBody getItem(int pos) {
        return boards.get(pos);
    }

    private void dateFormat(String date) {
        if (date != null) {
            int idx = date.indexOf(" ");
            mDate = date.substring(0, idx);
            mTime = date.substring(idx + 1, date.length());
        }
    }


}
