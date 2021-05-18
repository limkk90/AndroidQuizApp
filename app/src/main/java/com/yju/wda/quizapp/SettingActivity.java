package com.yju.wda.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    LocalDateTime dateTime;
    boolean checked = false;
    Intent intent;
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
    Bitmap bitmap1, bitmap2, bitmap3, bitmap4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        long systemTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String dTime = format.format(systemTime);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "quiz-db")
                .allowMainThreadQueries()
                .build();

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
                    Toast.makeText(SettingActivity.this, "이미지 눌림", Toast.LENGTH_SHORT).show();
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

                    buttonImg1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, 1);
                        }
                    });
                    buttonImg2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, 2);
                        }
                    });
                    buttonImg3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, 3);
                        }
                    });
                    buttonImg4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, 4);
                        }
                    });
                    buttonSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stredtProblem = edtProblem.getText().toString();
                            strEdtScore = edtScore.getText().toString();
                            Log.i("Setting", "onClick: " + stredtProblem);
                            Log.i("Setting", "onClick: " + stredtProblem);
                            Log.i("Setting", "onClick: " + bitmap1);
                            Log.i("Setting", "onClick: " + bitmap2);
                            Log.i("Setting", "onClick: " + bitmap3);
                            Log.i("Setting", "onClick: " + bitmap4);
                            db.quizDao().insert(new QuizListItem("T", stredtProblem, dTime, strEdtScore, correct,
                                    null, null, null, null, imageViewToByte(imageView1), imageViewToByte(imageView2), imageViewToByte(imageView3), imageViewToByte(imageView4)));

                        }
                    });
                }
                //===================================이미지문제 출제=============================이미지 문제 출제
                else{
                    Toast.makeText(SettingActivity.this, "텍스트 눌림", Toast.LENGTH_SHORT).show();
                    textLayout.setVisibility(View.VISIBLE);
                    imgLayout.setVisibility(View.GONE);

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
                            long systemTime = System.currentTimeMillis();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                            String dTime = format.format(systemTime);
                            stredtProblem = edtProblem.getText().toString();
                            strEdtScore = edtScore.getText().toString();
                            strEdtP1 = edtP1.getText().toString();
                            strEdtP2 = edtP2.getText().toString();
                            strEdtP3 = edtP3.getText().toString();
                            strEdtP4 = edtP4.getText().toString();
                            Log.i("Setting1", "onClick: " + stredtProblem);
                            Log.i("Setting1", "onClick: " + strEdtScore);
                            Log.i("Setting1", "onClick: " + strEdtP1);
                            Log.i("Setting1", "onClick: " + strEdtP2);
                            Log.i("Setting1", "onClick: " + strEdtP3);
                            Log.i("Setting1", "onClick: " + strEdtP4);
                            Log.i("Setting1", "onClick: " + correct);
                            //DB에 넣어줘야 됨
                            db.quizDao().insert(new QuizListItem("T", stredtProblem, dTime, strEdtScore, correct,
                                    strEdtP1, strEdtP2, strEdtP3, strEdtP4, null, null, null, null));
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    bitmap1 = BitmapFactory.decodeStream(in);
                    in.close();
                    Log.e("Img", "onActivityResult: " + bitmap1 );
                    imageView1.setImageBitmap(bitmap1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    bitmap2 = BitmapFactory.decodeStream(in);
                    in.close();
                    Log.e("Img", "onActivityResult: " + bitmap2 );
                    imageView2.setImageBitmap(bitmap2);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(requestCode == 3){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    bitmap3 = BitmapFactory.decodeStream(in);
                    in.close();
                    Log.e("Img", "onActivityResult: " + bitmap3 );
                    imageView3.setImageBitmap(bitmap3);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(requestCode == 4){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    bitmap4 = BitmapFactory.decodeStream(in);
                    in.close();
                    Log.e("Img", "onActivityResult: " + bitmap4 );
                    imageView4.setImageBitmap(bitmap4);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] imageViewToByte(ImageView image){ //9
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}