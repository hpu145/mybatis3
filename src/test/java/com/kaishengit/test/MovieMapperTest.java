package com.kaishengit.test;

import com.kaishengit.entity.Movie;
import com.kaishengit.entity.MovieExample;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/28.
 */
public class MovieMapperTest {
    private SqlSession sqlSession;
    private MovieMapper movieMapper;
    @Before
    public void init() {
        sqlSession = MyBatisUtil.getSqlSession();
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @After
    public void close() {
        sqlSession.close();
    }
    @Test
    public void select() {
        MovieExample movieExample = new MovieExample();
        movieExample.createCriteria().andDirectorLike("%恩%");
        List<Movie> movieList = movieMapper.selectByExample(movieExample);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }


}
