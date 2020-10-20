package com.example.umnozhka;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

// возможно этот класс не нужен, так как буду сохранять в БД
// но временно,для проверки как работает recycleview сделаю через этот класс
public class LessonSummary {
    private String dateLesson;// без привязки ко времени
    private String nameUser;
    private int image;
    private int idResultLesson;//номер занятия
    private String stringPrimerovTasks; // countAllPrimerov+countRightTask+countWrongTask
    private String stringMDSA;//Multiply + Divide + Substrac + Add
    private String stringMultiplyNumbers; //MultiplyNumber1+...MultiplyNumber10
    private static int countIdResultLesson;

    public LessonSummary(Date dateLesson, String nameUser, String imageName, int idResultLesson, String stringPrimerovTasks, String stringMDSA, String stringMultiplyNumbers) {
        countIdResultLesson++;
        this.dateLesson = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.nameUser = nameUser;
        this.image = countIdResultLesson;
        this.idResultLesson = countIdResultLesson;
        this.stringPrimerovTasks = stringPrimerovTasks;
        this.stringMDSA = stringMDSA;
        this.stringMultiplyNumbers = stringMultiplyNumbers;
    }

    public static int getCountIdResultLesson() {
        return countIdResultLesson;
    }

    public static void setCountIdResultLesson(int countIdResultLesson) {
        LessonSummary.countIdResultLesson = countIdResultLesson;
    }

    public String getDateLesson() {
        return dateLesson;
    }

    public String getNameUser() {
        return nameUser;
    }

    public int getImage() {
        return image;
    }

    public String getStringPrimerovTasks() {
        return stringPrimerovTasks;
    }

    public String getStringMDSA() {
        return stringMDSA;
    }

    public String getStringMultiplyNumbers() {
        return stringMultiplyNumbers;
    }

    public void saveLessonToDB(SQLiteDatabase db) {
//        ContentValues contentValues = new ContentValues();
//        SQLiteDatabase db = openOrCreateDatabase("lessons.db", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "nameUser TEXT, dateLesson TEXT, image INTEGER,idResultLesson INTEGER, " +
                " stringPrimerovTasks TEXT, stringMDSA TEXT, stringMultiplyNumbers TEXT )");
        db.execSQL("INSERT INTO lessons VALUES ('" + this.nameUser + "' , '" + this.dateLesson + "' , " + this.image
                + ", '" + this.stringPrimerovTasks + "', '" + this.stringMDSA + "', '" + this.stringMultiplyNumbers + "')");
    }


}
