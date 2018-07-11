package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Commentary;
import com.haohua.mapper.CommentaryMapper;
import com.haohua.mapper.MovieMapper;
import com.haohua.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class CommentaryMapperTest {
    Logger logger = LoggerFactory.getLogger(CommentaryMapperTest.class);
    SqlSession sqlSession ;
    CommentaryMapper commentaryMapper;
    @Before
    public void before(){
        sqlSession = SqlSessionUtil.getSqlSession();
        commentaryMapper = sqlSession.getMapper(CommentaryMapper.class);
    }
    @After
    public void after(){
        sqlSession.close();
    }


    @Test
    public void fingByCommentaryIds(){
        List<Integer> idList = Arrays.asList(1,2,3,4);
        List<Commentary> commentaryList = commentaryMapper.findByCommentaryId(idList);
        for(Commentary commentary: commentaryList){
            logger.debug("前4条的评论为：{}",commentary);
        }
    }

    @Test
    public  void findByUserIdTest(){
        Commentary commentary = commentaryMapper.findByUserId(5);
     logger.debug("评论为：{}",commentary);
    }
}
