package com.vincentz.retrofitapi.Services;

import com.vincentz.retrofitapi.Models.Student;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {
    @GET("Student")
    Call<List<Student>> getStudentAll();

    @GET("Student/{id}")
    Call<Student> getStudentById(@Path("id") int id);

    @DELETE("Student/{id}")
    Call<ResponseBody> deleteStudentById(@Path("id") int id);

    @POST("Student/{student}")
    Call<Student> createStudent(@Body Student student);

    @PUT("Student/{id}")
    Call<ResponseBody> updateStudent(@Path("id") int id, @Body Student student);
}
