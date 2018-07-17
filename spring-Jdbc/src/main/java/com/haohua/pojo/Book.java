package com.haohua.pojo;    /*
 * @author  Administrator
 * @date 2018/7/17
 */

import org.springframework.stereotype.Repository;

@Repository
public class Book {
    private Integer id;
    private String name;
    private String author;
    private Integer nowNum;
    private Integer total;
    private String isbn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNowNum() {
        return nowNum;
    }

    public void setNowNum(Integer nowNum) {
        this.nowNum = nowNum;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", nowNum=" + nowNum +
                ", total=" + total +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
