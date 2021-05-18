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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
    int upCorrect;
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



            // 텍스트 레이아웃 업데이트 버튼 눌렀을 때
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "텍스트 업뎃눌림", Toast.LENGTH_SHORT).show();

                    long systemTime = System.currentTimeMillis();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                    String dTime = format.format(systemTime);
                    stredtProblem = edtProblem.getText().toString();
                    strEdtScore = edtScore.getText().toString();
                    strEdtP1 = edtP1.getText().toString();
                    strEdtP2 = edtP2.getText().toString();
                    strEdtP3 = edtP3.getText().toString();
                    strEdtP4 = edtP4.getText().toString();

                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId == R.id.radioButton1){
                                upCorrect = 1;
                            }
                            if(checkedId == R.id.radioButton2){
                                upCorrect = 2;
                            }
                            if(checkedId == R.id.radioButton3){
                                upCorrect = 3;
                            }
                            if(checkedId == R.id.radioButton4){
                                upCorrect = 4;
                            }
                        }
                    });
                    Log.i("textUpdate", "onClick: " + stredtProblem);
                    Log.i("textUpdate", "onClick: " + strEdtScore);
                    Log.i("textUpdate", "onClick: " + strEdtP1);
                    Log.i("textUpdate", "onClick: " + strEdtP2);
                    Log.i("textUpdate", "onClick: " + strEdtP3);
                    Log.i("textUpdate", "onClick: " + strEdtP4);
                    Log.i("textUpdate", "onClick: " + upCorrect);
                    quizListItem.setpTitle(stredtProblem);
                    quizListItem.setpRegDate(dTime);
                    quizListItem.setScore(strEdtScore);
                    quizListItem.setEdt1(strEdtP1);
                    quizListItem.setEdt2(strEdtP2);
                    quizListItem.setEdt3(strEdtP3);
                    quizListItem.setEdt4(strEdtP4);
                    quizListItem.setCorrect(upCorrect);

                    db.quizDao().update(quizListItem);
                    finish();
                }
            });

            //텍스트 레이아웃 삭제버튼 눌렀을 때
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "버튼 눌림", Toast.LENGTH_SHORT).show();
                    db.quizDao().delete(quizListItem);
                    Toast.makeText(getApplicationContext(), "텍스트 데이터 삭제완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
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

            buttonImg1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 1);
                }
            });

            buttonImg2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 2);
                }
            });

            buttonImg3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 3);
                }
            });

            buttonImg4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 4);
                }
            });


            //이미지 레이아웃 업데이트 버튼
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "이미지 업뎃눌림", Toast.LENGTH_SHORT).show();

                    long systemTime = System.currentTimeMillis();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                    String dTime = format.format(systemTime);
                    stredtProblem = edtProblem.getText().toString();
                    strEdtScore = edtScore.getText().toString();

                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId == R.id.radioButton1){
                                upCorrect = 1;
                            }
                            if(checkedId == R.id.radioButton2){
                                upCorrect = 2;
                            }
                            if(checkedId == R.id.radioButton3){
                                upCorrect = 3;
                            }
                            if(checkedId == R.id.radioButton4){
                                upCorrect = 4;
                            }
                        }
                    });

                    quizListItem.setpTitle(stredtProblem);
                    quizListItem.setpRegDate(dTime);
                    quizListItem.setScore(strEdtScore);
                    quizListItem.setImg1(imageViewToByte(imageView1));
                    quizListItem.setImg2(imageViewToByte(imageView2));
                    quizListItem.setImg3(imageViewToByte(imageView3));
                    quizListItem.setImg4(imageViewToByte(imageView4));
                    quizListItem.setCorrect(upCorrect);
                    db.quizDao().update(quizListItem);
                    finish();
//                    quizListItem.setpTitle(stredtProblem);
//                    quizListItem.setpRegDate(dTime);
//                    quizListItem.setScore(strEdtScore);
//                    quizListItem.setEdt1(strEdtP1);
//                    quizListItem.setEdt2(strEdtP2);
//                    quizListItem.setEdt3(strEdtP3);
//                    quizListItem.setEdt4(strEdtP4);
//                    quizListItem.setCorrect(upCorrect);
//
//                    db.quizDao().update(quizListItem);
//                    finish();
                }
            });
            //이미지 레이아웃 삭제 버튼
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "버튼 눌림", Toast.LENGTH_SHORT).show();
                    db.quizDao().delete(quizListItem);
                    Toast.makeText(getApplicationContext(), "이미지 데이터 삭제완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    bitmap1 = BitmapFactory.decodeStream(in);
                    in.close();
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