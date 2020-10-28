package com.example.umnozhka;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

// возможно этот класс не нужен, так как буду сохранять в БД
// но временно,для проверки как работает recycleview сделаю через этот класс
public class LessonSummary {
    private String dateLesson;// без привязки ко времени
    private String nameUser;
//    private String imageFileName;
    String imageFileName;

    private int idResultLesson;//номер занятия, должен быть для все уникалным. берется из БД.
    private String stringPrimerovTasks; // countAllPrimerov+countRightTask+countWrongTask
    private String stringMDSA;//Multiply + Divide + Substrac + Add
    private String stringMultiplyNumbers; //MultiplyNumber1+...MultiplyNumber10

    public LessonSummary(String nameUser, String imageFileName, String stringPrimerovTasks, String stringMDSA, String stringMultiplyNumbers) {
        this.dateLesson = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
        this.nameUser = nameUser;
        this.stringPrimerovTasks = stringPrimerovTasks;
        this.stringMDSA = stringMDSA;
        this.stringMultiplyNumbers = stringMultiplyNumbers;
        this.imageFileName = imageFileName;
    }


    public String getDateLesson() {
        return dateLesson;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getImage() {
        return imageFileName;
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
        db.execSQL("INSERT INTO lessons VALUES ('" + this.nameUser + "' , '" + this.dateLesson + "' , " + this.imageFileName
                + ", '" + this.stringPrimerovTasks + "', '" + this.stringMDSA + "', '" + this.stringMultiplyNumbers + "')");
//        нужно ли в БД первую запись делать с ноунем и ноуфото?
    }

//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        this.imageFileName = image.getAbsolutePath();
//        return image;
//    }


}
