package com.yju.wda.quizapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

}
