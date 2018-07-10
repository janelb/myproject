package com.libang.test;

import com.libang.entity.Movie;
import com.libang.mapper.MovieMapper;
import com.libang.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/10 20:38
 */
public class MovieMapperTestCase {
    Logger logger = LoggerFactory.getLogger(MovieMapperTestCase.class);
    private SqlSession sqlSession;
    private MovieMapper movieMapper;
    @Before
    public void before(){
         sqlSession = MybatisUtils.getSqlSession();
         movieMapper = sqlSession.getMapper(MovieMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testFindMovieByKeys(){
        /*String movieName = "%西游%";*/
        List<Movie> movieList = movieMapper.findMovieByKeys( "%西游%");
        logger.debug("电影数量:{}",movieList.size());
    }






}
