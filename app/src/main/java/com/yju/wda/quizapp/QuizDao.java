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

    @Query("SELECT * FROM QuizListItem WHERE id = :id")
    QuizListItem getQuiz(int id);

    @Insert
    void insert(QuizListItem quizListItem);


}
