package com.haohua.entity;    /*
 * @author  Administrator
 * @date 2018/12/6
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator ="JDBC")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 身份编号
     */
    @Column(name = "card_no")
    private Integer cardNo;

    /**
     * 生日
     * jsonFormat:json转换时，转换为对应的类型
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Column(name = "birthday")
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cardNo=" + cardNo +
                ", birthday=" + birthday +
                '}';
    }
}
