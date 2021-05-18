package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yju.wda.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String adminPwd = "1"; //설정 비밀번호
    ActivityMainBinding mainBinding;
    ImageView imgAdminBtn;
    ConstraintLayout mainLayout, passwordLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
//        imgAdminBtn = (ImageView)findViewById(R.id.imgAdminButton);
        mainLayout = findViewById(R.id.mainLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        mainBinding.mainLayout.setVisibility(View.VISIBLE);
        mainBinding.passwordLayout.setVisibility(View.GONE);

        //이미지 버튼 눌렀을 때 관리자 모드 들어가기
        mainBinding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBinding.mainLayout.setVisibility(View.GONE);
                mainBinding.passwordLayout.setVisibility(View.VISIBLE);

                mainBinding.pwdBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String insertPwd = mainBinding.pwdEditText.getText().toString();
                        if (adminPwd.equals(insertPwd)){
                            Intent intent = new Intent(MainActivity.this, QuizList.class);
                            startActivity(intent);
                            //인텐트 이동
                        }else{
                            mainBinding.mainLayout.setVisibility(View.VISIBLE);
                            mainBinding.passwordLayout.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "비번틀림", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        //============================================================
        //EasyMode, HardMode초이스
        mainBinding.mainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.easyRadioBtn){
                    mainBinding.mainSelectText.setText("이지모드ㄱㄱ");
                    mainBinding.mainStartBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                            intent.putExtra("choice", "E");
                            startActivity(intent);
                        }
                    });
                }
                if(checkedId == R.id.hardRadioBtn){
                    mainBinding.mainSelectText.setText("하드모드ㄱㄱ");
                    mainBinding.mainStartBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                            intent.putExtra("choice", "H");
                            startActivity(intent);
                        }
                    });
                }
            }
        });


    }
}