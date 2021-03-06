package com.yju.wda.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

public class QuizActivity extends AppCompatActivity {
    Iterator it = new Iterator() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    };
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


    Button buttonSubmit;
    Intent intent;
    AppDatabase db;
    TextView txtScore, txtNowP, txtallP, txtProblem;
    LinearLayout textLayout, imageLayout;
    TextView quizP1, quizP2, quizP3, quizP4;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    ImageView imageView1, imageView2, imageView3, imageView4;
    Bitmap bitmap1, bitmap2, bitmap3, bitmap4;
    String sumScore;
    int userScore;
    int userChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "quiz-db")
                .allowMainThreadQueries()
                .build();

        List<QuizListItem> qList = db.quizDao().getAll();
        AtomicInteger quizNum = new AtomicInteger();


        problem(qList, quizNum, qList.get(quizNum.get()));

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtomicInteger sumScore = new AtomicInteger();
                Log.i("submit", "onClick: " + qList.get(quizNum.get()).getCorrect());

//                if(qList.get(quizNum.get()).getCorrect() == userChoice){
//                    Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
////                userScore= sumScore.intValue() + Integer.parseInt(qList.get(quizNum.get()).getScore());
//                    Log.i("HA", "onClick: " + userScore);
//
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
//                }

                quizNum.addAndGet(1);
                if(quizNum.get() < qList.size()){
                    problem(qList, quizNum, qList.get(quizNum.get()));
                }else{
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });
//==========================================?????????==============

    }

    public void problem(List<QuizListItem> quizList, AtomicInteger quizNum, QuizListItem quizListItem){
        if(quizListItem.getpType().equals("T")){
            textLayout.setVisibility(View.VISIBLE);
            imageLayout.setVisibility(View.GONE);
            txtProblem.setText(quizListItem.getpTitle());
            quizP1.setText(quizListItem.getEdt1());
            quizP2.setText(quizListItem.getEdt2());
            quizP3.setText(quizListItem.getEdt3());
            quizP4.setText(quizListItem.getEdt4());

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId == R.id.radioButton1){
                        userChoice = 1;
                    }
                    if(checkedId == R.id.radioButton2){
                        userChoice = 2;
                    }
                    if(checkedId == R.id.radioButton3){
                        userChoice = 3;
                    }
                    if(checkedId == R.id.radioButton4){
                        userChoice = 4;
                    }
                }
            });

            if(qList.get(quizNum.get()).getCorrect() == userChoice){
                Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
                userScore= Integer.valueOf(sumScore) + Integer.parseInt(qList.get(quizNum.get()).getScore());
                Log.i("HA", "onClick: " + userScore);

            }
            else{
                Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
            }

        }
        //===========================================?????????==============================
        if(quizListItem.getpType().equals("I")){
            textLayout.setVisibility(View.GONE);
            imageLayout.setVisibility(View.VISIBLE);
            txtProblem.setText(quizListItem.getpTitle());

            byte img1[] = quizListItem.getImg1();
            bitmap1 = BitmapFactory.decodeByteArray(img1, 0, img1.length);
            imageView1.setImageBitmap(bitmap1);

            byte img2[] = quizListItem.getImg2();
            bitmap2 = BitmapFactory.decodeByteArray(img2, 0, img2.length);
            imageView2.setImageBitmap(bitmap2);

            byte img3[] = quizListItem.getImg3();
            bitmap3 = BitmapFactory.decodeByteArray(img3, 0, img3.length);
            imageView3.setImageBitmap(bitmap3);

            byte img4[] = quizListItem.getImg4();
            bitmap4 = BitmapFactory.decodeByteArray(img4, 0, img4.length);
            imageView4.setImageBitmap(bitmap4);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId == R.id.radioButton1){
                        userChoice = 1;
                    }
                    if(checkedId == R.id.radioButton2){
                        userChoice = 2;
                    }
                    if(checkedId == R.id.radioButton3){
                        userChoice = 3;
                    }
                    if(checkedId == R.id.radioButton4){
                        userChoice = 4;
                    }
                }
            });

            if(qList.get(quizNum.get()).getCorrect() == userChoice){
                Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
                userScore= Integer.valueOf(sumScore) + Integer.parseInt(qList.get(quizNum.get()).getScore());
                Log.i("HA", "onClick: " + userScore);

            }
            else{
                Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void init(){
        buttonSubmit = findViewById(R.id.buttonSubmit);
        txtScore = findViewById(R.id.textViewScore);
        txtProblem = findViewById(R.id.textViewProblem);
        textLayout = findViewById(R.id.TextLayout);
        imageLayout = findViewById(R.id.ImageLayout);
        quizP1 = findViewById(R.id.quizP1);
        quizP2 = findViewById(R.id.quizP2);
        quizP3 = findViewById(R.id.quizP3);
        quizP4 = findViewById(R.id.quizP4);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
    }
}