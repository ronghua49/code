package com.haohua.service;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import com.haohua.dao.UserDao;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope
@Lazy
public class UserService {
    @Resource
    private UserDao userDao;
    private String name;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

