package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Movie;
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
        List<Movie> movieList= movieMapper.findByMovieId(47);
        for(Movie movie: movieList){
        logger.debug("movie:{}",movie);
        }
    }


}
