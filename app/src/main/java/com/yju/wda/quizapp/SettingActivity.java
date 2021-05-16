package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class SettingActivity extends AppCompatActivity {
    //상단
    ToggleButton toggleButton;
    //저장버튼
    Button buttonSave;
    //점수, 문제
    EditText edtScore, edtProblem;
    String strEdtScore, stredtProblem;
    //텍스트 이미지 레이아웃
    LinearLayout textLayout, imgLayout;

    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    //텍스트 문제 출제
    EditText edtP1, edtP2, edtP3, edtP4;
    String strEdtP1, strEdtP2, strEdtP3, strEdtP4;
    int correct;

    //이미지 문제 출제
    ImageView imageView1, imageView2, imageView3, imageView4;
    Button buttonImg1, buttonImg2, buttonImg3, buttonImg4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //토글버튼
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        //저장버튼
        buttonSave = (Button)findViewById(R.id.buttonSave);
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

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //텍스트 문제 출제
                if(isChecked){
                    textLayout.setVisibility(View.GONE);
                    imgLayout.setVisibility(View.VISIBLE);
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId == R.id.radioButton1){
                                correct = 1;
                            }
                            if(checkedId == R.id.radioButton2){
                                correct = 2;
                            }
                            if(checkedId == R.id.radioButton3){
                                correct = 3;
                            }
                            if(checkedId == R.id.radioButton4){
                                correct = 4;
                            }

                        }
                    });
                    buttonSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stredtProblem = edtProblem.getText().toString();
                            strEdtScore = edtScore.getText().toString();
                            strEdtP1 = edtP1.getText().toString();
                            strEdtP2 = edtP2.getText().toString();
                            strEdtP3 = edtP3.getText().toString();
                            strEdtP4 = edtP4.getText().toString();
                            Log.i("Setting", "onClick: " + stredtProblem);
                            Log.i("Setting", "onClick: " + strEdtScore);
                            Log.i("Setting", "onClick: " + strEdtP1);
                            Log.i("Setting", "onClick: " + strEdtP2);
                            Log.i("Setting", "onClick: " + strEdtP3);
                            Log.i("Setting", "onClick: " + strEdtP4);
                            Log.i("Setting", "onClick: " + correct);

                            //DB에 넣어줘야 됨
                        }
                    });
                }
                //이미지 문제 출제
                else{
                    textLayout.setVisibility(View.VISIBLE);
                    imgLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}