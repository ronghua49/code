package com.haohua.entity;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import java.sql.Timestamp;

public class Type {
    private Integer id;
    private String typeName;
    private Timestamp timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
