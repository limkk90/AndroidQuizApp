package com.yju.wda.quizapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {
    private List<QuizListItem> mData = null;

    //인터페이스 정의
    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    QuizListAdapter(List<QuizListItem> list) {
        mData = list;
        Log.i("Adapter", "QuizListAdapter: " + mData.toString());
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView type;
        TextView title;
        TextView regDate;

        ViewHolder(View itemView){
            super(itemView);
            type = itemView.findViewById(R.id.pType);
            title = itemView.findViewById(R.id.pTitle);
            regDate = itemView.findViewById(R.id.pRegDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(listener != null){
                            listener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }



    @NonNull
    @Override
    public QuizListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        QuizListAdapter.ViewHolder vh = new QuizListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizListAdapter.ViewHolder holder, int position) {

        QuizListItem item = mData.get(position);

        holder.title.setText(item.getpTitle());
        holder.regDate.setText(item.getpRegDate());
        holder.type.setText(item.getpType());
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<QuizListItem> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    public QuizListItem getItem(int position){
        return mData.get(position);
    }
}
