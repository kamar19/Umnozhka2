package com.firstSet.umnozhka;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<LessonSummary> lessonSummaries;

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
        holder.nameUserView.setText(lessonSummary.getNameUser());
        holder.dateLesson.setText(lessonSummary.getDateLesson());
        holder.stringPrimerovTasks.setText(lessonSummary.getStringPrimerovTasks());
        holder.stringMDSA.setText(lessonSummary.getStringMDSA());
        holder.countPoints.setText(String.valueOf(lessonSummary.getCountPoints()));
        holder.stringMultiplyNumbers.setText(lessonSummary.getStringMultiplyNumbers());
    }

    @Override
    public int getItemCount() {
        return lessonSummaries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameUserView, dateLesson, stringPrimerovTasks, stringMDSA, stringMultiplyNumbers, countPoints;
        ViewHolder(View view) {
            super(view);
            nameUserView = (TextView) view.findViewById(R.id.listItemNameUser);
            dateLesson = (TextView) view.findViewById(R.id.listItemDateLesson);
            countPoints = (TextView) view.findViewById(R.id.listItemCountPoints);
            stringPrimerovTasks = (TextView) view.findViewById(R.id.listItemStringPrimerovTasks);
            stringMDSA = (TextView) view.findViewById(R.id.listItemStringMDSA);
            stringMultiplyNumbers = (TextView) view.findViewById(R.id.listItemStringMultiplyNumbers);
        }
    }
}
