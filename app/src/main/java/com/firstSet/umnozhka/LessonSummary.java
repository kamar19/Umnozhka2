package com.firstSet.umnozhka;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.SharedPreferences;
import android.os.Build;

public class LessonSummary {
    private String nameUser;
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

    public LessonSummary(String nameUser, int countPoints, String  dateLesson, String stringPrimerovTasks, String stringMDSA, String stringMultiplyNumbers) {
        this.nameUser = nameUser;
        this.countPoints = countPoints;
        if (dateLesson == null || dateLesson.length()<5) {
            this.dateLesson = new SimpleDateFormat("dd.MM.yy").format(new Date());
        } else {
            this.dateLesson = dateLesson;
        }
        this.stringPrimerovTasks = stringPrimerovTasks;
        this.stringMDSA = stringMDSA;
        this.stringMultiplyNumbers = stringMultiplyNumbers;
    }

    public LessonSummary(SharedPreferences sharedPreferences) {
        loadValuesLessonSummary(sharedPreferences);
    }

    public String getDateLesson() {
        return dateLesson;
    }

    public String getNameUser() {
        return nameUser;
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

    public void loadValuesLessonSummary(SharedPreferences sharedPreferences){
        this.nameUser = sharedPreferences.getString("nameUserLessonSummary", "Noname");;
        this.countPoints = sharedPreferences.getInt("countPointsLessonSummary", 0);
        this.dateLesson = sharedPreferences.getString("dateLessonSummary", "");;
        this.stringPrimerovTasks = sharedPreferences.getString("primerovTasksLessonSummary", "");;
        this.stringMDSA = sharedPreferences.getString("mDSALessonSummary", "");;
        this.stringMultiplyNumbers = sharedPreferences.getString("multiplyNumbersLessonSummary", "");;
    }

    public void saveValuesLessonSummary(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putString("nameUserLessonSummary", this.nameUser);
        editorSharedPreferences.putInt("countPointsLessonSummary", this.countPoints);
        editorSharedPreferences.putString("dateLessonSummary", this.dateLesson);
        editorSharedPreferences.putString("primerovTasksLessonSummary", this.stringPrimerovTasks);
        editorSharedPreferences.putString("mDSALessonSummary", this.stringMDSA);
        editorSharedPreferences.putString("multiplyNumbersLessonSummary", this.stringMultiplyNumbers);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();
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
