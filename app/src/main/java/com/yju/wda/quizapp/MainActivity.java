package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yju.wda.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String adminPwd = "12345"; //설정 비밀번호
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
    }
}