package com.haohua.entity;    /*
 * @author  Adminastor
 * @date 2018/7/9
 *
 */

public class Author {
    private Integer id;
    private String authorName;
    private String address;

        public Author(){

        }
    public Author(String authorName, String address) {
        this.authorName = authorName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
