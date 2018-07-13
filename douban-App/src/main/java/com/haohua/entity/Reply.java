package com.haohua.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Reply {
    private Integer id;

    private Timestamp createTime;

    private Integer state;

    private Integer userId;

    private Integer movieId;

    private String remark;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", state=" + state +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", remark='" + remark + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}