package com.haohua.entity;    /*
 * @author  Administrator
 * @date 2018/7/9
 */

public class Book {
    private Integer id;
    private String bookName;
    private String author;
    private Integer total;
    private Integer nowNum;
    private String isbn;
    private Card card;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbookName() {
        return bookName;
    }

    public void setName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", total=" + total +
                ", nowNum=" + nowNum +
                ", isbn='" + isbn + '\'' +
                ", card=" + card +
                '}';
    }
}
