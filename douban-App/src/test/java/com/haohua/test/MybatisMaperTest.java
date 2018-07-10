package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/10
 */

import com.haohua.entity.Author;
import com.haohua.mapper.AuthorMapper;
import com.haohua.mapper.AuthorMapper;
import com.haohua.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class MybatisMaperTest {
Logger logger = LoggerFactory.getLogger(Mybatis.class);
    SqlSession sqlsession;
    AuthorMapper authorMapper;
    @Before
    public void before(){
        sqlsession = SqlSessionUtil.getSqlSession();
         authorMapper=sqlsession.getMapper(AuthorMapper.class);
    }
    @After
    public  void after(){
        sqlsession.close();
    }
    @Test
    public void testInsert(){
        Author author = new Author("希特勒","德国");
        authorMapper.insertAuthor(author);
        sqlsession.commit();
       logger.debug("id:{}",author.getId());
    }
    @Test
    public void testDelete(){
        authorMapper.deleteById(1);
        sqlsession.commit();
    }

    @Test
    public void testUpdate(){
        Author author = authorMapper.selectById(4);
        author.setAddress("瑞典");
        author.setAuthorName("Towalovers");
        authorMapper.updateById(author);
        sqlsession.commit();
    }
    @Test
    public void testFindAll(){
        String key = "尼";
        key = "%"+key+"%";
        List<Author> authorList = authorMapper.selectAll(key);
        for(Author author: authorList){
           logger.debug("author:{}",author);
        }
    }
    @Test
    public void testFindOne(){
        Author author = authorMapper.selectById(3);
        System.out.println(author);
    }

    @Test
    public void testFindPage(){
    List<Author> authorList = authorMapper.findByPage(0,4);
        System.out.println(authorList.size());
    }



}
