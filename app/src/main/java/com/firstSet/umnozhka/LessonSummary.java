package com.firstSet.umnozhka;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

// возможно этот класс не нужен, так как буду сохранять в БД
// но временно,для проверки как работает recycleview сделаю через этот класс
public class LessonSummary {
    private String nameUser;
    private String imageFileName;
    private int countPoints;
    private String dateLesson;// без привязки ко времени
    private String stringPrimerovTasks; // countAllPrimerov+countRightTask+countWrongTask
    private String stringMDSA;//Multiply + Divide + Substrac + Add
    private String stringMultiplyNumbers; //MultiplyNumber1+...MultiplyNumber10


    public int getCountPoints() {
        return countPoints;
    }

    public void setCountPoints(int countPoints) {
        this.countPoints = countPoints;
    }


    public LessonSummary(String nameUser, String imageFileName, int countPoints, String stringPrimerovTasks, String stringMDSA, String stringMultiplyNumbers) {
        this.nameUser = nameUser;
        this.imageFileName = imageFileName;
        this.countPoints = countPoints;
        this.dateLesson = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
        this.stringPrimerovTasks = stringPrimerovTasks;
        this.stringMDSA = stringMDSA;
        this.stringMultiplyNumbers = stringMultiplyNumbers;
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

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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
