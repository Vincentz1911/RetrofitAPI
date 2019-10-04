package com.vincentz.retrofitapi.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vincentz.retrofitapi.Models.Student;
import com.vincentz.retrofitapi.R;
import com.vincentz.retrofitapi.Services.ServiceBuilder;
import com.vincentz.retrofitapi.Services.StudentService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private StudentService studentService;
    ListView listView;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        studentService = ServiceBuilder.buildService(StudentService.class);
        listView = root.findViewById(R.id.listView);
        UpdateList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return root;
    }

    private void UpdateList() {
        Call<List<Student>> requestList = studentService.getStudentAll();
        requestList.enqueue(new Callback<List<Student>>()
        {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                ArrayList<Student> list = (ArrayList)response.body();
                StudentListAdapter mAdapter = new StudentListAdapter(getActivity().getBaseContext(), list);
                listView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}