package com.haohua.entity.resultMap;    /*
 * @author  Administrator
 * @date 2018/7/13
 */

import com.haohua.entity.Movie;
import com.haohua.entity.Reply;
import com.haohua.entity.User;

import java.util.List;

public class MovieReply {
    private Integer id;
    private List<User> userList;
    private Movie movie;
    private Reply reply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "MovieReply{" +
                "id=" + id +
                ", userList=" + userList +
                ", movie=" + movie +
                ", reply=" + reply +
                '}';
    }
}
