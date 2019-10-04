package com.vincentz.retrofitapi.Services;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vincentz.retrofitapi.Models.Student;
import com.vincentz.retrofitapi.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentCalls extends HomeFragment {
    private StudentService studentService ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentService = ServiceBuilder.buildService(StudentService.class);
    }

    public void GetStudent()
    {
        Call<Student> request = studentService.getStudentById(Integer.parseInt(inp_id.getText().toString()));
        request.enqueue(new Callback<Student>()
        {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                FillDataSheet(response.body());
                textView.setText("Found " + response.body().getName());
            }
            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

}
