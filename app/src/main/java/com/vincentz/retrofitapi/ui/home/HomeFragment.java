package com.vincentz.retrofitapi.ui.home;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vincentz.retrofitapi.Models.EyeColor;
import com.vincentz.retrofitapi.Models.Student;
import com.vincentz.retrofitapi.R;
import com.vincentz.retrofitapi.Services.ServiceBuilder;
import com.vincentz.retrofitapi.Services.StudentCalls;
import com.vincentz.retrofitapi.Services.StudentService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.*;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    ListView listView;
    TextView textView_Student;
    public EditText inp_id, inp_name, inp_desc, inp_height, inp_shoe, inp_age;
    Switch swi_smoker;
    Spinner spi_eye;
    NumberPicker num_age;
    StudentService studentService;
    View root;
    public TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        studentService = ServiceBuilder.buildService(StudentService.class);
        FindGUI();
        ButtonHandlers();

        spi_eye.setAdapter(new ArrayAdapter<EyeColor>(container.getContext(), android.R.layout.simple_spinner_dropdown_item, EyeColor.values()));
        return root;
    }

    private void FindGUI() {
        textView_Student = root.findViewById(R.id.textView);
        inp_id = root.findViewById(R.id.et_id);
        inp_name = root.findViewById(R.id.et_name);
        inp_age = root.findViewById(R.id.et_age);
        inp_desc = root.findViewById(R.id.et_description);
        inp_height = root.findViewById(R.id.et_height);
        inp_shoe = root.findViewById(R.id.et_shoeSize);
        swi_smoker = root.findViewById(R.id.et_smoker);
        spi_eye = root.findViewById(R.id.spin_eyeColor);
        num_age = root.findViewById(R.id.nump_age);
    }

    private void ButtonHandlers() {

        root.findViewById(R.id.button_Get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new StudentCalls().GetStudent();

                Call<Student> request = studentService.getStudentById(Integer.parseInt(inp_id.getText().toString()));
                request.enqueue(new Callback<Student>()
                {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        FillDataSheet(response.body());
                        textView.setText(response.body().getName());
                    }
                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        textView.setText(t.getMessage());
                    }
                });
            }
        });

        root.findViewById(R.id.button_Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete?")
                        .setMessage("Do you want to delete ")
                        .setIcon(R.drawable.ic_contacts_black_24dp)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Call<ResponseBody> request = studentService.deleteStudentById(Integer.parseInt(inp_id.getText().toString()));
                                request.enqueue(new Callback<ResponseBody>()
                                {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        textView_Student.setText("Deleted Student");
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        textView_Student.setText(t.getMessage());
                                    }
                                });



                            }
                        } )
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();










            }
        });

        root.findViewById(R.id.button_createStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = ReadDataSheet();
                Call<Student> request = studentService.createStudent(student);
                request.enqueue(new Callback<Student>()
                {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        //textView.setText(response.body().getName());
                    }
                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        //textView.setText(t.getMessage());
                    }
                });
            }
        });

        root.findViewById(R.id.button_Update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = ReadDataSheet();
                Call<ResponseBody> request = studentService.updateStudent(student.getId(), student);
                request.enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //textView.setText(response.body().getName());
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //textView.setText(t.getMessage());
                    }
                });
            }
        });
    }

    public Student ReadDataSheet() {
        int id = Integer.parseInt(inp_id.getText().toString());
        String name = inp_name.getText().toString();
        String desc = inp_desc.getText().toString();
        int age = Integer.parseInt(inp_age.getText().toString());
        float height = Float.parseFloat(inp_height.getText().toString());
        double shoeSize = Double.parseDouble(inp_shoe.getText().toString());
        boolean smoker = swi_smoker.isChecked();
        EyeColor eyeColor = (EyeColor) spi_eye.getSelectedItem();

        return new Student(id, name, desc, age, smoker, eyeColor, shoeSize, height);
    }

    public void FillDataSheet(Student student) {
        inp_name.setText(student.getName());
        inp_desc.setText(student.getDescription());
        inp_age.setText(String.valueOf(student.getAge()));
        inp_height.setText(String.valueOf(student.getHeight()));
        inp_shoe.setText(String.valueOf(student.getShoeSize()));
        swi_smoker.setChecked(student.isSmoker());
        spi_eye.setSelection(student.getEyeColor().ordinal());
    }

//    private void UpdateList() {
//        Call<List<Student>> requestList = studentService.getStudentAll();
//        requestList.enqueue(new Callback<List<Student>>()
//        {
//            @Override
//            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
//
//                ArrayList<String> list = new ArrayList<>();
//                for (Student s : response.body()) list.add(s.getName());
//
////                Toast.makeText(MainActivity.this,"Numbers in list : " + list.size(), Toast.LENGTH_LONG);
//                ArrayAdapter adapter = new ArrayAdapter(getView().getContext(), android.R.layout.simple_list_item_1, list);
//                listView.setAdapter(adapter);
//            }
//            @Override
//            public void onFailure(Call<List<Student>> call, Throwable t) {
//                textView_Student.setText(t.getMessage());
//            }
//        });
//    }

}