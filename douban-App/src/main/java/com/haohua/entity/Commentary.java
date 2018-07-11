package com.haohua.entity;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import java.sql.Timestamp;

public class Commentary {
    private Integer id;
    private String content;
    private Timestamp commentaryTime;
    private Integer state;
    private Integer userId;
    private Integer movieId;
    private Movie  movie;
    private User user;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCommentaryTime() {
        return commentaryTime;
    }

    public void setCommentaryTime(Timestamp commentaryTime) {
        this.commentaryTime = commentaryTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ",commentaryTime =" + commentaryTime +
                ", state=" + state +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", movie=" + movie +
                ", user=" + user +
                '}';
    }
}
