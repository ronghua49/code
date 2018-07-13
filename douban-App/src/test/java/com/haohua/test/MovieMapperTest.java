package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Movie;
import com.haohua.entity.ReplyExample;
import com.haohua.entity.User;
import com.haohua.mapper.MovieMapper;
import com.haohua.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MovieMapperTest {
 Logger logger = LoggerFactory.getLogger(MovieMapperTest.class);
 SqlSession sqlSession ;
 MovieMapper movieMapper;
    @Before
    public void before(){
        sqlSession = SqlSessionUtil.getSqlSession();
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void findByMovieIdTest(){
        Movie justMovie = movieMapper.findByMovieId(47);
        logger.debug("movie:{}",justMovie);
        System.out.println("....................................");
        sqlSession.close();
        sqlSession = SqlSessionUtil.getSqlSession();
        movieMapper = sqlSession.getMapper(MovieMapper.class);
        Movie movie = movieMapper.findByMovieId(47);
        movie.setScanNum(100);
        movieMapper.updateByMovie(movie);
    }

    @Test
    public void save(){
        Movie movie = new Movie();
        movie.setArea("美国");
        movie.setDirectorName("巨石强森");
        movie.setMovieName("狂暴巨兽");
        movieMapper.saveMovie(movie);
        sqlSession.commit();
    }
}
