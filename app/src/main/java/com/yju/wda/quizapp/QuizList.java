package com.yju.wda.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuizList extends AppCompatActivity {
    AppDatabase db;
    FloatingActionButton actionButton;
    Handler handler;
    RecyclerView recyclerView = null;
    QuizListAdapter adapter = null;
    List<QuizListItem> qList = new List<QuizListItem>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<QuizListItem> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(QuizListItem quizListItem) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends QuizListItem> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends QuizListItem> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public QuizListItem get(int index) {
            return null;
        }

        @Override
        public QuizListItem set(int index, QuizListItem element) {
            return null;
        }

        @Override
        public void add(int index, QuizListItem element) {

        }

        @Override
        public QuizListItem remove(int index) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<QuizListItem> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<QuizListItem> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<QuizListItem> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    LinearLayoutManager linearLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "quiz-db")
                .allowMainThreadQueries()
                .build();
        actionButton = findViewById(R.id.floatingActionButton);
        //==============
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new QuizListAdapter(qList);//어댑터
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemClickListener(new QuizListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                int id = adapter.getItem(pos).getId();
                Log.i("ItemClick", "onItemClick: "+id);
                Intent intent = new Intent(getApplicationContext(), QuizView.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문제 출제 화면으로 넘어가게
                Intent intent = new Intent(QuizList.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        new Thread(()->{
            qList = db.quizDao().getAll();
            Log.i("getAll", "onCreate: "+ qList.toString());
            Log.i("getAll", "onStart: ."+qList.size());
            adapter.setData(qList);
//               addItem(problem, regDate, type);
//        }).start();
    }

//    public void addItem(String problem, String regdate, String type){
//        QuizListItem quizListItem = new QuizListItem();
//        quizListItem.setpType(type);
//        quizListItem.setpTitle(problem);
//        quizListItem.setpRegDate(regdate);
//        qList.add(quizListItem);
//        adapter.notifyDataSetChanged();
//
//    }
}