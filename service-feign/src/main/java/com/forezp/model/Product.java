package com.forezp.model;

/**
 * @author haohua
 * @version 1.0
 * @describe
 * @date 2020/1/6 17:20
 */
public class Product {
    private String code;

    private  String content;

    private  Double price;

    private Integer num;


    public Product(String code, String content, Double price, Integer num) {
        this.code = code;
        this.content = content;
        this.price = price;
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
