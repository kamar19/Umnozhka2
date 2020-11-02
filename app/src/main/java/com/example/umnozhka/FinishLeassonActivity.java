package com.example.umnozhka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FinishLeassonActivity extends Activity implements View.OnClickListener {
    Button finishButtonSave;
//            finishButtonTakeFoto;
    EditText finishStringUserName;
    TextView finishStringPrimerovTasks, finishStringActions, finishStringMultiplyNumbers, finishTextValueViewPoints, finishTextViewPoints;
    private SQLiteDatabase db;
    //    MyLesson myLesson;
    LessonSummary lessonSummary; // на Основе myLesson и MySettings
    //    SurfaceView surfaceView;
//    public static ImageView finishImageView;
//    private List<Bitmap> bitmaps;
    private List<LessonSummary> lessonSummaries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_leasson);
        lessonSummary = StartActivity.getStartLessonSummary();

        finishButtonSave = findViewById(R.id.finishButtonSave);
//        finishButtonTakeFoto = findViewById(R.id.finishButtonTakeFoto);
        finishStringUserName = findViewById(R.id.finishStringUserName);
        finishStringPrimerovTasks = findViewById(R.id.finishStringPrimerovTasks);
        finishStringActions = findViewById(R.id.finishStringActions);
        finishStringMultiplyNumbers = findViewById(R.id.finishStringMultiplyNumbers);
        finishTextValueViewPoints = findViewById(R.id.finishTextValueViewPoints);
        finishTextViewPoints = findViewById(R.id.finishTextViewPoints);
        finishStringUserName.setText(lessonSummary.getNameUser());
        finishStringPrimerovTasks.setText(lessonSummary.getStringPrimerovTasks());
        finishStringActions.setText(lessonSummary.getStringMDSA());
        finishStringMultiplyNumbers.setText(lessonSummary.getStringMultiplyNumbers());
        finishButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.finishButtonTakeFoto:
//                    Intent intent = new Intent(this, MakeFotoActivity.class );
//                    startActivity(intent);
//                break;
            case R.id.finishButtonSave:
                if (finishStringUserName.getText().length() > 0) {
                    lessonSummary.setNameUser(finishStringUserName.getText().toString());
                    SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lessonSummary.db", MODE_PRIVATE, null);
                    saveLessonSummaryToDB(db);
                    // закрываем текущую активность
                    // закрываем mainActivity
                    // и переходим startActivity
                    finish();

                } else
                    Toast.makeText(getApplicationContext(), R.string.finishLeassonActivityNotUserName,
                            Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void saveLessonSummaryToDB(SQLiteDatabase db) {
//        ContentValues contentValues = new ContentValues();
//        SQLiteDatabase db = openOrCreateDatabase("lessons.db", Context.MODE_PRIVATE, null);
//        SQLiteDatabase db = new SQLiteDatabase();
//        String string0=""
        // Удалю таблицу, через создание новой БД?
//      db.delete("lessons", null, null);
//        if (db.isOpen()) db.close();

        String string = "CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "'nameUser' TEXT, 'countPoints' INTEGER, 'dateLesson' TEXT, 'imageFileName' TEXT, " +
                " 'stringPrimerovTasks' TEXT, 'stringMDSA' TEXT, 'stringMultiplyNumbers' TEXT )";
        db.execSQL(string);
        String string2 ="INSERT INTO lessons VALUES ( 'id'=?, '" + lessonSummary.getNameUser() + "', " + lessonSummary.getCountPoints() +", '" + lessonSummary.getDateLesson() + "' , '" + lessonSummary.getImage()
                + "', '" + lessonSummary.getStringPrimerovTasks() + "', '" + lessonSummary.getStringMDSA() + "', '" + lessonSummary.getStringMultiplyNumbers() + "')";
        db.execSQL(string2);
//        нужно ли в БД первую запись делать с ноунем и ноуфото?
        db.close();

//        String string = "CREATE TABLE IF NOT EXISTS lessons ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
//                "nameUser TEXT, countPoints INTEGER, dateLesson TEXT, image INTEGER, idResultLesson INTEGER, " +
//                " stringPrimerovTasks TEXT, stringMDSA TEXT, stringMultiplyNumbers TEXT )";
//        db.execSQL(string);
//        String string2 ="INSERT INTO lessons VALUES ('" + this.nameUser + "', " + this.countPoints +", '" + this.dateLesson + "' , " + this.imageFileName
//                + ", '" + this.stringPrimerovTasks + "', '" + this.stringMDSA + "', '" + this.stringMultiplyNumbers + "')";
//                db.execSQL(string2);
////        нужно ли в БД первую запись делать с ноунем и ноуфото?
//        db.close ();
    }
}
