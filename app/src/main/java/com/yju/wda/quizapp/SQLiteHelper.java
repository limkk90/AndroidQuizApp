package com.yju.wda.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void insertData(QuizListItem Quiz){
        Log.i("data", "insertData: "+ Quiz.getpType());
        Log.i("data", "insertData: "+ Quiz.getpTitle());
        Log.i("data", "insertData: "+ Quiz.getpRegDate());

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", Quiz.getpType());
        values.put("type", Quiz.getpTitle());
        values.put("type", Quiz.getScore());
        values.put("type", Quiz.getEdt1());
        values.put("type", Quiz.getEdt2());
        values.put("type", Quiz.getEdt3());
        values.put("type", Quiz.getEdt4());
        values.put("type", Quiz.getCorrect());

        //0 1 2 3 4 5 6 7 8 9 10 11 12  13
        String sql = "INSERT INTO QUIZ VALUES(null, ?, ? ,? ,?, ?, ? ,? ,? , ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

//        statement.bindString(1, type);
//        statement.bindString(2, problem);
//        statement.bindString(3, regDate);
//        statement.bindString(4, score);
//        statement.bindString(5, edt1);
//        statement.bindString(6, edt2);
//        statement.bindString(7, edt3);
//        statement.bindString(8, edt4);
//        statement.bindLong(9, correct);

    }

//    public void insetData(String type, String problem, String regDate, String score, byte[] img1, byte[] img2, byte[] img3, byte[] img4, int correct){
//        SQLiteDatabase database = getWritableDatabase();
//        String sql = "INSERT INTO QUIZ VALUES(null, ?, ? ,? ,?, ?, ? ,? ,? , ?)";
//        SQLiteStatement statement = database.compileStatement(sql);
//        statement.clearBindings();
//
//        statement.bindString(1, type);
//        statement.bindString(2, problem);
//        statement.bindString(3, regDate);
//        statement.bindString(4, score);
//        statement.bindBlob(5, img1);
//        statement.bindBlob(6, img2);
//        statement.bindBlob(7, img3);
//        statement.bindBlob(8, img4);
//        statement.bindLong(9, correct);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS QUIZTXT(Id INTEGER PRIMARY KEY AUTOINCREMENT, type VARCHAR, problem VARCHAR, regdate VARCHAR, score VARCHAR," +
                " edt1 VARCHAR, edt2 VARCHAR, edt3 VARCHAR, edt4 VARCHAR,img1 BLOB, img2 BLOB, img3 BLOB, img4 BLOB,  correct INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table QUIZTXT";
        db.execSQL(sql);
        onCreate(db);
    }
}
