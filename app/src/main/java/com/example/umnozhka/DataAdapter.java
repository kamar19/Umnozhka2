package com.example.umnozhka;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<LessonSummary> lessonSummaries ;

        DataAdapter(Context context, List<LessonSummary> lessonSummaries) {
            this.lessonSummaries = lessonSummaries;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
            LessonSummary lessonSummary = lessonSummaries.get(position);
            holder.imageView.setImageResource(lessonSummary.getImage());
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
