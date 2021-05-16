package com.yju.wda.quizapp;

import android.graphics.drawable.Drawable;

public class QuizListItem {
    String pType;
    String pTitle;
    String pRegDate;

    @Override
    public String toString() {
        return "QuizListItem{" +
                "pType='" + pType + '\'' +
                ", pTitle='" + pTitle + '\'' +
                ", pRegDate='" + pRegDate + '\'' +
                '}';
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
}
