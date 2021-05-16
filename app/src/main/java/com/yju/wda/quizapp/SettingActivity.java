package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class SettingActivity extends AppCompatActivity {
    //상단
    ToggleButton toggleButton;
    Button buttonSave;
    EditText edtScore, edtProblem;
    //텍스트 이미지 레이아웃
    LinearLayout textLayout, imgLayout;

    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    //텍스트 문제 출제
    EditText edtP1, edtP2, edtP3, edtP4;
    //이미지 문제 출제
    ImageView imageView1, imageView2, imageView3, imageView4;
    Button buttonImg1, buttonImg2, buttonImg3, buttonImg4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        buttonSave = (Button)findViewById(R.id.buttonSave);
        edtScore = findViewById(R.id.editTextTextScore);
        edtProblem = findViewById(R.id.editTextProblem);
        textLayout = findViewById(R.id.TextLayout);
        imgLayout = findViewById(R.id.ImageLayout);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        edtP1 = findViewById(R.id.edtP1);
        edtP2 = findViewById(R.id.edtP2);
        edtP3 = findViewById(R.id.edtP3);
        edtP4 = findViewById(R.id.edtP4);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        buttonImg1 = findViewById(R.id.buttonImg1);
        buttonImg2 = findViewById(R.id.buttonImg2);
        buttonImg3 = findViewById(R.id.buttonImg3);
        buttonImg4 = findViewById(R.id.buttonImg4);
        
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else{

                }
            }
        });
    }
}