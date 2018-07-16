package com.haohua.service;    /*
 * @author  Administrator
 * @date 2018/7/14
 */

import com.haohua.dao.UserDao;
import com.haohua.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UserService {

    private UserDao userDao;
    private User user;
    private Integer num;
    private List<String> list;
    private Map<String ,Object> map;
    private Set<Double> set;
    private Properties properties;

    //提供对象的set方法，让容器把生成的对象注入到属性
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    public void setUser(User user){
        this.user = user;
    }
    public UserService(){}
    //提供类的构造方法，在此类创建时，依赖spring容器给予的对象，一同创建
    public UserService(UserDao userDao){
        this.userDao=userDao;
    }
    public UserService(User user){
        this.user = user;
    }
//构造方法，传入map集合
    public UserService(Map<String,Object> map){
        this.map=map;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Set<Double> getSet() {
        return set;
    }

    public void setSet(Set<Double> set) {
        this.set = set;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void fu(){
        userDao.insert();
    }
    public void fun2(){
        System.out.println(user);
    }
}
