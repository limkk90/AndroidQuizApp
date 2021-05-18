package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizView extends AppCompatActivity {
    int id;
    AppDatabase db;
    //저장버튼
    Button buttonDelete, buttonUpdate;
    //점수, 문제
    EditText edtScore, edtProblem;
    String strEdtScore, stredtProblem;
    //텍스트 이미지 레이아웃
    LinearLayout textLayout, imgLayout;

    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    //정답 변수
    int correct;
    //텍스트 문제 출제
    EditText edtP1, edtP2, edtP3, edtP4;
    String strEdtP1, strEdtP2, strEdtP3, strEdtP4;


    //이미지 문제 출제
    ImageView imageView1, imageView2, imageView3, imageView4;
    Button buttonImg1, buttonImg2, buttonImg3, buttonImg4;
    Bitmap bitmap1, bitmap2, bitmap3, bitmap4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);
        init(); //findViewById;
        String type;
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "quiz-db")
                .allowMainThreadQueries()
                .build();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", id);
        Log.i("QuizView", "onCreate야: " + id);

        QuizListItem quizListItem = db.quizDao().getQuiz(id);
        Log.i("QuizView", "onCreate: " + quizListItem.getpType());
        Log.i("QuizView", "onCreate: " + quizListItem);
        type = quizListItem.getpType();



        if(type.equals("T")){
            int correct = quizListItem.getCorrect();
            imgLayout.setVisibility(View.GONE);
            textLayout.setVisibility(View.VISIBLE);
            edtProblem.setText(quizListItem.getpTitle());
            edtScore.setText(quizListItem.getScore());
            edtP1.setText(quizListItem.getEdt1());
            edtP2.setText(quizListItem.getEdt2());
            edtP3.setText(quizListItem.getEdt3());
            edtP4.setText(quizListItem.getEdt4());
            if(correct == 1)
                radioGroup.check(R.id.radioButton1);
            if(correct == 2)
                radioGroup.check(R.id.radioButton2);
            if(correct == 3)
                radioGroup.check(R.id.radioButton3);
            if(correct == 4)
                radioGroup.check(R.id.radioButton4);


        }
        if(type.equals("I")){
            int correct = quizListItem.getCorrect();
            textLayout.setVisibility(View.GONE);
            imgLayout.setVisibility(View.VISIBLE);
            edtProblem.setText(quizListItem.getpTitle());
            edtScore.setText(quizListItem.getScore());
//           //이미지1
            byte img1[] = quizListItem.getImg1();
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(img1, 0, img1.length);
            imageView1.setImageBitmap(bitmap1);
            //이미지2
            byte img2[] = quizListItem.getImg2();
            Bitmap bitmap2 = BitmapFactory.decodeByteArray(img2, 0, img2.length);
            imageView2.setImageBitmap(bitmap1);
             //이미지3
            byte img3[] = quizListItem.getImg3();
            Bitmap bitmap3 = BitmapFactory.decodeByteArray(img3, 0, img3.length);
            imageView3.setImageBitmap(bitmap3);
             //이미지4
            byte img4[] = quizListItem.getImg4();
            Bitmap bitmap4 = BitmapFactory.decodeByteArray(img4, 0, img4.length);
            imageView4.setImageBitmap(bitmap4);

            if(correct == 1)
                radioGroup.check(R.id.radioButton1);
            if(correct == 2)
                radioGroup.check(R.id.radioButton2);
            if(correct == 3)
                radioGroup.check(R.id.radioButton3);
            if(correct == 4)
                radioGroup.check(R.id.radioButton4);
        }
    }

    public void init(){
        //삭제,수정버튼
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        
        //문제점수 배점
        edtScore = findViewById(R.id.editTextTextScore);
        //문제
        edtProblem = findViewById(R.id.editTextProblem);
        //텍스트 레이아웃 이미지 레이아웃
        textLayout = findViewById(R.id.TextLayout);
        imgLayout = findViewById(R.id.ImageLayout);
        //라디오버튼 1,2,3,4
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        //텍스트 문제 1,2,3,4
        edtP1 = findViewById(R.id.edtP1);
        edtP2 = findViewById(R.id.edtP2);
        edtP3 = findViewById(R.id.edtP3);
        edtP4 = findViewById(R.id.edtP4);
        //이미지 문제 뷰 & 버튼
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        buttonImg1 = findViewById(R.id.buttonImg1);
        buttonImg2 = findViewById(R.id.buttonImg2);
        buttonImg3 = findViewById(R.id.buttonImg3);
        buttonImg4 = findViewById(R.id.buttonImg4);
    }
}