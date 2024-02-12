package com.firstSet.umnozhka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase myDataBase;
    public static final int DATABASE_VERSION = 2;
    public String DB_NAME;

    public DBHelper(String DB_NAME, Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.DB_NAME = DB_NAME;
        myDataBase = this.getWritableDatabase();
    }

    public void saveLessonSummaryToDB(LessonSummary lessonSummary) {
        String string = "CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "'nameUser' TEXT, 'countPoints' INTEGER, 'dateLesson' TEXT, " +
                " 'stringPrimerovTasks' TEXT, 'stringMDSA' TEXT, 'stringMultiplyNumbers' TEXT )";
        myDataBase.execSQL(string);
        if (lessonSummary != null) {
            String string2 = "INSERT INTO lessons VALUES ( 'id'=?, '" + lessonSummary.getNameUser() + "', " + lessonSummary.getCountPoints() + ", '" + lessonSummary.getDateLesson()
                    + "', '" + lessonSummary.getStringPrimerovTasks() + "', '" + lessonSummary.getStringMDSA() + "', '" + lessonSummary.getStringMultiplyNumbers() + "')";
            myDataBase.execSQL(string2);
        }
        ;
        myDataBase.close();
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

