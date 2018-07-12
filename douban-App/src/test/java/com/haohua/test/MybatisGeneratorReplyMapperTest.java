package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/12
 */

import com.haohua.entity.Reply;
import com.haohua.entity.ReplyExample;
import com.haohua.mapper.CommentaryMapper;
import com.haohua.mapper.ReplyMapper;
import com.haohua.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class MybatisGeneratorReplyMapperTest {
    Logger logger = LoggerFactory.getLogger(MybatisGeneratorReplyMapperTest.class);
    SqlSession sqlSession ;
    ReplyMapper replyMapper;
    @Before
    public void before(){
        sqlSession = SqlSessionUtil.getSqlSession();
        replyMapper = sqlSession.getMapper(ReplyMapper.class);
    }
    @After
    public void after(){
        sqlSession.close();
    }




    @Test
        public void fiandById(){
       ReplyExample example = new ReplyExample();
             List<Reply> replyList = replyMapper.selectByExampleWithBLOBs(example);
             for(Reply reply: replyList){
                 System.out.println(reply);
             }
        }
@Test
    public void findByState(){
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andStateEqualTo(1);
        List<Reply> replyList = replyMapper.selectByExample(replyExample);
        for(Reply reply: replyList){
            System.out.println(reply);
        }
        }

        @Test
    public void saveByReply(){
        Reply reply = new Reply();
        reply.setContent("逆向工程，是mybatis和hibernate sql不用写的相似之处 ");
        reply.setMovieId(48);
        reply.setUserId(4);
        reply.setState(1);
        //有选择条件的会对插入值进行判空校验，提高数据库效率,TimeStrap 为数据库默认值
        replyMapper.insertSelective(reply);
            System.out.println(reply.getId());
        sqlSession.commit();
        }

        @Test
        public void updateById(){
        Reply reply = replyMapper.selectByPrimaryKey(15);
        reply.setContent("图片已修改");
        replyMapper.updateByPrimaryKeyWithBLOBs(reply);
        sqlSession.commit();
        }
        @Test
        public  void updateByState(){
            ReplyExample replyExample = new ReplyExample();
            replyExample.createCriteria().andStateEqualTo(2);
            Reply reply = new Reply();
            reply.setState(1);
            replyMapper.updateByExampleSelective(reply,replyExample);
            sqlSession.commit();
    }
        @Test
    public void delete(){
        replyMapper.deleteByPrimaryKey(36);
        sqlSession.commit();
        }


}
;