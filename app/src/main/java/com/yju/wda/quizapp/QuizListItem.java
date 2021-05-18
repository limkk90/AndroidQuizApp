package com.yju.wda.quizapp;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Arrays;

import static androidx.room.ColumnInfo.BLOB;

@Entity
public class QuizListItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    String pType;
    String pTitle;
    String pRegDate;
    String score;
    int correct;
    String edt1, edt2, edt3, edt4;
    @ColumnInfo(typeAffinity = BLOB)
    byte[] img1;
    @ColumnInfo(typeAffinity = BLOB)
    byte[] img2;
    @ColumnInfo(typeAffinity = BLOB)
    byte[] img3;
    @ColumnInfo(typeAffinity = BLOB)
    byte[] img4;

    public QuizListItem() {

    }

    public QuizListItem(String pType, String pTitle, String pRegDate, String score, int correct, String edt1, String edt2, String edt3, String edt4,
                        byte[] img1, byte[] img2, byte[] img3, byte[] img4) {
        this.id = id;
        this.pType = pType;
        this.pTitle = pTitle;
        this.pRegDate = pRegDate;
        this.score = score;
        this.correct = correct;
        this.edt1 = edt1;
        this.edt2 = edt2;
        this.edt3 = edt3;
        this.edt4 = edt4;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
    }

    @Override
    public String toString() {
        return "QuizListItem{" +
                "id=" + id +
                ", pType='" + pType + '\'' +
                ", pTitle='" + pTitle + '\'' +
                ", pRegDate='" + pRegDate + '\'' +
                ", score='" + score + '\'' +
                ", correct=" + correct +
                ", edt1='" + edt1 + '\'' +
                ", edt2='" + edt2 + '\'' +
                ", edt3='" + edt3 + '\'' +
                ", edt4='" + edt4 + '\'' +
                ", img1=" + Arrays.toString(img1) +
                ", img2=" + Arrays.toString(img2) +
                ", img3=" + Arrays.toString(img3) +
                ", img4=" + Arrays.toString(img4) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpRegDate() {
        return pRegDate;
    }

    public void setpRegDate(String pRegDate) {
        this.pRegDate = pRegDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public String getEdt1() {
        return edt1;
    }

    public void setEdt1(String edt1) {
        this.edt1 = edt1;
    }

    public String getEdt2() {
        return edt2;
    }

    public void setEdt2(String edt2) {
        this.edt2 = edt2;
    }

    public String getEdt3() {
        return edt3;
    }

    public void setEdt3(String edt3) {
        this.edt3 = edt3;
    }

    public String getEdt4() {
        return edt4;
    }

    public void setEdt4(String edt4) {
        this.edt4 = edt4;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }

    public byte[] getImg3() {
        return img3;
    }

    public void setImg3(byte[] img3) {
        this.img3 = img3;
    }

    public byte[] getImg4() {
        return img4;
    }

    public void setImg4(byte[] img4) {
        this.img4 = img4;
    }
}
