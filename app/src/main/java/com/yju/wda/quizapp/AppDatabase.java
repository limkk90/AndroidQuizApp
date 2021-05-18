package com.yju.wda.quizapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {QuizListItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
