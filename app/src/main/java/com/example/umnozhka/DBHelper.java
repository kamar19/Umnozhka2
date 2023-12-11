package com.example.umnozhka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "lessons", null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                    "nameUser TEXT, dateLesson TEXT, image INTEGER,idResultLesson INTEGER, " +
                    " stringPrimerovTasks TEXT, stringMDSA TEXT, stringMultiplyNumbers TEXT )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

