package com.haohua.entity;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import java.sql.Timestamp;
import java.util.List;

public class Movie {
    private Integer id;
    private String moiveName;
    private String directorName;
    private String  area;
    private Integer year;
    private Timestamp timestamp;
    private  Integer scanNum;
    private Integer replyNum;
    private List<Type> typeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoiveName() {
        return moiveName;
    }

    public void setMoiveName(String moiveName) {
        this.moiveName = moiveName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getScanNum() {
        return scanNum;
    }

    public void setScanNum(Integer scanNum) {
        this.scanNum = scanNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", moiveName='" + moiveName + '\'' +
                ", directorName='" + directorName + '\'' +
                ", area='" + area + '\'' +
                ", year=" + year +
                ", timestamp=" + timestamp +
                ", scanNum=" + scanNum +
                ", replyNum=" + replyNum +
                ", typeList=" + typeList +
                '}';
    }
}
