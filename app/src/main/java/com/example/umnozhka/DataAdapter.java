package com.example.umnozhka;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<LessonSummary> lessonSummaries ;
//        private Bitmap bitmap;
        private List<Bitmap> bitmaps;


        DataAdapter(Context context, List<LessonSummary> lessonSummaries) {
            this.lessonSummaries = lessonSummaries;
            this.inflater = LayoutInflater.from(context);
            Iterator<LessonSummary> iterator=lessonSummaries.iterator();
            while (iterator.hasNext())
             {
                 LessonSummary lessonSummary = iterator.next();
                 FileInputStream fileInputStream = null;
                 try {
                     fileInputStream = new FileInputStream(lessonSummary.getImage());
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                 Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                 bitmaps.add(bitmap);
            }

        }
        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
            LessonSummary lessonSummary = lessonSummaries.get(position);
            holder.imageView.setImageBitmap(bitmaps.get(position));
            holder.nameUserView.setText(lessonSummary.getNameUser());
            holder.dateLesson.setText(lessonSummary.getDateLesson());
            holder.stringPrimerovTasks.setText(lessonSummary.getDateLesson());
            holder.stringMDSA.setText(lessonSummary.getDateLesson());
            holder.stringMultiplyNumbers.setText(lessonSummary.getDateLesson());
        }

        @Override
        public int getItemCount() {
            return lessonSummaries.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView imageView;
            final TextView nameUserView, dateLesson,stringPrimerovTasks,stringMDSA,stringMultiplyNumbers;
            ViewHolder(View view){
                super(view);
                imageView = (ImageView)view.findViewById(R.id.imageName);
                nameUserView = (TextView) view.findViewById(R.id.nameUser);
                dateLesson = (TextView) view.findViewById(R.id.dateLesson);
                stringPrimerovTasks = (TextView) view.findViewById(R.id.stringPrimerovTasks);
                stringMDSA = (TextView) view.findViewById(R.id.stringMDSA);
                stringMultiplyNumbers = (TextView) view.findViewById(R.id.stringMultiplyNumbers);
            }
        }
}
