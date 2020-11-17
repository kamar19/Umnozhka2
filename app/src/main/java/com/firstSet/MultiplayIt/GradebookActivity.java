package com.firstSet.MultiplayIt;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.umnozhka.R;

import java.util.ArrayList;
import java.util.List;

public class GradebookActivity extends Activity implements View.OnClickListener {
    List<LessonSummary> lessonSummaryList = new ArrayList<>();
    private SQLiteDatabase db;
    Cursor cursor;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradebook);
        setInitialData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, lessonSummaryList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        button = findViewById(R.id.gradeBookActivityButtonClear);
        button.setOnClickListener(this);

    }

    private void setInitialData() {
        db = getBaseContext().openOrCreateDatabase("lessonSummary.db", MODE_PRIVATE, null);
//        db.e
//        if (db.isOpen()) {
        try {
            cursor = db.rawQuery("SELECT * FROM lessons3 ", null);
        } catch (Exception e) {
            // таблица не создана

        }
        if (cursor != null)
            if ((cursor.getCount() > 0)) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {
//        }
//        while (cursor.moveToNext()) {
                    //  ( 'id'=?, '" + lessonSummary.getNameUser() + "', " + lessonSummary.getCountPoints() +", '" + lessonSummary.getDateLesson() + "' , '" + lessonSummary.getImage()
                    //                + "', '" + lessonSummary.getStringPrimerovTasks() + "', '" + lessonSummary.getStringMDSA() + "', '" + lessonSummary.getStringMultiplyNumbers() + "')";
                    LessonSummary tempLessonSummary = new LessonSummary(
//    public LessonSummary(String nameUser, String imageFileName, int countPoints, String stringPrimerovTasks, String stringMDSA, String stringMultiplyNumbers) {
//                    cursor.getInt(1),//ID
                            cursor.getString(1),//getNameUser()
                            cursor.getString(2),//getDateLesson
                            cursor.getInt(3), //countPoints
                            cursor.getString(4),//getStringMDSA
                            cursor.getString(5));//getStringMultiplyNumbers
                    lessonSummaryList.add(tempLessonSummary);
                    cursor.moveToNext();
                }
//            }
                cursor.close();
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gradeBookActivityButtonClear: {
                db = getBaseContext().openOrCreateDatabase("lessonSummary.db", MODE_PRIVATE, null);
                try {
//                    cursor = db.rawQuery("DELETE FROM lessons3 ", null);
                    db.delete("lessons3",null, null);

                } catch (Exception e) {
                    // таблица не создана
                }
                db.close();
                finish();
            break;
        }
    }

}

//    private String DateLessonToNewFormat(String dataLesson) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date;
//        String returnDateString="";
//        try {
//             date = format.parse(dataLesson);
//            returnDateString = format.format(date);
//        } catch (ParseException e) { // TODO Auto-generated catch block e.printStackTrace(); }
//        }
//
//            return returnDateString;
//        }
//

//    public void OpenDBtoLessonSummary(SQLiteDatabase db) {
//        ContentValues contentValues = new ContentValues();
//        SQLiteDatabase db = openOrCreateDatabase("lessons.db", Context.MODE_PRIVATE, null);
//        SQLiteDatabase db = new SQLiteDatabase();
//        String string0=""
// Удалю таблицу, через создание новой БД?
//      db.delete("lessons", null, null);
//        if (db.isOpen()) db.close();

//        String string = "CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
//                "'nameUser' TEXT, 'countPoints' INTEGER, 'dateLesson' TEXT, 'imageFileName' TEXT, " +
//                " 'stringPrimerovTasks' TEXT, 'stringMDSA' TEXT, 'stringMultiplyNumbers' TEXT )";
//        db.execSQL(string);
//        String string2 = "INSERT INTO lessons VALUES ( 'id'=?, '" + lessonSummary.getNameUser() + "', " + lessonSummary.getCountPoints() + ", '" + lessonSummary.getDateLesson() + "' , '" + lessonSummary.getImage()
//                + "', '" + lessonSummary.getStringPrimerovTasks() + "', '" + lessonSummary.getStringMDSA() + "', '" + lessonSummary.getStringMultiplyNumbers() + "')";
//        db.execSQL(string2);
////        нужно ли в БД первую запись делать с ноунем и ноуфото?
//        db.close();

//        String string = "CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
//                "nameUser TEXT, countPoints INTEGER, dateLesson TEXT, image INTEGER, idResultLesson INTEGER, " +
//                " stringPrimerovTasks TEXT, stringMDSA TEXT, stringMultiplyNumbers TEXT )";
//        db.execSQL(string);
//        String string2 ="INSERT INTO lessons VALUES ('" + this.nameUser + "', " + this.countPoints +", '" + this.dateLesson + "' , " + this.imageFileName
//                + ", '" + this.stringPrimerovTasks + "', '" + this.stringMDSA + "', '" + this.stringMultiplyNumbers + "')";
//                db.execSQL(string2);
////        нужно ли в БД первую запись делать с ноунем и ноуфото?
//        db.close ();
//    }
}