package com.haohua.socket.model;

public class Student {

    private String  name;

    private  String sex;

    private String  hobby;

    private  String age;


    private String region;

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", age='" + age + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
