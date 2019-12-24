package com.haohua.socket.model;

public class Teacher {

    private String  subject;

    private  String name;

    public Teacher(String subject, String name) {
        this.subject = subject;
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
