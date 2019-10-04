package com.vincentz.retrofitapi.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.vincentz.retrofitapi.Models.Student;
import com.vincentz.retrofitapi.R;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student> {
    private Context mContext;
    private List<Student> studentsList;

    public StudentListAdapter(Context context, ArrayList<Student> list) {
        super(context, 0, list);
        mContext = context;
        studentsList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_layout, parent, false);
        Student currentStudent = studentsList.get(position);

        TextView id = listItem.findViewById(R.id.txt_id);
        id.setText(String.valueOf(currentStudent.getId()));

        TextView name = listItem.findViewById(R.id.txt_name);
        name.setText(currentStudent.getName());

        TextView age = listItem.findViewById(R.id.txt_age);
        age.setText(String.valueOf(currentStudent.getAge()));
        return listItem;
    }
}