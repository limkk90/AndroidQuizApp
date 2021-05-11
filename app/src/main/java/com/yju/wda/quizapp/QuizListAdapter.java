package com.yju.wda.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {
    private ArrayList<QuizListItem> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        TextView regDate;

        ViewHolder(View itemView){
            super(itemView);

            icon = itemView.findViewById(R.id.pCatImage);
            title = itemView.findViewById(R.id.pTitle);
            regDate = itemView.findViewById(R.id.pRegDate);
        }
    }
    QuizListAdapter(ArrayList<QuizListItem> list) {
        mData = list;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
