package com.haohua.entity;    /*
 * @author  Administrator
 * @date 2018/9/4
 */

public class Teacher {

    private String teacherName;
    private String teaching;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeaching() {
        return teaching;
    }

    public void setTeaching(String teaching) {
        this.teaching = teaching;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ", teaching='" + teaching + '\'' +
                '}';
    }
}
