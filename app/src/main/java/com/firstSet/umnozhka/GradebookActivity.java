package com.firstSet.umnozhka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class GradebookActivity extends AppCompatActivity {
    List<LessonSummary> lessonSummaryList = new ArrayList<>();
    public final static String nameDb = "lessonSummary7.db";
    private SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradebook);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this, lessonSummaryList);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        db = getBaseContext().openOrCreateDatabase(nameDb, MODE_PRIVATE, null);
        cursor = db.rawQuery("SELECT * FROM lessons ", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                LessonSummary tempLessonSummary = new LessonSummary(
                        // 0 - id
                        cursor.getString(1), // 1 - nameUser
                        cursor.getInt(2),    // 2 - countPoints
                        cursor.getString(3), // 3 - dateLesson
                        cursor.getString(4), // 4 - stringPrimerovTasks
                        cursor.getString(5), // 5 - stringMDSA
                        cursor.getString(6));// 6 - stringMultiplyNumbers
                lessonSummaryList.add(tempLessonSummary);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}