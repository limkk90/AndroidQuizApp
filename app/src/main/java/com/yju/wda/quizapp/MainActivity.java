package com.yju.wda.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yju.wda.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String adminPwd = "12345"; //설정 비밀번호
    ActivityMainBinding mainBinding;
    ImageView imgAdminBtn;
    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
//        imgAdminBtn = (ImageView)findViewById(R.id.imgAdminButton);

        mainBinding.imgAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Main:" , "이미지버튼 눌림");
                mainBinding.mainLayout.setVisibility(View.GONE);
                mainBinding.passwordLayout.setVisibility(View.VISIBLE);
                String insertPwd = mainBinding.pwdEditText.getText().toString();
                if(adminPwd.equals(insertPwd)){
                    //인텐트 이동 ListAciticty로
                }else{
                    mainBinding.mainLayout.setVisibility(View.VISIBLE);
                    mainBinding.passwordLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}