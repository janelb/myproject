package com.libang.mapper;

import com.libang.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/10 20:41
 */
public interface MovieMapper {

    /**
     * 模糊查询
     * @param movieName
     * @return
     */
    List<Movie> findMovieByKeys(@Param("movieName") String movieName);


}
