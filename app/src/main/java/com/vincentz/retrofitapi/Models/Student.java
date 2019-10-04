package com.vincentz.retrofitapi.Models;

public class Student {
    private int id;
    private String name;
    private String description;
    private int age;
    private boolean smoker;
    private EyeColor eyeColor;
    private double shoeSize;
    private float height;

    public Student() {
    }

    public Student(int id, String name, String description, int age, boolean smoker, EyeColor eyeColor, double shoeSize,
                   float height) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.age = age;
        this.smoker = smoker;
        this.eyeColor = eyeColor;
        this.shoeSize = shoeSize;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(double shoeSize) {
        this.shoeSize = shoeSize;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


}
