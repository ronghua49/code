package com.haohua.dao;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void delete(){
        System.out.println("delete.....");
    }
    public int save(int age){
        System.out.println("saving...");
        return  age;
    }

}
