package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class QuizActivity extends AppCompatActivity {
    Intent intent;
    String chocie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        intent = getIntent();
        chocie = intent.getStringExtra("choice");
        Log.i("Quiz", "onCreate: " + chocie);
    }
}