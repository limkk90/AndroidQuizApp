package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HardActivity extends AppCompatActivity {
    Button buttonSubmit;
    AppDatabase db;
    TextView txtScore, txtNowP, txtallP, txtProblem;
    LinearLayout textLayout;
    ConstraintLayout imageLayout;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    ImageView imageView1, imageView2, imageView3, imageView4;
    Bitmap bitmap1, bitmap2, bitmap3, bitmap4;
    int userChoice;
    String strCorrect = "a";
    EditText userAns= null;
    String userAnswer = "b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        init();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "quiz-db")
                .allowMainThreadQueries()
                .build();
        List<QuizListItem> qList = db.quizDao().getAll();
        AtomicInteger quizNum = new AtomicInteger();
        AtomicInteger sumScore = new AtomicInteger();

        problem(qList, quizNum, qList.get(quizNum.get()));

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strCorrect.equals(userAnswer)){
                    Toast.makeText(HardActivity.this, "정답입니다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HardActivity.this, "틀렸습니다", Toast.LENGTH_SHORT).show();
                }
                if(userChoice == qList.get(quizNum.get()).getCorrect()){
                    Toast.makeText(HardActivity.this, "정답입니다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HardActivity.this, "틀렸습니다", Toast.LENGTH_SHORT).show();
                }
                quizNum.addAndGet(1);
                if(quizNum.get() < qList.size()){
                    problem(qList, quizNum, qList.get(quizNum.get()));
                }else{
                    Intent intent = new Intent(HardActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    public void problem(List<QuizListItem> quizList, AtomicInteger quizNum, QuizListItem quizListItem){
        if(quizListItem.getpType().equals("T")){
            textLayout.setVisibility(View.VISIBLE);
            imageLayout.setVisibility(View.GONE);
            txtProblem.setText(quizListItem.getpTitle());

            if(quizListItem.getCorrect() ==1){
                strCorrect = quizListItem.edt1;
            }
            if(quizListItem.getCorrect() == 2){
                strCorrect = quizListItem.edt2;
            }
            if(quizListItem.getCorrect() == 3){
                strCorrect = quizListItem.edt3;
            }
            if(quizListItem.getCorrect() == 4){
                strCorrect = quizListItem.edt4;
            }
            //정답 가져옴
            userAnswer = userAns.getText().toString();

//            quizP1.setText(quizListItem.getEdt1());
//            quizP2.setText(quizListItem.getEdt2());
//            quizP3.setText(quizListItem.getEdt3());
//            quizP4.setText(quizListItem.getEdt4());
        }
        //===========================================이미지==============================
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
        }
    }


    public void init(){
        userAns = findViewById(R.id.editCorrect);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        txtScore = findViewById(R.id.textViewScore2);
        txtProblem = findViewById(R.id.textViewProblem2);
        textLayout = findViewById(R.id.EditLayout);
        imageLayout = findViewById(R.id.ConstraintLayoutImage);
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