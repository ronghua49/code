package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/9
 */

import com.haohua.entity.Author;
import com.haohua.util.SqlSessionUtil;

import org.junit.Assert;
import org.junit.Test;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class Mybatis {
    Logger logger = LoggerFactory.getLogger(Mybatis.class);
    @Test
    public void testInsert(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession(true);
        Author author = new Author();
        author.setAuthorName("释迦摩尼");
        author.setAddress("尼泊尔");
        int res = sqlSession.insert("com.haohua.mapper.Author.insertAuthor",author);
        Assert.assertEquals(1,res);
        sqlSession.close();
    }

    @Test
    public void testDelete(){
       SqlSession sqlsession = SqlSessionUtil.getSqlSession();
        int res = sqlsession.delete("com.haohua.mapper.Author.deleteById",3);
        Assert.assertEquals(1,res);
        sqlsession.close();
    }


    @Test
    public void testFindOne(){
       SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Author author = sqlSession.selectOne("com.haohua.mapper.Author.selectById",1);
       logger.debug("author:{}",author);
        sqlSession.close();

    }
    @Test
public void testFindAll(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        List<Author> authorList = sqlSession.selectList("com.haohua.mapper.Author.selectAll");
               for(Author author:authorList){
                   logger.debug("author:{}",author);
               }
    }
    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession(true);
    Author author = sqlSession.selectOne("com.haohua.mapper.Author.selectById",1);
        System.out.println(author);
    author.setAuthorName("海德格尔");
    author.setAddress("瑞士");
        System.out.println(author);
    sqlSession.update("com.haohua.mapper.Author.updateById",author);
    sqlSession.close();
}


}
