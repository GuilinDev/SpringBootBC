package com.guilin.web.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class User {
    @NotEmpty(message = "Names can not be empty!")
    private String name;
    @Max(value = 100, message = "Age can not be over 100!")
    @Min(value = 18, message = "Age must be above 18")
    private int age;
    @NotEmpty(message = "Password can not be empty")
    @Length(min = 6, message = "Password length should be at least 6 digits")
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
