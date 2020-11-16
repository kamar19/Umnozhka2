package com.kaidamar.umnozhka;

import android.content.SharedPreferences;
import android.os.Build;

public class MyLesson {
    private int countHeartLive = 0;
    private int countRightTask = 0, countWrongTask = 0;
    private int countCurrentRightTask = 0, countCurrentWrongTask = 0;
    private int countPoints = 0;
    private int progressBarTime = 300;
    private int progressBarCount = 1;
    private int countAllPrimerov = 0;
    private int countPrimerov = 1; // количество примеров в текущей отображаемой сессии, до 12, потом с 1
    private int progressBarSpeed = 1;
    private String stringCurrentAct = "*";
    private boolean endGame = false;
    private boolean lastGame = false; // используется толдько в main.activity?
    private boolean beginGame = false;

    private boolean beginFinishLeassonActivity = false;
    private String userNameDefault = "noname";


    public boolean isBeginGame() {        return beginGame;    }

    public void setBeginGame(boolean beginGame) {        this.beginGame = beginGame;    }

    public String getUserNameDefault() {
        return userNameDefault;
    }

    public void setUserNameDefault(String userNameDefault) {    this.userNameDefault = userNameDefault;    }

    public int getCountPoints() {
        return countPoints;
    }

    public void setCountPoints(int countPoints) {
        this.countPoints = countPoints;
    }

    public boolean isLastGame() {
        return lastGame;
    }

    public void setLastGame(boolean lastGame) {
        this.lastGame = lastGame;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public int getCountHeartLive() {
        return countHeartLive;
    }

    public void setCountHeartLive(int countHeartLive) {
        this.countHeartLive = countHeartLive;

    }

    public boolean isBeginFinishLeassonActivity() {
        return beginFinishLeassonActivity;
    }

    public void setBeginFinishLeassonActivity(boolean beginFinishLeassonActivity) {
        this.beginFinishLeassonActivity = beginFinishLeassonActivity;
    }

    public int getCountAllPrimerov() {
        return countAllPrimerov;
    }

    public int getCountWrongTask() {
        return countWrongTask;
    }

    public int getCountCurrentWrongTask() {
        return countCurrentWrongTask;
    }

    public int getProgressBarTime() {
        return progressBarTime;
    }

    public int getCountPrimerov() {
        return countPrimerov;
    }

    public int getProgressBarSpeed() {
        return progressBarSpeed;
    }

    public int getCountRightTask() {
        return countRightTask;
    }

    public int getCountCurrentRightTask() {
        return countCurrentRightTask;
    }

    public int getProgressBarCount() {
        return progressBarCount;
    }

    public void setProgressBarCount(int progressBarCount) {
        this.progressBarCount = progressBarCount;
    }

    public void setProgressBarSpeed(int progressBarSpeed) {
        this.progressBarSpeed = progressBarSpeed;
    }

    public String getStringCountAllPrimerov() {
        return String.valueOf(countAllPrimerov) + ", " + String.valueOf(countRightTask) + ", " + String.valueOf(countWrongTask);
    }

    public void setCountPrimerov(int countPrimerov) {
        this.countPrimerov = countPrimerov;
    }

    public void setCountAllPrimerov(int countAllPrimerov) {
        this.countAllPrimerov = countAllPrimerov;
    }

    public void setCountCurrentRightTask(int countCurrentRightTask) {
        this.countCurrentRightTask = countCurrentRightTask;
    }

    public void setCountCurrentWrongTask(int countCurrentWrongTask) {
        this.countCurrentWrongTask = countCurrentWrongTask;
    }


    public void setCountRightTask(int countRightTask) {
        this.countRightTask = countRightTask;
    }

    public void setCountWrongTask(int countWrongTask) {
        this.countWrongTask = countWrongTask;
    }

    public int upInterval(int step) {
        progressBarCount = progressBarCount + step;
        return progressBarCount;
    }

    public int downInterval(int step) {
        progressBarCount = progressBarCount - step;
        if (progressBarCount < 0) progressBarCount = 0;
        return progressBarCount;
    }

    public void saveValuesFinishLeassonActivity(String finishStringUserName) {
        SharedPreferences.Editor editorSharedPreferences = StartActivity.getSharedPreferences().edit();
        editorSharedPreferences.putString("valueUserNameDefault", finishStringUserName);
        editorSharedPreferences.putBoolean("valueBeginFinishLeassonActivity", this.isBeginFinishLeassonActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();
    }

    public void saveValuesBeginAndEndGame(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putBoolean("valueEndGame", endGame);
        editorSharedPreferences.putBoolean("valueBeginGame", beginGame);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();
    }

    public void saveValuesMyLesson(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putString("valueCountHeartLive", String.valueOf(countHeartLive));
        editorSharedPreferences.putString("valueCountAllPrimerov", String.valueOf(countAllPrimerov));
        editorSharedPreferences.putString("valueCountRightTask", String.valueOf(countRightTask));
        editorSharedPreferences.putString("valueCountWrongTask", String.valueOf(countWrongTask));
        editorSharedPreferences.putString("valueCountCurrentRightTask", String.valueOf(countCurrentRightTask));
        editorSharedPreferences.putString("valueCountCurrentWrongTask", String.valueOf(countCurrentWrongTask));
        editorSharedPreferences.putString("valueCountPoints", String.valueOf(countPoints));
        editorSharedPreferences.putString("valueProgressBarTime", String.valueOf(progressBarTime));
        editorSharedPreferences.putString("valueProgressBarCount", String.valueOf(progressBarCount));
        editorSharedPreferences.putString("valueProgressBarSpeed", String.valueOf(progressBarSpeed));
        editorSharedPreferences.putString("valueCountPrimerov", String.valueOf(countPrimerov));
        editorSharedPreferences.putString("valueStringCurrentAct", String.valueOf(stringCurrentAct));
        editorSharedPreferences.putBoolean("valueEndGame", endGame);
        editorSharedPreferences.putBoolean("valueLastGame", lastGame);
        editorSharedPreferences.putBoolean("valueBeginGame", beginGame);
        editorSharedPreferences.putBoolean("valueBeginFinishLeassonActivity", beginFinishLeassonActivity);
        editorSharedPreferences.putString("valueUserNameDefault", String.valueOf(userNameDefault));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editorSharedPreferences.apply();
        } else editorSharedPreferences.commit();
    }

//    public void saveFinishLeassonActivity(SharedPreferences sharedPreferences) {
//        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
//        editorSharedPreferences.putBoolean("valueBeginGame", beginFinishLeassonActivity);
////        editorSharedPreferences.putBoolean("valueEndGame", endGame);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//            editorSharedPreferences.apply();
//        } else editorSharedPreferences.commit();
//    }

    public void loadValuesMyLesson(SharedPreferences sharedPreferences) {
        countHeartLive = Integer.valueOf(sharedPreferences.getString("valueCountHeartLive", "0"));
        countAllPrimerov = Integer.valueOf(sharedPreferences.getString("valueCountAllPrimerov", "0"));
        countRightTask = Integer.valueOf(sharedPreferences.getString("valueCountRightTask", "0"));
        countWrongTask = Integer.valueOf(sharedPreferences.getString("valueCountWrongTask", "0"));
        countCurrentRightTask = Integer.valueOf(sharedPreferences.getString("valueCountCurrentRightTask", "0"));
        progressBarTime = Integer.valueOf(sharedPreferences.getString("valueProgressBarTime", "300"));
        progressBarCount = Integer.valueOf(sharedPreferences.getString("valueProgressBarCount", "1"));
        progressBarSpeed = Integer.valueOf(sharedPreferences.getString("valueProgressBarSpeed", "1"));
        countPrimerov = Integer.valueOf(sharedPreferences.getString("valueCountPrimerov", "1"));
        stringCurrentAct = sharedPreferences.getString("valueStringCurrentAct", "*");
        endGame = sharedPreferences.getBoolean("valueEndGame", false);
        beginGame = sharedPreferences.getBoolean("valueBeginGame", false);
        lastGame = sharedPreferences.getBoolean("valueLastGame", false);
        beginFinishLeassonActivity = sharedPreferences.getBoolean("valueBeginFinishLeassonActivity", false);
        userNameDefault = sharedPreferences.getString("valueUserNameDefault", "noname");
        countPoints = Integer.valueOf(sharedPreferences.getString("valueCountPoints", "0"));

    }


    public void startNewLesson() {
        countHeartLive = 0;
        countAllPrimerov = 0;
        countRightTask = 0;
        countWrongTask = 0;
        countCurrentRightTask = 0;
        countCurrentWrongTask = 0;
        progressBarTime = 30;//300
        progressBarCount = 1;
        countPrimerov = 1;
        progressBarSpeed = 1;
        stringCurrentAct = "*";
        endGame = false;
        lastGame = false;
        beginFinishLeassonActivity = false;
        countPoints = 0;
        beginGame = false;
    }
}
