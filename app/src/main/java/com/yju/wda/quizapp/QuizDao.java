package com.yju.wda.quizapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM QuizListItem")
    List<QuizListItem> getAll();

    @Insert
    void insert(QuizListItem quizListItem);



}
