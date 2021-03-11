package com.guilin.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UserParam {
    private long id;
    @NotEmpty(message = "Name can't be null")
    private String userName;
    @NotEmpty(message = "Password can't be null")
    @Length(min = 6, message = "Passwrod length must at least 6")
    private String passWord;
    @Max(value = 100, message = "Age can exceeed 100")
    @Min(value = 16, message = "Age must at least 18")
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                '}';
    }
}
