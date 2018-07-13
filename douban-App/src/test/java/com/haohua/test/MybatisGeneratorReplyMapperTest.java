package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/12
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
   private SqlSession sqlSession ;
    private ReplyMapper replyMapper;
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
        @Test
    public void orderBydesc(){
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andUserIdEqualTo(3);
        replyExample.setOrderByClause("id");
        List<Reply> replyList = replyMapper.selectByExample(replyExample);
        for(Reply reply:replyList){
            System.out.println(reply);
        }
    }
    @Test
    public void pageHelperTest(){
            //每页显示5条，显示第二页的数据
        PageHelper.startPage(2,5);
        List<Reply> replyList = replyMapper.selectByExample(null);
        for(Reply reply:replyList){
        System.out.println(reply);
        }
    }
    @Test
    public void pageHelperTest2(){
        //在查询结果中从0数到5，开始显示，显示10 条数据
        PageHelper.offsetPage(5,10);
         List<Reply> replyList = replyMapper.selectByExample(null);
        for(Reply reply:replyList){
            System.out.println(reply);
        }
    }
    @Test
    public void pagerInfoTest(){
        PageHelper.startPage(3,5);
        List<Reply> replyList = replyMapper.selectByExampleWithBLOBs(null);
        //将分页的查询结果，转换为pageInfo对象，通过前台分页插件展示页面
        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);
        for(Reply reply :pageInfo.getList()){
            System.out.println(reply.getId()+"--->"+reply.getContent());
        }
    }
}
