package com.yju.wda.quizapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Update
    void update(QuizListItem quizListItem);

    @Delete
    void delete(QuizListItem quizListItem);

}
