package com.yju.wda.quizapp;

import android.graphics.drawable.Drawable;

public class QuizListItem {
    Drawable iconDrawable;
    String pTitle;
    String pRegDate;


    @Override
    public String toString() {
        return "QuizListItem{" +
                "iconDrawable=" + iconDrawable +
                ", pTitle='" + pTitle + '\'' +
                ", pRegDate='" + pRegDate + '\'' +
                '}';
    }

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
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
